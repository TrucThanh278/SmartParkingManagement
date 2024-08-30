import SP from '../../SP.svg';
import React from "react";
// import logo from "./logo512.png";
import "./contact.css";
import Header from '../Layout/Header';

function contact() {
  const phone = "+212 771325766";
  const email = "abdelhamidnajmi10@gmail.com";
  const address = "INPT, Av Allal Al Fassi, Rabat, Morocco";

  return (
    <div className="contact">
     <Header />
      <div className="contact-text">
        <h1>Contact Us</h1>
        <p>Phone: {phone}</p>
        <p>Email: {email}</p>
        <p>Address: {address}</p>
      </div>
    </div>
  );
}

export default contact;
