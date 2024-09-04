
// import React, { useState, useEffect } from "react";
// import "./BookTicket.css";
// import Ticket from '../Ticket/ticket';

// function BookTicket({ spotIndex, onClose }) {
//     const [vehicleType, setVehicleType] = useState("");
//     const [licensePlate, setLicensePlate] = useState("");
//     const [startDate, setStartDate] = useState("");
//     const [endDate, setEndDate] = useState("");
//     const [ticketInfo, setTicketInfo] = useState(null);
//     const [existingBookings, setExistingBookings] = useState([]);
//     const [errorMessage, setErrorMessage] = useState("");

//     useEffect(() => {
//         // Fetch existing bookings for the spot from the server or state
//         // This is just an example, replace with actual data fetching logic
//         const fetchExistingBookings = async () => {
//             const bookings = [
//                 {
//                     startDate: "2024-08-05T10:00",
//                     endDate: "2024-08-05T12:00"
//                 },
//                 {
//                     startDate: "2024-08-05T13:00",
//                     endDate: "2024-08-05T14:00"
//                 }
//             ];
//             setExistingBookings(bookings);
//         };

//         fetchExistingBookings();
//     }, [spotIndex]);

//     const checkForConflicts = (newStart, newEnd) => {
//         return existingBookings.some(booking => {
//             const existingStart = new Date(booking.startDate);
//             const existingEnd = new Date(booking.endDate);
//             return (newStart < existingEnd && newEnd > existingStart);
//         });
//     };

//     const handleSubmit = (e) => {
//         e.preventDefault();
//         const start = new Date(startDate);
//         const end = new Date(endDate);

//         if (checkForConflicts(start, end)) {
//             setErrorMessage("The selected time slot is already booked.");
//             return;
//         }

//         setErrorMessage("");
//         const ticket = {
//             name: "John Doe", // This should be collected from user input in a real app
//             carModel: vehicleType,
//             vehicleNumber: licensePlate,
//             spotNumber: spotIndex + 1,
//             startDate: startDate,
//             endDate: endDate,
//             amount: calculateAmount(start, end)
//         };
//         setTicketInfo(ticket);
//     };

//     const calculateAmount = (start, end) => {
//         const hours = (end - start) / 1000 / 3600;
//         return (hours * 10).toFixed(2); // Example rate: $10/hour
//     };

//     return (
//         <div>
//             {!ticketInfo ? (
//                 <div className="booking-details-container">
//                     <div className="booking-details-form">
//                         <h2>Booking Details</h2>
//                         <div className="book-detail-flex">
//                             <form onSubmit={handleSubmit}>
//                                 <label>
//                                     Parking Spot Number:
//                                     <input type="text" value={`Spot ${spotIndex + 1}`} readOnly />
//                                 </label>
//                                 <label>
//                                     Vehicle Type:
//                                     <input
//                                         type="text"
//                                         value={vehicleType}
//                                         onChange={(e) => setVehicleType(e.target.value)}
//                                         required
//                                     />
//                                 </label>
//                                 <label>
//                                     License Plate:
//                                     <input
//                                         type="text"
//                                         value={licensePlate}
//                                         onChange={(e) => setLicensePlate(e.target.value)}
//                                         required
//                                     />
//                                 </label>
//                                 <label>
//                                     Start Date and Time:
//                                     <input
//                                         type="datetime-local"
//                                         value={startDate}
//                                         onChange={(e) => setStartDate(e.target.value)}
//                                         required
//                                     />
//                                 </label>
//                                 <label>
//                                     End Date and Time:
//                                     <input
//                                         type="datetime-local"
//                                         value={endDate}
//                                         onChange={(e) => setEndDate(e.target.value)}
//                                         required
//                                     />
//                                 </label>
//                                 {errorMessage && <p className="error-message">{errorMessage}</p>}
//                                 <button type="submit">Submit</button>
//                             </form>
//                             <div className="existing-bookings">
//                                 <h3>Existing Bookings:</h3>
//                                 {existingBookings.map((booking, index) => (
//                                     <div key={index}>
//                                         <p>Start: {booking.startDate}</p>
//                                         <p>End: {booking.endDate}</p>
//                                     </div>
//                                 ))}
//                             </div>
//                         </div>

//                         <button className="close-btn" onClick={onClose}>
//                             <i className="fas fa-times"></i>
//                         </button>

//                     </div>
//                 </div>
//             ) : (
//                 <Ticket ticketInfo={ticketInfo} />
//             )}
//         </div>
//     );
// }

// export default BookTicket;

import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./BookTicket.css";

function BookTicket({ spotIndex, existingBookings, onClose, price }) {
    const [vehicleType, setVehicleType] = useState("");
    const [licensePlate, setLicensePlate] = useState("");
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const navigate = useNavigate();

    const checkForConflicts = (newStart, newEnd) => {
        return existingBookings.some(booking => {
            const existingStart = new Date(booking.startDate);
            const existingEnd = new Date(booking.endDate);
            return (newStart < existingEnd && newEnd > existingStart);
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const start = new Date(startDate);
        const end = new Date(endDate);

        if (isNaN(start) || isNaN(end)) {
            setErrorMessage("Please enter valid start and end dates.");
            return;
        }

        if (checkForConflicts(start, end)) {
            setErrorMessage("The selected time slot is already booked.");
            return;
        }

        setErrorMessage("");
        const ticket = {
            name: "John Doe", // This should be collected from user input in a real app
            carModel: vehicleType,
            vehicleNumber: licensePlate,
            spotNumber: spotIndex + 1,
            startDate: startDate,
            endDate: endDate,
            amount: calculateAmount(start, end)
        };

        // Save ticket to local storage
        localStorage.setItem("ticketInfo", JSON.stringify(ticket));

        // Navigate to the Ticket page with the ticket info
        navigate('/ticket');
    };

    const calculateAmount = (start, end) => {
        const hours = (end - start) / 1000 / 3600;
        return (hours * price).toFixed(2); // Use the price prop for the hourly rate
    };

    return (
        <div className="booking-details-container">
            <div className="booking-details-form">
                <h2>Booking Details</h2>
                <div className="book-detail-flex">
                    <form onSubmit={handleSubmit}>
                        <label>
                            Parking Spot Number:
                            <input type="text" value={`Spot ${spotIndex + 1}`} readOnly />
                        </label>
                        <label>
                            Vehicle Type:
                            <input
                                type="text"
                                value={vehicleType}
                                onChange={(e) => setVehicleType(e.target.value)}
                                required
                            />
                        </label>
                        <label>
                            License Plate:
                            <input
                                type="text"
                                value={licensePlate}
                                onChange={(e) => setLicensePlate(e.target.value)}
                                required
                            />
                        </label>
                        <label>
                            Start Date and Time:
                            <input
                                type="datetime-local"
                                value={startDate}
                                onChange={(e) => setStartDate(e.target.value)}
                                required
                            />
                        </label>
                        <label>
                            End Date and Time:
                            <input
                                type="datetime-local"
                                value={endDate}
                                onChange={(e) => setEndDate(e.target.value)}
                                required
                            />
                        </label>
                        {errorMessage && <div className="alert">{errorMessage}</div>}
                        <button type="submit">Submit</button>
                    </form>
                    <div className="existing-bookings">
                        <h3>Existing Bookings:</h3>
                        {existingBookings.map((booking, index) => (
                            <div key={index}>
                                <p>Start: {booking.startDate}</p>
                                <p>End: {booking.endDate}</p>
                            </div>
                        ))}
                    </div>
                </div>

                <button className="close-btn" onClick={onClose}>
                    <i className="fas fa-times"></i>
                </button>
            </div>
        </div>
    );
}

export default BookTicket;
