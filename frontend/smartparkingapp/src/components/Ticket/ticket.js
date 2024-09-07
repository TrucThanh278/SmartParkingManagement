import React, { useState, useEffect } from "react";
import { authAPIs, endpoints } from "../../configs/APIs";
import Swal from 'sweetalert2'; 
import "./Ticket.css";
import Header from '../Layout/Header';
import UpdateBookingForm from './UpdateBookingForm';

const Ticket = () => {
    const [bookingInfo, setBookingInfo] = useState([]);
    const [errorMessage, setErrorMessage] = useState("");
    const [selectedBookingId, setSelectedBookingId] = useState(null);
    const [loading, setLoading] = useState(false); 

    useEffect(() => {
        const fetchBookingInfo = async () => {
            setLoading(true);
            try {
                const response = await authAPIs().get(endpoints.bookingInfo);
                setBookingInfo(response.data);
                setErrorMessage("");
            } catch (error) {
                setErrorMessage("Failed to fetch booking info.");
                Swal.fire('Error', 'Failed to fetch booking info.', 'error');
            } finally {
                setLoading(false);
            }
        };

        fetchBookingInfo();
    }, []);

    const handleUpdateClick = (bookingId) => {
        setSelectedBookingId(bookingId); 
    };

    const handleDeleteClick = async (bookingId) => {
        setLoading(true);
        try {
            await authAPIs().delete(`${endpoints.bookingInfo}/delete/${bookingId}`);
            Swal.fire('Deleted!', 'Booking has been deleted.', 'success');
            const response = await authAPIs().get(endpoints.bookingInfo);
            setBookingInfo(response.data);
            setErrorMessage("");
        } catch (error) {
            setErrorMessage("Failed to delete booking.");
            Swal.fire('Error', 'Failed to delete booking.', 'error');
        } finally {
            setLoading(false);
        }
    };

    const handleUpdateClose = () => {
        setSelectedBookingId(null); 
    };

    const handleUpdateSuccess = () => {
        setSelectedBookingId(null);
        const fetchBookingInfo = async () => {
            setLoading(true);
            try {
                const response = await authAPIs().get(endpoints.bookingInfo);
                setBookingInfo(response.data);
                setErrorMessage("");
            } catch (error) {
                setErrorMessage("Failed to fetch booking info.");
                Swal.fire('Error', 'Failed to fetch booking info.', 'error');
            } finally {
                setLoading(false);
            }
        };
        fetchBookingInfo();
    };

    return (
        <div className="ticket">
            <Header />
            <div className="ticket-container">
                <h2>Your Bookings</h2>
                {loading && <p>Loading...</p>} 
                {errorMessage && <p className="error">{errorMessage}</p>}
                {bookingInfo.length > 0 ? (
                    <ul>
                        {bookingInfo.map((booking) => (
                            <li key={booking.id} className="ticket-item">
                                <p>Parking Spot: {booking.parkingSpotId.spotNumber}</p>
                                <p>Start Time: {new Date(booking.startTime).toLocaleString()}</p>
                                <p>End Time: {new Date(booking.endTime).toLocaleString()}</p>
                                <button onClick={() => handleUpdateClick(booking.id)} className="btn-update">
                                    Update
                                </button>
                                <button onClick={() => handleDeleteClick(booking.id)} className="btn-delete">
                                    Delete
                                </button>
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>No bookings found.</p>
                )}
            </div>

            {selectedBookingId && (
                <UpdateBookingForm
                    bookingId={selectedBookingId}
                    onClose={handleUpdateClose}
                    onUpdate={handleUpdateSuccess}
                />
            )}
        </div>
    );
};

export default Ticket;
