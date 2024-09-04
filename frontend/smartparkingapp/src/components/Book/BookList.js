import React, { useState, useEffect } from 'react';
import Hero from '../../asset/images/hero.jpg';
import './BookList.css';
import BookForm from './BookForm';
import BookSearch from './BookSearch';
import axios from 'axios'; // Sử dụng axios để thực hiện các yêu cầu HTTP
import { authAPIs, endpoints } from '../../configs/APIs'; // Import endpoints từ cấu hình API

function ClickableImage({ image, name, address, startTime, endTime, pricePerHour, totalSpots, description, onClick }) {
    return (
        <div className="image-container" onClick={onClick}>
            <img className="image-shape" src={image || Hero} alt={name} />
            <div className="text-overlay">
                <span>{name}</span>
                <p>Address: {address}</p>
                <p>Price: {pricePerHour || 'N/A'}$ - hour</p>
                <p>Total Spots: {totalSpots || 'N/A'}</p>
                <p>Start Time: {startTime}</p>
                <p>End Time: {endTime}</p>
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

    useEffect(() => {
        const fetchParkingData = async () => {
            try {
                const response = await authAPIs().get(endpoints.parkinglots);
                console.log("Parking Data from API:", response.data); // Log API response for debugging

                // Adjust response to match the structure expected in ClickableImage
                const data = response.data.map(parking => ({
                    ...parking,
                    image: parking.image || Hero, // Use default image if not provided
                    startTime: parking.startTime ? new Date(parking.startTime).toLocaleString() : 'N/A',
                    endTime: parking.endTime ? new Date(parking.endTime).toLocaleString() : 'N/A'
                }));
                setFilteredData(data);
            } catch (err) {
                setError('Failed to fetch parking data');
                console.error(err);
            } finally {
                setLoading(false);
            }
        };

        fetchParkingData();
    }, []);

    const handleImageClick = (parking) => {
        setSelectedParking(parking);
        setShowForm(true);
    };

    const handleCloseForm = () => {
        setShowForm(false);
        setSelectedParking(null);
    };

    const handleSearchResults = (results) => {
        setFilteredData(results);
    };

    if (loading) return <div>Loading...</div>;
    if (error) return <div>{error}</div>;

    return (
        <div>
            <h1>Choose Your Parking</h1>
            <h2>Other parking lots will be added soon. Stay tuned!!!</h2>
            <BookSearch onSearchResults={handleSearchResults} />
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
