import React, { useState, useEffect } from "react";
import Cookies from 'react-cookies';
import { authAPIs, endpoints } from '../../configs/APIs';
import "./BookTicket.css";
import AddVehicleForm from './AddVehicleForm';
import PaymentForm from './PaymentForm';
import { useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';

function BookTicket({ spotIndex, spotId, onClose, price }) {
    const [vehicleType, setVehicleType] = useState("");
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [vehicles, setVehicles] = useState([]);
    const [existingBookings, setExistingBookings] = useState([]);
    const [errorMessage, setErrorMessage] = useState("");
    const [showAddVehicleForm, setShowAddVehicleForm] = useState(false);
    const [userId, setUserId] = useState(null);
    const [loading, setLoading] = useState(false);
    const [showPaymentForm, setShowPaymentForm] = useState(false);

    const navigate = useNavigate();

    useEffect(() => {
        const fetchUserData = async () => {
            const token = Cookies.load('access-token');
            if (!token) {
                setErrorMessage('Authorization token is missing.');
                return;
            }

            try {
                const response = await authAPIs().get(endpoints['current-user']);
                setUserId(response.data.id);
            } catch (error) {
                setErrorMessage(`Failed to fetch user data: ${error.response?.data?.message || 'Unknown error'}`);
            }
        };

        fetchUserData();
    }, []);

    useEffect(() => {
        if (userId !== null) {
            const fetchVehicles = async () => {
                const token = Cookies.load('access-token');
                if (!token) {
                    setErrorMessage('Authorization token is missing.');
                    return;
                }

                try {
                    const response = await authAPIs().get(endpoints.vehiclesForUser(userId));
                    setVehicles(response.data);
                } catch (error) {
                    setErrorMessage(`Failed to fetch vehicles: ${error.response?.data?.message || 'Unknown error'}`);
                }
            };

            const fetchExistingBookings = async () => {
                const token = Cookies.load('access-token');
                if (!token) {
                    setErrorMessage('Authorization token is missing.');
                    return;
                }

                try {
                    const response = await authAPIs().get(endpoints.existingBookingsForSpot(spotId));
                    setExistingBookings(response.data);
                } catch (error) {
                    setErrorMessage(`Failed to fetch existing bookings: ${error.response?.data?.message || 'Unknown error'}`);
                }
            };

            fetchVehicles();
            fetchExistingBookings();
        }
    }, [userId, spotId]);

    const formatDateTimeForInput = (date) => {
        const d = new Date(date);
        return d.toISOString().slice(0, 16); // Format to YYYY-MM-DDTHH:MM
    };

    const checkForConflicts = (newStart, newEnd) => {
        return existingBookings.some(booking => {
            const existingStart = new Date(booking.startTime);
            const existingEnd = new Date(booking.endTime);
            return (newStart < existingEnd && newEnd > existingStart);
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const token = Cookies.load('access-token');
        if (!token) {
            setErrorMessage('Authorization token is missing.');
            return;
        }

        if (userId === null) {
            setErrorMessage('User ID is missing.');
            return;
        }

        const start = new Date(startDate).toISOString();
        const end = new Date(endDate).toISOString();

        if (isNaN(new Date(start)) || isNaN(new Date(end))) {
            setErrorMessage("Please enter valid start and end dates.");
            return;
        }

        if (checkForConflicts(new Date(start), new Date(end))) {
            setErrorMessage("The selected time slot is already booked.");
            return;
        }

        setErrorMessage("");
        setLoading(true);

        try {
            const response = await authAPIs().post(endpoints.createBooking, {
                startTime: start,
                endTime: end,
                parkingSpotId: spotId,
                vehicleId: vehicleType
            });

            if (response.status === 200) {
                setLoading(false);
                setShowPaymentForm(true);

                Swal.fire({
                    title: 'Booking Successful!',
                    text: 'Your parking spot has been booked successfully. Please proceed with payment.',
                    icon: 'success',
                    confirmButtonText: 'Proceed to Payment'
                });
            } else {
                const errorData = await response.data;
                setErrorMessage(`Failed to book: ${errorData.message || 'Unknown error'}`);
                setLoading(false);
            }
        } catch (error) {
            setLoading(false);
            if (error.response && error.response.status === 500) {
                Swal.fire({
                    title: 'Booking Failed',
                    text: errorMessage.includes("outside business hours")
                        ? "Booking failed: Outside business hours."
                        : errorMessage.includes("time slot conflict")
                            ? "Booking failed: Time slot conflict."
                            : "An error occurred while booking. Please try again later.",
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            }
        }
    };

    return (
        <div className="booking-details-container">
            <div className="booking-details-form">
                <h2 className="name-book-details">Booking Details for Spot {spotIndex}</h2>
                <div className="book-detail-flex">
                    <form onSubmit={handleSubmit}>
                        <label>
                            Parking Spot Number:
                            <input type="text" value={`Spot ${spotIndex}`} readOnly />
                        </label>

                        <label>
                            Vehicle Type:
                            <div className="vehicle-select-container">
                                <select
                                    value={vehicleType}
                                    onChange={(e) => setVehicleType(e.target.value)}
                                    required
                                >
                                    <option value="">Select a vehicle</option>
                                    {vehicles.map(vehicle => (
                                        <option key={vehicle.id} value={vehicle.id}>
                                            {vehicle.plateNumber}
                                        </option>
                                    ))}
                                </select>
                                <button
                                    type="button"
                                    onClick={() => setShowAddVehicleForm(true)}
                                    className="add-vehicle-btn"
                                >
                                    Add Vehicle
                                </button>
                            </div>
                        </label>

                        <label>
                            Start Date and Time:
                            <input
                                type="datetime-local"
                                value={startDate ? formatDateTimeForInput(startDate) : ''}
                                onChange={(e) => setStartDate(e.target.value)}
                                required
                            />
                        </label>
                        <label>
                            End Date and Time:
                            <input
                                type="datetime-local"
                                value={endDate ? formatDateTimeForInput(endDate) : ''}
                                onChange={(e) => setEndDate(e.target.value)}
                                required
                            />
                        </label>
                        {errorMessage && <div className="alert">{errorMessage}</div>}
                        {loading ? <p>Loading...</p> : <button type="submit">Submit</button>}
                    </form>
                    <form className="existing-bookings">
                        <h3>Existing Bookings:</h3>
                        {existingBookings.map((booking, index) => (
                            <div key={index}>
                                <p>Start: {new Date(booking.startTime).toLocaleString()}</p>
                                <p>End: {new Date(booking.endTime).toLocaleString()}</p>
                                <p>Vehicle: {booking.vehicle?.plateNumber || 'N/A'}</p>
                            </div>
                        ))}
                    </form>
                </div>
                {showAddVehicleForm && (
                    <AddVehicleForm
                        onClose={() => setShowAddVehicleForm(false)}
                        onAddVehicle={(newVehicle) => {
                            setVehicles([...vehicles, newVehicle]);
                            setShowAddVehicleForm(false);
                        }}
                    />
                )}
                <button className="close-btn" onClick={onClose}>
                    <i className="fas fa-times"></i>
                </button>
            </div>
            {showPaymentForm && (
                <PaymentForm
                    price={price}
                    onClose={() => setShowPaymentForm(false)}
                />
            )}

        </div>
    );
}

export default BookTicket;
