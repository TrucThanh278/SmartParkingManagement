import React, { useState } from 'react';
import axios from '../../configs/APIs';
import { endpoints } from '../../configs/APIs';
import "./ChangePasswordForm.css";
import cookie from 'react-cookies';

function ChangePasswordForm({ userId, onCancel, onPasswordChange }) {
    const [oldPassword, setOldPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");
        setSuccess("");

        if (newPassword !== confirmPassword) {
            setError("New passwords do not match");
            return;
        }

        try {
            const token = cookie.load('access-token');
            console.log('Token:', token);

            if (!token) {
                setError('No token found');
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
        } catch (error) {
            console.error("Failed to change password", error);
            setError(
                error.response?.data || "An error occurred while changing the password"
            );
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
                    <button type="submit" className="btnChange">Change</button>
                    <button type="button" className="btnCancel" onClick={onCancel}>
                        Cancel
                    </button>
                </div>
            </form>
        </div>
    );
}

export default ChangePasswordForm;
