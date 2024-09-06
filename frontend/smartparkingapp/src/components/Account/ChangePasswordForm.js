import React, { useState } from 'react';
import axios from '../../configs/APIs';
import { endpoints } from '../../configs/APIs';
import "./ChangePasswordForm.css";
import cookie from 'react-cookies';
import Swal from 'sweetalert2';

function ChangePasswordForm({ userId, onCancel, onPasswordChange }) {
    const [oldPassword, setOldPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");
    const [loading, setLoading] = useState(false); 

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");
        setSuccess("");
        setLoading(true); 

        if (newPassword !== confirmPassword) {
            setError("New passwords do not match");
            setLoading(false);
            return;
        }

        try {
            const token = cookie.load('access-token');
            console.log('Token:', token);

            if (!token) {
                setError('No token found');
                setLoading(false); 
                return;
            }

            const response = await axios.post(
                endpoints.changePassword(userId),
                {
                    oldPassword,
                    newPassword,
                },
                {
                    headers: {
                        Authorization: `${token}`,
                        'Content-Type': 'application/json',
                    },
                }
            );

            console.log(response);
            setSuccess("Password changed successfully!");
            onPasswordChange();


            Swal.fire({
                title: 'Success!',
                text: 'Your password has been changed successfully.',
                icon: 'success',
                confirmButtonText: 'OK'
            });

            // Clear the form fields
            setOldPassword("");
            setNewPassword("");
            setConfirmPassword("");
        } catch (error) {
            console.error("Failed to change password", error);
            setError(
                error.response?.data?.message || "An error occurred while changing the password"
            );

            // Show SweetAlert2 error message
            Swal.fire({
                title: 'Error!',
                text: error.response?.data?.message || 'An error occurred while changing the password. Please try again later.',
                icon: 'error',
                confirmButtonText: 'OK'
            });
        } finally {
            setLoading(false); // Hide loading spinner
        }
    };

    return (
        <div className="change-password-form">
            <h2>Change Password</h2>
            {error && <p className="error">{error}</p>}
            {success && <p className="success">{success}</p>}
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="oldPassword">Old Password</label>
                    <input
                        type="password"
                        id="oldPassword"
                        value={oldPassword}
                        onChange={(e) => setOldPassword(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="newPassword">New Password</label>
                    <input
                        type="password"
                        id="newPassword"
                        value={newPassword}
                        onChange={(e) => setNewPassword(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="confirmPassword">Confirm New Password</label>
                    <input
                        type="password"
                        id="confirmPassword"
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                        required
                    />
                </div>
                <div className="button-group">
                    <button type="submit" className="btnChange" disabled={loading}>
                        {loading ? 'Changing...' : 'Change'}
                    </button>
                    <button type="button" className="btnCancel" onClick={onCancel}>
                        Cancel
                    </button>
                </div>
            </form>
        </div>
    );
}

export default ChangePasswordForm;
