import React, { useState } from 'react';
import { authAPIs, endpoints } from '../../configs/APIs';
import "./UpdateForm.css";

function UpdateForm({ userData, onSave, onCancel }) {
  const [formData, setFormData] = useState({
    firstName: userData.firstName || '',
    lastName: userData.lastName || '',
    phone: userData.phone || '',
    address: userData.address || '',
    avatar: null,
  });

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
    } catch (error) {
      console.error('An error occurred while updating the user:', error);
    }
  };

  return (
    <div className="update-form-container">
      <form className="update-form" onSubmit={handleSubmit}>
        <h2>Update Account</h2>
        <label>
          First Name:
          <input type="text"
            name="firstName"
            value={formData.firstName}
            onChange={handleChange}
            className="form-control" />
        </label>
        <label>
          Last Name:
          <input type="text"
            name="lastName"
            value={formData.lastName}
            onChange={handleChange}
            className="form-control" />
        </label>
        <label>
          Phone:
          <input type="text"
            name="phone"
            value={formData.phone}
            onChange={handleChange}
            className="form-control" />
        </label>
        <label>
          Address:
          <input
            type="text"
            name="address"
            value={formData.address}
            onChange={handleChange}
            className="form-control"
          />
        </label>
        <label>
          Avatar:
          <input type="file"
            name="avatar"
            onChange={handleFileChange}
            className="form-control" />
        </label>
        <div className="buttons">
          <button type="submit" className="save-btn">Save</button>
          <button type="button" className="cancel-btn" onClick={onCancel}>Cancel</button>
        </div>
      </form>
    </div>
  );
}

export default UpdateForm;
