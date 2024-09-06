import React, { useState, useEffect } from "react";
import Cookies from 'react-cookies';
import { authAPIs, endpoints } from '../../configs/APIs';
import Swal from 'sweetalert2';
import "./AddVehicleForm.css";

function AddVehicleForm({ onClose, onVehicleAdded }) {
    const [vehiclePlate, setVehiclePlate] = useState('');
    const [vehicleCategoryId, setVehicleCategoryId] = useState(3);
    const [vehicleCategories, setVehicleCategories] = useState([]);
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);
    const [userId, setUserId] = useState(null);

    useEffect(() => {
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
            setLoading(true);

            const response = await authAPIs().post(endpoints.addVehicle, {
                plateNumber: vehiclePlate,
                userId,
                vehicleCategoryId
            });

            if (response.status === 200) {
                const newVehicle = response.data;

                Swal.fire({
                    title: 'Success!',
                    text: 'Vehicle added successfully!',
                    icon: 'success',
                    confirmButtonText: 'OK'
                });

                onVehicleAdded(newVehicle);
                onClose();
            }
        } catch (error) {
            setError(`Failed to add vehicle: ${error.response?.data?.message || 'Unknown error'}`);
        } finally {
            setLoading(false);
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
                <div className="btn-flex">
                    <button type="submit" disabled={loading}>
                        {loading ? 'Submitting...' : 'Submit'}
                    </button>
                    <button type="button" onClick={onClose} disabled={loading}>
                        Cancel
                    </button>
                </div>
            </form>
        </div>
    );
}

export default AddVehicleForm;
