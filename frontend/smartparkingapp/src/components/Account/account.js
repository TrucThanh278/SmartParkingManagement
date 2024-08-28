import React, { useState, useEffect } from "react";
import Header from '../Layout/Header';
import UpdateForm from './UdateForm';
import { authAPIs, endpoints } from "../../configs/APIs"; // Adjust the path if needed
import "./account.css";

function Account() {
  const [userData, setUserData] = useState(null);
  const [isEditing, setIsEditing] = useState(false);

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const response = await authAPIs().get(endpoints['current-user']);
        const { firstName, lastName, ...otherData } = response.data;
        setUserData({
          ...otherData,
          name: `${firstName} ${lastName}`
        });
      } catch (error) {
        console.error("Failed to fetch user data", error);
      }
    };

    fetchUserData();
  }, []);

  const handleUpdateClick = () => {
    setIsEditing(true);
  };

  const handleSave = (updatedData) => {
    const { firstName, lastName, ...otherData } = updatedData;
    setUserData({
      ...otherData,
      name: `${firstName} ${lastName}`
    });
    setIsEditing(false);
  };

  const handleCancel = () => {
    setIsEditing(false);
  };

  return (
    <div className="account">
      <Header />
      {userData ? (
        <div className="profile">
          <img src={userData.avatar || "https://via.placeholder.com/150"} alt="Avatar" className="avatar" />
          <p>{userData.name}</p>
          <p>Email: {userData.email}</p>
          <p>Phone: {userData.phone}</p>
          <p>Address: {userData.address}</p>
          <button className="update-btn" onClick={handleUpdateClick}>Update</button>
        </div>
      ) : (
        <p>Loading user data...</p>
      )}
      {isEditing && (
        <UpdateForm
          userData={userData}
          onSave={handleSave}
          onCancel={handleCancel}
        />
      )}
    </div>
  );
}

export default Account;
