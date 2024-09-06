import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faHome, faPhone, faEnvelope, faLock, faImage } from '@fortawesome/free-solid-svg-icons';
import { Alert, Spinner } from 'react-bootstrap';
import Swal from 'sweetalert2';
import APIs, { endpoints } from "../../configs/APIs";
import "./signup.css";

function SignUp() {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    username: '',
    address: '',
    phone: '',
    email: '',
    password: '',
    confirmPassword: '',
    avatar: null
  });

  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const register = async (e) => {
    e.preventDefault();

    if (formData.password !== formData.confirmPassword) {
      setError("Passwords do not match!");
      return;
    }

    try {
      setLoading(true);
      let form = new FormData();
      Object.keys(formData).forEach(key => {
        if (formData[key] !== null) {
          form.append(key, formData[key]);
        }
      });

      console.log("Sending form data:", formData);

      let res = await APIs.post(endpoints['signup'], form, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });

      if (res.status === 200) {
        console.log("Navigating to /signin");

        Swal.fire({
          title: 'Success!',
          text: 'You have successfully registered!',
          icon: 'success',
          confirmButtonText: 'OK'
        }).then(() => {
          navigate("/signin");
        });

      } else if (res.status === 409) {
        setError("Email already exists. Please choose another email.");
      } else {
        setError("Failed to register. Please try again.");
      }
    } catch (error) {
      console.error("Registration error:", error.response || error);
      setError("An error occurred during registration. Please try again later.");
    } finally {
      setLoading(false);
    }
  };

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    setFormData({
      ...formData,
      [name]: files ? files[0] : value
    });
  };

  return (
    <div className="container-signup">
      <h2 className="signUp">Sign Up</h2>
      {error && <Alert variant="danger">{error}</Alert>}
      <form onSubmit={register} method="post">
        <div className="form-group">
          <label className="label" htmlFor="username">Username</label>
          <div className="input-container">
            <FontAwesomeIcon icon={faUser} className="icon" />
            <input
              className="input"
              type="text"
              id="username"
              name="username"
              value={formData.username}
              onChange={handleChange}
              required
            />
          </div>
        </div>

        <div className="form-group-row">
          <div className="form-group">
            <label className="label" htmlFor="firstName">First Name</label>
            <div className="input-container">
              <FontAwesomeIcon icon={faUser} className="icon" />
              <input
                className="input"
                type="text"
                id="firstName"
                name="firstName"
                value={formData.firstName}
                onChange={handleChange}
                required
              />
            </div>
          </div>
          <div className="form-group">
            <label className="label" htmlFor="lastName">Last Name</label>
            <div className="input-container">
              <FontAwesomeIcon icon={faUser} className="icon" />
              <input
                className="input"
                type="text"
                id="lastName"
                name="lastName"
                value={formData.lastName}
                onChange={handleChange}
                required
              />
            </div>
          </div>
        </div>

        <div className="form-group-row">
          <div className="form-group">
            <label className="label" htmlFor="address">Address</label>
            <div className="input-container">
              <FontAwesomeIcon icon={faHome} className="icon" />
              <input
                className="input"
                type="text"
                id="address"
                name="address"
                value={formData.address}
                onChange={handleChange}
                required
              />
            </div>
          </div>
          <div className="form-group">
            <label className="label" htmlFor="phone">Phone</label>
            <div className="input-container">
              <FontAwesomeIcon icon={faPhone} className="icon" />
              <input
                className="input"
                type="text"
                id="phone"
                name="phone"
                value={formData.phone}
                onChange={handleChange}
                required
              />
            </div>
          </div>
        </div>

        <div className="form-group">
          <label className="label" htmlFor="email">Email</label>
          <div className="input-container">
            <FontAwesomeIcon icon={faEnvelope} className="icon" />
            <input
              className="input"
              type="email"
              id="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              required
            />
          </div>
        </div>

        <div className="form-group">
          <label className="label" htmlFor="password">Password</label>
          <div className="input-container">
            <FontAwesomeIcon icon={faLock} className="icon" />
            <input
              className="input"
              type="password"
              id="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              required
            />
          </div>
        </div>

        <div className="form-group">
          <label className="label" htmlFor="confirmPassword">Confirm Password</label>
          <div className="input-container">
            <FontAwesomeIcon icon={faLock} className="icon" />
            <input
              className="input"
              type="password"
              id="confirmPassword"
              name="confirmPassword"
              value={formData.confirmPassword}
              onChange={handleChange}
              required
            />
          </div>
        </div>

        <div className="form-group">
          <label className="label" htmlFor="avatar">Avatar</label>
          <div className="input-container">
            <FontAwesomeIcon icon={faImage} className="icon" />
            <input
              className="input"
              type="file"
              id="avatar"
              name="avatar"
              accept="image/*"
              onChange={handleChange}
            />
          </div>
        </div>

        <button type="submit" className="button" disabled={loading}>
          {loading ? <Spinner animation="border" size="sm" /> : 'Sign Up'}
        </button>
      </form>
      <p className="haveAccount">
        Already have an account? <Link to="/signin">Sign in here</Link>
      </p>
    </div>
  );
}

export default SignUp;
