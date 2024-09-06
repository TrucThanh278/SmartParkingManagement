import React, { useState, useEffect } from 'react';
import Hero from '../../asset/images/hero.jpg';
import './BookList.css';
import BookForm from './BookForm';
import BookSearch from './BookSearch';
import { authAPIs, endpoints } from '../../configs/APIs';

function ClickableImage({ image, name, address, startTime, endTime, pricePerHour, totalSpots, description, onClick }) {
    return (
        <div className="image-container" onClick={onClick}>
            <img className="image-shape" src={image || Hero} alt={name} />
            <div className="text-overlay">
                <span>{name}</span>
                <p>Address: {address}</p>
                <p>Price: {pricePerHour || 'N/A'}$ - hour</p>
                <p>Total Spots: {totalSpots || 'N/A'}</p>
                <p>Start Time: {startTime || 'N/A'}</p>
                <p>End Time: {endTime || 'N/A'}</p>
                <p>{description || 'No description'}</p>
            </div>
        </div>
    );
}

function BookList() {
    const [showForm, setShowForm] = useState(false);
    const [selectedParking, setSelectedParking] = useState(null);
    const [filteredData, setFilteredData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [page, setPage] = useState(1);
    const [totalPages, setTotalPages] = useState(1);

    useEffect(() => {
        const fetchParkingData = async () => {
            try {
                const response = await authAPIs().get(endpoints.parkinglots(page));
                console.log("Full API Response:", response);

                if (response.data && Array.isArray(response.data.data)) {
                    console.log("Data inside response.data.data:", response.data.data);

                    const formattedData = response.data.data.map(parking => ({
                        ...parking,
                        image: parking.image || Hero,
                        startTime: parking.startTime || 'N/A',
                        endTime: parking.endTime || 'N/A'
                    }));
                    setFilteredData(formattedData);

                    setTotalPages(response.data.totalPages || 1);
                } else {
                    throw new Error("Invalid API response structure");
                }
            } catch (err) {
                setError('Error fetching parking data');
                console.error("Error fetching data:", err);
            } finally {
                setLoading(false);
            }
        };

        fetchParkingData();
    }, [page]);

    const handleImageClick = (parking) => {
        setSelectedParking(parking);
        setShowForm(true);
    };

    const handleCloseForm = () => {
        setShowForm(false);
        setSelectedParking(null);
    };

    const handlePageChange = (newPage) => {
        if (newPage >= 1 && newPage <= totalPages && newPage !== page) {
            setPage(newPage);
        }
    };

    if (loading) return <div>Loading...</div>;
    if (error) return <div>{error}</div>;

    return (
        <div>
            <h1 className='h1-description'>Select Your Parking Lot</h1>
            <h2 className='h2-description'>More parking lots will be added soon. Stay tuned!</h2>
            <BookSearch
                onSearchResults={setFilteredData}
                setTotalPages={setTotalPages}
                setPage={setPage}
            />

            <div className="pagination">
                <button
                    onClick={() => handlePageChange(page - 1)}
                    disabled={page <= 1}
                    aria-label="Previous Page"
                >
                    <i className="fas fa-chevron-left"></i>
                </button>
                <span>Page {page} of {totalPages}</span>
                <button
                    onClick={() => handlePageChange(page + 1)}
                    disabled={page >= totalPages}
                    aria-label="Next Page"
                >
                    <i className="fas fa-chevron-right"></i>
                </button>
            </div>

            <div className="img-container">
                {filteredData.map((parking, index) => (
                    <ClickableImage
                        key={index}
                        image={parking.image}
                        name={parking.name}
                        address={parking.address}
                        startTime={parking.startTime}
                        endTime={parking.endTime}
                        pricePerHour={parking.pricePerHour}
                        totalSpots={parking.totalSpots}
                        description={parking.description}
                        onClick={() => handleImageClick(parking)}
                    />
                ))}
            </div>
            {showForm && <BookForm parkingData={selectedParking} onClose={handleCloseForm} />}

        </div>
    );
}

export default BookList;
