import React, { useState } from 'react';
import { authAPIs, endpoints } from '../../configs/APIs';
import "./UpdateForm.css";
import Swal from 'sweetalert2';

function UpdateForm({ userData, onSave, onCancel }) {
  const [formData, setFormData] = useState({
    firstName: userData.firstName || '',
    lastName: userData.lastName || '',
    phone: userData.phone || '',
    address: userData.address || '',
    avatar: null,
  });
  const [loading, setLoading] = useState(false); // Add loading state

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleFileChange = (e) => {
    setFormData((prevData) => ({
      ...prevData,
      avatar: e.target.files[0],
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const form = new FormData();

    form.append('firstName', formData.firstName);
    form.append('lastName', formData.lastName);
    form.append('phone', formData.phone);
    form.append('address', formData.address);

    if (formData.avatar) {
      form.append('avatar', formData.avatar);
    }

    setLoading(true); // Show loading spinner

    try {
      const response = await authAPIs().post(
        endpoints.updateUser(userData.id),
        form,
        {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        }
      );
      console.log('User updated successfully:', response.data);
      onSave(response.data);

      // Show SweetAlert2 success message
      Swal.fire({
        title: 'Success!',
        text: 'User information updated successfully.',
        icon: 'success',
        confirmButtonText: 'OK'
      });
    } catch (error) {
      console.error('An error occurred while updating the user:', error);

      // Show SweetAlert2 error message
      Swal.fire({
        title: 'Error!',
        text: error.response?.data?.message || 'An error occurred while updating the user. Please try again later.',
        icon: 'error',
        confirmButtonText: 'OK'
      });
    } finally {
      setLoading(false); // Hide loading spinner
    }
  };

  return (
    <div className="update-form-container">
      <form className="update-form" onSubmit={handleSubmit}>
        <h2>Update Account</h2>
        <label>
          First Name:
          <input
            type="text"
            name="firstName"
            value={formData.firstName}
            onChange={handleChange}
            className="form-control"
            disabled={loading}
          />
        </label>
        <label>
          Last Name:
          <input
            type="text"
            name="lastName"
            value={formData.lastName}
            onChange={handleChange}
            className="form-control"
            disabled={loading}
          />
        </label>
        <label>
          Phone:
          <input
            type="text"
            name="phone"
            value={formData.phone}
            onChange={handleChange}
            className="form-control"
            disabled={loading}
          />
        </label>
        <label>
          Address:
          <input
            type="text"
            name="address"
            value={formData.address}
            onChange={handleChange}
            className="form-control"
            disabled={loading}
          />
        </label>
        <label>
          Avatar:
          <input
            type="file"
            name="avatar"
            onChange={handleFileChange}
            className="form-control"
            disabled={loading}
          />
        </label>
        <div className="buttons">
          <button type="submit" className="save-btn" disabled={loading}>
            {loading ? 'Saving...' : 'Save'}
          </button>
          <button type="button" className="cancel-btn" onClick={onCancel} disabled={loading}>
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
}

export default UpdateForm;
