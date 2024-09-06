import React, { useState, useEffect } from "react";
import Header from '../Layout/Header';
import UpdateForm from './UpdateForm';
import ChangePasswordForm from './ChangePasswordForm';
import { authAPIs, endpoints } from "../../configs/APIs";
import "./account.css";

function Account() {
  const [userData, setUserData] = useState(null);
  const [isEditing, setIsEditing] = useState(false);
  const [isChangingPassword, setIsChangingPassword] = useState(false);

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const response = await authAPIs().get(endpoints['current-user']);
        console.log("User Data from API:", response.data);

        const { id, firstName, lastName, avatar, email, phone, address } = response.data;
        setUserData({
          id,
          firstName,
          lastName,
          avatar,
          email,
          phone,
          address
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

  const handlePasswordChangeClick = () => {
    setIsChangingPassword(true);
  };

  const handleSave = (updatedData) => {
    console.log("Updated Data:", updatedData);
    const { firstName, lastName, avatar, email, phone, address } = updatedData;

    setUserData({
      id: userData.id,
      firstName,
      lastName,
      avatar,
      email,
      phone,
      address,
      name: `${firstName} ${lastName}`
    });

    setIsEditing(false);
  };

  const handleCancel = () => {
    setIsEditing(false);
    setIsChangingPassword(false);
  };

  return (
    <div className="account">
      <Header />
      {!isEditing && !isChangingPassword && userData && (
        <div className="profile">
          <img src={userData.avatar || "https://via.placeholder.com/150"} alt="Avatar" className="avatar" />
          <p className="profileName">{userData.firstName} {userData.lastName}</p>
          <p>Email: {userData.email}</p>
          <p>Phone: {userData.phone}</p>
          <p>Address: {userData.address}</p>
          <button className="update-btn" onClick={handleUpdateClick}>Update</button>
          <button className="change-password-btn" onClick={handlePasswordChangeClick}>Change Password</button>
        </div>
      )}
      {isEditing && (
        <UpdateForm
          userData={userData}
          onSave={handleSave}
          onCancel={handleCancel}
        />
      )}
      {isChangingPassword && (
        <ChangePasswordForm
          userId={userData.id}
          onCancel={handleCancel}
          onPasswordChange={() => setIsChangingPassword(false)}
        />
      )}
    </div>
  );
}

export default Account;
