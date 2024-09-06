import React, { useState, useEffect } from "react";
import "./BookForm.css";
import '@fortawesome/fontawesome-free/css/all.min.css';
import BookTicket from './BookTicket';

function BookForm({ onClose, parkingData }) {
    const { name, address, pricePerHour, parkingSpotList } = parkingData;
    const [selectedSpot, setSelectedSpot] = useState(null);
    const [closing, setClosing] = useState(false);
    const [showDetails, setShowDetails] = useState(false);
    const [existingBookings, setExistingBookings] = useState([]);
    const [ticketInfo, setTicketInfo] = useState(null);

    // Handle selecting a parking spot
    const handleSpotClick = (spot) => {
        setSelectedSpot(spot);
    };

    // Handle submitting the form
    const handleSubmit = (e) => {
        e.preventDefault();
        if (selectedSpot === null) {
            alert("Please select a parking spot.");
            return;
        }
        setShowDetails(true);
    };

    // Close form with a CSS transition
    const closeForm = () => {
        setClosing(true);
        setTimeout(() => {
            onClose();
        }, 500); // Transition delay
    };

    // Handle booking details submission and calculate total amount
    const handleBookingDetailsSubmit = (ticketData) => {
        const { vehicleType, licensePlate, startDate, endDate } = ticketData;
        const start = new Date(startDate);
        const end = new Date(endDate);
        const hours = (end - start) / 1000 / 3600;
        const amount = (hours * pricePerHour).toFixed(2); // Calculate amount based on price per hour

        const ticket = {
            name: "John Doe", // Collect user input in a real app
            carModel: vehicleType,
            vehicleNumber: licensePlate,
            spotNumber: selectedSpot.spotNumber,
            startDate,
            endDate,
            amount,
        };

        setTicketInfo(ticket);
    };

    // UseEffect to fetch existing bookings
    useEffect(() => {
        if (selectedSpot) {
            const bookings = [
                { spotNumber: selectedSpot.spotNumber, startDate: "2024-08-05T10:00", endDate: "2024-08-05T12:00" },
                { spotNumber: selectedSpot.spotNumber, startDate: "2024-08-05T13:00", endDate: "2024-08-05T14:00" }
            ];
            setExistingBookings(bookings.filter(booking => booking.spotNumber === selectedSpot.spotNumber));
        }
    }, [selectedSpot]);

    return (
        <div className={`form-container ${closing ? "closing" : ""}`}>
            <div className={`form ${closing ? "closing" : ""}`}>
                <h2>Book a Spot at {name}</h2>
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
                    spotId={selectedSpot.id} // Pass the selected spot id to BookTicket
                    existingBookings={existingBookings}
                    onClose={() => setShowDetails(false)}
                    onBookingSubmit={handleBookingDetailsSubmit}
                    ticketInfo={ticketInfo}
                />
            )}
        </div>
    );
}

export default BookForm;
