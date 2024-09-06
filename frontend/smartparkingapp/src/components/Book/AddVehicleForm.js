import React, { useState, useEffect } from "react";
import Cookies from 'react-cookies';
import { authAPIs, endpoints } from '../../configs/APIs';
import "./AddVehicleForm.css";

function AddVehicleForm({ onClose, onVehicleAdded }) {
    const [vehiclePlate, setVehiclePlate] = useState('');
    const [vehicleCategoryId, setVehicleCategoryId] = useState(3);
    const [vehicleCategories, setVehicleCategories] = useState([]);
    const [error, setError] = useState('');
    const [userId, setUserId] = useState(null);

    useEffect(() => {
        // Fetch vehicle categories and user data
        const fetchVehicleCategories = async () => {
            const token = Cookies.load('access-token');
            if (!token) {
                setError('Authorization token is missing.');
                return;
            }

            try {
                const response = await authAPIs().get(endpoints.vehicleCategories);
                setVehicleCategories(response.data);
            } catch (error) {
                setError(`Failed to fetch vehicle categories: ${error.response?.data?.message || 'Unknown error'}`);
            }
        };

        const fetchUserData = async () => {
            const token = Cookies.load('access-token');
            if (!token) {
                setError('Authorization token is missing.');
                return;
            }

            try {
                const response = await authAPIs().get(endpoints['current-user']);
                setUserId(response.data.id);
            } catch (error) {
                setError(`Failed to fetch user data: ${error.response?.data?.message || 'Unknown error'}`);
            }
        };

        fetchVehicleCategories();
        fetchUserData();
    }, []);

    const handleVehicleSubmit = async (e) => {
        e.preventDefault();
        const token = Cookies.load('access-token');
        if (!token) {
            setError('Authorization token is missing.');
            return;
        }

        if (userId === null) {
            setError('User ID is missing.');
            return;
        }

        try {
            const response = await authAPIs().post(endpoints.addVehicle, {
                plateNumber: vehiclePlate,
                userId,
                vehicleCategoryId
            });

            if (response.status === 200) {
                alert("Vehicle added successfully!");
                const newVehicle = response.data;  // Assuming the response contains the newly added vehicle data

                // Notify parent component with the new vehicle data
                onVehicleAdded(newVehicle);
                onClose(); // Close the form
            }
        } catch (error) {
            setError(`Failed to add vehicle: ${error.response?.data?.message || 'Unknown error'}`);
        }
    };


    return (
        <div className="vehicle-form">
            <h3>Add a Vehicle</h3>
            <form onSubmit={handleVehicleSubmit}>
                <label>
                    Plate Number:
                    <input
                        type="text"
                        value={vehiclePlate}
                        onChange={(e) => setVehiclePlate(e.target.value)}
                        required
                    />
                </label>
                <label>
                    Vehicle Category:
                    <select
                        value={vehicleCategoryId}
                        onChange={(e) => setVehicleCategoryId(Number(e.target.value))}
                        required
                    >
                        {vehicleCategories.map(category => (
                            <option key={category.id} value={category.id}>
                                {category.name}
                            </option>
                        ))}
                    </select>
                </label>
                {error && <p className="error">{error}</p>}
                <button type="submit">Submit</button>
                <button type="button" onClick={onClose}>Cancel</button>
            </form>
        </div>
    );
}

export default AddVehicleForm;
