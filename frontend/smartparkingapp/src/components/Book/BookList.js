import React, { useState } from 'react';
import Hero from '../../asset/images/hero.jpg';
import './BookList.css';
import BookForm from './BookForm';
import BookSearch from './BookSearch';

const parkingData = [
    {
        image: Hero,
        name: "Central Parking",
        address: "123 Main St, Downtown",
        start_time: "2024-08-10 06:00:00",
        end_time: "2024-08-10 22:00:00",
        price_per_hour: 5.5,
        total_spots: 100,
        description: "Free for first 2 hours"
    }

];

function ClickableImage({ image, name, address, start_time, end_time, price_per_hour, total_spots, description, onClick }) {
    return (
        <div className="image-container" onClick={onClick}>
            <img className="image-shape" src={image} alt={name} />
            <div className="text-overlay">
                <span>{name}</span>
                <p>Address: {address}</p>

                <p>Price: {price_per_hour}$ - hour</p>
                <p>Total Spots: {total_spots}</p>
            </div>
        </div>
    );
}

function BookList() {
    const [showForm, setShowForm] = useState(false);
    const [selectedParking, setSelectedParking] = useState(null);
    const [filteredData, setFilteredData] = useState(parkingData);

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

    return (
        <div>
            <h1>Choose your parking</h1>
            <h2>Other parking lots will be added soon. Stay tuned!!!</h2>
            <BookSearch parkingData={parkingData} onSearchResults={handleSearchResults} />
            <div className="img-container">
                {filteredData.map((parking, index) => (
                    <ClickableImage
                        key={index}
                        image={parking.image}
                        name={parking.name}
                        address={parking.address}
                        start_time={parking.start_time}
                        end_time={parking.end_time}
                        price_per_hour={parking.price_per_hour}
                        total_spots={parking.total_spots}
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
