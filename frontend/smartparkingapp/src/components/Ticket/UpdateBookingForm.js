import React, { useState, useEffect } from 'react';
import { authAPIs, endpoints } from '../../configs/APIs';
import Swal from 'sweetalert2';
import './UpdateBookingForm.css';

const UpdateBookingForm = ({ bookingId, onClose, onUpdate }) => {
    const [startTime, setStartTime] = useState('');
    const [endTime, setEndTime] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const fetchBookingDetails = async () => {
            try {
                const response = await authAPIs().get(`${endpoints.bookingInfo}/${bookingId}`);
                setStartTime(response.data.startTime);
                setEndTime(response.data.endTime);
            } catch (error) {
                setErrorMessage('');
            }
        };

        if (bookingId) {
            fetchBookingDetails();
        }
    }, [bookingId]);

    const handleSubmit = async (event) => {
        event.preventDefault();
        setLoading(true);

        try {
            await authAPIs().post(`${endpoints.bookingInfo}/update/${bookingId}`, {
                startTime,
                endTime,
            });
            setLoading(false);

            Swal.fire({
                title: 'Success!',
                text: 'Booking updated successfully.',
                icon: 'success',
                confirmButtonText: 'OK'
            }).then(() => {
                onUpdate();
                onClose();
            });
        } catch (error) {
            setLoading(false);
            setErrorMessage('Failed to update booking.');
        }
    };

    return (
        <div className="update-form">
            <h3>Update Booking</h3>
            {errorMessage && <p className="error">{errorMessage}</p>}
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="startTime">Start Time:</label>
                    <input
                        type="datetime-local"
                        id="startTime"
                        value={startTime}
                        onChange={(e) => setStartTime(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="endTime">End Time:</label>
                    <input
                        type="datetime-local"
                        id="endTime"
                        value={endTime}
                        onChange={(e) => setEndTime(e.target.value)}
                        required
                    />
                </div>
                <button type="submit" className="btn-update btn-u" disabled={loading}>
                    {loading ? 'Updating...' : 'Update'}
                </button>
                <button type="button" className="btn-cancel" onClick={onClose}>Cancel</button>
            </form>
        </div>
    );
};

export default UpdateBookingForm;
