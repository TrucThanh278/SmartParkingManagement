// import React, { useState } from "react";
// import "./BookForm.css";
// import '@fortawesome/fontawesome-free/css/all.min.css';
// import BookTicket from './BookTicket';

// const totalSpots = 10; // Number of total parking spots

// function BookForm({ onClose, parkingData }) {
//     const [spots, setSpots] = useState(Array(totalSpots).fill(false));
//     const [selectedSpot, setSelectedSpot] = useState(null);
//     const [closing, setClosing] = useState(false);
//     const [showDetails, setShowDetails] = useState(false);

//     const handleSpotClick = (index) => {
//         if (spots[index]) return; // Do nothing if the spot is already taken
//         setSelectedSpot(index);
//     };

//     const handleSubmit = (e) => {
//         e.preventDefault();
//         if (selectedSpot === null) {
//             alert("Please select a parking spot.");
//             return;
//         }
//         setShowDetails(true);
//     };

//     const closeForm = () => {
//         setClosing(true);
//         setTimeout(() => {
//             onClose();
//         }, 500); // Delay must match the CSS transition duration
//     };

//     return (
//         <div className={`form-container ${closing ? "closing" : ""}`}>
//             <div className={`form ${closing ? "closing" : ""}`}>
//                 <h2>Book a Spot</h2>
//                 <span>{parkingData.text}</span>
//                 <div className="parking-info">
//                     <p>Location: {parkingData.location}</p>
//                     <p>Start Time: {parkingData.startTime}</p>
//                     <p>End Time: {parkingData.endTime}</p>
//                     <p>Price: {parkingData.price}</p>
//                 </div>
//                 <div className="spots-container">
//                     {spots.map((spot, index) => (
//                         <div
//                             key={index}
//                             className={`spot ${spot ? "taken" : ""} ${selectedSpot === index ? "selected" : ""}`}
//                             onClick={() => handleSpotClick(index)}
//                         >
//                             {spot ? <span className="spot-taken">X</span> : <i className="fas fa-car"></i>}
//                             <span className="spot-number">{index + 1}</span>
//                         </div>
//                     ))}
//                 </div>
//                 <form onSubmit={handleSubmit}>
//                     <button type="submit" disabled={selectedSpot === null}>Book</button>
//                 </form>
//                 <button className="close-btn" onClick={closeForm}>
//                     <i className="fas fa-times"></i>
//                 </button>
//             </div>
//             {showDetails && <BookTicket spotIndex={selectedSpot} onClose={() => setShowDetails(false)} />}
//         </div>
//     );
// }

// export default BookForm;


import React, { useState, useEffect } from "react";
import "./BookForm.css";
import '@fortawesome/fontawesome-free/css/all.min.css';
import BookTicket from './BookTicket';

const totalSpots = 10; // Number of total parking spots

function BookForm({ onClose, parkingData }) {
    const [spots, setSpots] = useState(Array(totalSpots).fill(false));
    const [selectedSpot, setSelectedSpot] = useState(null);
    const [closing, setClosing] = useState(false);
    const [showDetails, setShowDetails] = useState(false);
    const [existingBookings, setExistingBookings] = useState([]);
    const [ticketInfo, setTicketInfo] = useState(null);

    const handleSpotClick = (index) => {
        if (spots[index]) return; // Do nothing if the spot is already taken
        setSelectedSpot(index);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (selectedSpot === null) {
            alert("Please select a parking spot.");
            return;
        }
        setShowDetails(true);
    };

    useEffect(() => {
        if (selectedSpot !== null) {
            // Fetch existing bookings for the selected spot
            const bookings = [
                {
                    spotIndex: selectedSpot,
                    startDate: "2024-08-05T10:00",
                    endDate: "2024-08-05T12:00"
                },
                {
                    spotIndex: selectedSpot,
                    startDate: "2024-08-05T13:00",
                    endDate: "2024-08-05T14:00"
                }
            ];
            setExistingBookings(bookings.filter(booking => booking.spotIndex === selectedSpot));
        }
    }, [selectedSpot]);

    const closeForm = () => {
        setClosing(true);
        setTimeout(() => {
            onClose();
        }, 500); // Delay must match the CSS transition duration
    };

    const handleBookingDetailsSubmit = (ticketData) => {
        const { vehicleType, licensePlate, startDate, endDate } = ticketData;
        const start = new Date(startDate);
        const end = new Date(endDate);
        const hours = (end - start) / 1000 / 3600;
        const amount = (hours * 10).toFixed(2); // Example rate: $10/hour

        const ticket = {
            name: "John Doe", // This should be collected from user input in a real app
            carModel: vehicleType,
            vehicleNumber: licensePlate,
            spotNumber: selectedSpot + 1,
            startDate: startDate,
            endDate: endDate,
            amount: amount
        };

        setTicketInfo(ticket);
    };

    return (
        <div className={`form-container ${closing ? "closing" : ""}`}>
            <div className={`form ${closing ? "closing" : ""}`}>
                <h2>Book a Spot</h2>
                <span>{parkingData?.text}</span>
                <div className="parking-info">
                    <p>Location: {parkingData?.location}</p>
                    <p>Start Time: {parkingData?.startTime}</p>
                    <p>End Time: {parkingData?.endTime}</p>
                    <p>Price: {parkingData?.price}$ - hour</p>
                </div>
                <div className="spots-container">
                    {spots.map((spot, index) => (
                        <div
                            key={index}
                            className={`spot ${spot ? "taken" : ""} ${selectedSpot === index ? "selected" : ""}`}
                            onClick={() => handleSpotClick(index)}
                        >
                            {spot ? <span className="spot-taken">X</span> : <i className="fas fa-car"></i>}
                            <span className="spot-number">{index + 1}</span>
                        </div>
                    ))}
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
                    spotIndex={selectedSpot}
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
