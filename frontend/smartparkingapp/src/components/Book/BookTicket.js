import React, { useState, useEffect } from "react";
import Cookies from 'react-cookies';
import { authAPIs, endpoints } from '../../configs/APIs';
import "./BookTicket.css";
import AddVehicleForm from './AddVehicleForm';
import { useNavigate } from 'react-router-dom'; // Import useNavigate

function BookTicket({ spotIndex, spotId, onClose, price }) {
    const [vehicleType, setVehicleType] = useState("");
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [vehicles, setVehicles] = useState([]);
    const [existingBookings, setExistingBookings] = useState([]);
    const [errorMessage, setErrorMessage] = useState("");
    const [showAddVehicleForm, setShowAddVehicleForm] = useState(false);
    const [userId, setUserId] = useState(null);

    const navigate = useNavigate(); // Initialize navigate

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

        fetchUserData(); // Fetch user data to get userId
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

    const formatDate = (date) => {
        const d = new Date(date);
        const year = d.getFullYear();
        const month = String(d.getMonth() + 1).padStart(2, '0');
        const day = String(d.getDate()).padStart(2, '0');
        const hours = String(d.getHours()).padStart(2, '0');
        const minutes = String(d.getMinutes()).padStart(2, '0');
        return `${year}-${month}-${day} ${hours}:${minutes}:00`;
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

        const start = formatDate(startDate);
        const end = formatDate(endDate);

        if (isNaN(new Date(start)) || isNaN(new Date(end))) {
            setErrorMessage("Please enter valid start and end dates.");
            return;
        }

        if (checkForConflicts(new Date(start), new Date(end))) {
            setErrorMessage("The selected time slot is already booked.");
            return;
        }

        setErrorMessage("");

        try {
            const response = await authAPIs().post(endpoints.createBooking, {
                startTime: start,
                endTime: end,
                parkingSpotId: spotId,
                vehicleId: vehicleType
            });

            if (response.status === 200) {
                alert("Booking successful!");
                const ticket = {
                    carModel: vehicleType,
                    spotNumber: spotIndex,
                    spotId: spotId,
                    startDate: startDate,
                    endDate: endDate,
                    amount: calculateAmount(new Date(start), new Date(end))
                };
                navigate('/ticket', { state: { ticketInfo: ticket } }); // Use navigate for routing
            } else {
                const errorData = await response.data;
                setErrorMessage(`Failed to book: ${errorData.message || 'Unknown error'}`);
            }
        } catch (error) {
            setErrorMessage("An error occurred while booking.");
        }
    };

    const calculateAmount = (start, end) => {
        const hours = (end - start) / 1000 / 3600;
        return (hours * price).toFixed(2);
    };

    return (
        <div className="booking-details-container">
            <div className="booking-details-form">
                <h2>Booking Details for Spot {spotIndex}</h2>
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
                                <p>Start: {new Date(booking.startTime).toLocaleString()}</p>
                                <p>End: {new Date(booking.endTime).toLocaleString()}</p>
                            </div>
                        ))}
                    </div>
                </div>
                <button className="close-btn" onClick={onClose}>
                    <i className="fas fa-times"></i>
                </button>
            </div>

            {showAddVehicleForm && (
                <AddVehicleForm
                    onClose={() => setShowAddVehicleForm(false)}
                    onVehicleAdded={(newVehicle) => {
                        setVehicles((prevVehicles) => [...prevVehicles, newVehicle]);
                        setVehicleType(newVehicle.id);
                        setShowAddVehicleForm(false);
                    }}
                />
            )}
        </div>
    );
}

export default BookTicket;
