import React from "react";
import "./PaymentForm.css";

function PaymentForm({ price, onClose }) {

    return (
        <div className="payment-form">
            <h3>Proceed with Payment</h3>
            <p>Total Amount: {price} VND</p>
            <img
                src="https://developers.momo.vn/v3/assets/images/square-logo-f8712a4d5be38f389e6bc94c70a33bf4.png"
                alt="MoMo Logo"
                className="payment-form-image"
            />
            <button>Pay with MoMo</button>
            <button className="close-btn" onClick={onClose}>
                <i className="fas fa-times"></i>
            </button>
        </div>
    );
}

export default PaymentForm;
