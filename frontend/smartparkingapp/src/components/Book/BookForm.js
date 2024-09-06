import React, { useState, useEffect, createContext } from "react";
import "./BookForm.css";
import '@fortawesome/fontawesome-free/css/all.min.css';
import BookTicket from './BookTicket';

export const PriceContext = createContext();

function BookForm({ onClose, parkingData }) {
    const { name, address, pricePerHour, parkingSpotList } = parkingData;
    const [selectedSpot, setSelectedSpot] = useState(null);
    const [closing, setClosing] = useState(false);
    const [showDetails, setShowDetails] = useState(false);
    const [existingBookings, setExistingBookings] = useState([]);
    const [ticketInfo, setTicketInfo] = useState(null);


    const handleSpotClick = (spot) => {
        setSelectedSpot(spot);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (selectedSpot === null) {
            alert("Please select a parking spot.");
            return;
        }
        setShowDetails(true);
    };

    const closeForm = () => {
        setClosing(true);
        setTimeout(() => {
            onClose();
        }, 500);
    };


    const handleBookingDetailsSubmit = (ticketData) => {
        const { vehicleType, licensePlate, startDate, endDate } = ticketData;
        const start = new Date(startDate);
        const end = new Date(endDate);
        const hours = (end - start) / 1000 / 3600;
        const amount = (hours * pricePerHour).toFixed(2);

        const ticket = {
            carModel: vehicleType,
            vehicleNumber: licensePlate,
            spotNumber: selectedSpot.spotNumber,
            startDate,
            endDate,
        };

        setTicketInfo(ticket);
    };

    return (
        <PriceContext.Provider value={pricePerHour}>
            <div className={`form-container ${closing ? "closing" : ""}`}>
                <div className={`form ${closing ? "closing" : ""}`}>
                    <h2 className="nameSpot">Book a Spot at {name}</h2>
                    <div className="parking-info">
                        <p>Address: {address}</p>
                        <p>Price per hour: {pricePerHour}$</p>
                    </div>

                    <div className="spots-container">
                        {parkingSpotList && parkingSpotList.length > 0 ? (
                            parkingSpotList.map((spot) => (
                                <div
                                    key={spot.id}
                                    className={`spot ${spot.status === true ? 'available' : ''} ${selectedSpot?.id === spot.id ? 'selected' : ''}`}
                                    onClick={() => handleSpotClick(spot)}
                                >
                                    <i className={`fas fa-car ${spot.status === true ? 'car-icon' : 'car-icon-disabled'}`}></i>
                                    <span className="spot-number">{spot.spotNumber}</span>
                                </div>
                            ))
                        ) : (
                            <p>No parking spots available.</p>
                        )}
                    </div>

                    <form onSubmit={handleSubmit}>
                        <button type="submit" disabled={selectedSpot === null}>Book</button>
                    </form>

                    <button className="close-btn" onClick={closeForm}>
                        <i className="fas fa-times"></i>
                    </button>
                </div>

                {showDetails && (
                    <BookTicket
                        spotIndex={selectedSpot.spotNumber}
                        spotId={selectedSpot.id}
                        existingBookings={existingBookings}
                        onClose={() => setShowDetails(false)}
                        onBookingSubmit={handleBookingDetailsSubmit}
                        ticketInfo={ticketInfo}
                    />
                )}
            </div>
        </PriceContext.Provider>

    );
}

export default BookForm;
