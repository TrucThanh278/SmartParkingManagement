import React, { useContext } from "react";
import "./PaymentForm.css";
import { PriceContext } from "./BookForm";

import { authAPIs, endpoints } from "../../configs/APIs";

function PaymentForm({ onClose }) {
    const price = useContext(PriceContext);

    const momo = endpoints.payment(1, price * 10000);

    return (
        <div className="payment-form">
            <h3>Proceed with Payment</h3>
            <p>Total Amount: {price * 10000} VND</p>

            <a href="`${momo}`">
                <img
                    src="https://developers.momo.vn/v3/assets/images/square-logo-f8712a4d5be38f389e6bc94c70a33bf4.png"
                    alt="MoMo Logo"
                    className="payment-form-image"
                />
            </a>
            <button className="close-btn" onClick={onClose}>
                <i className="fas fa-times"></i>
            </button>
        </div>
    );
}

export default PaymentForm;
