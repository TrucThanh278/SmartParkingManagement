import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Header from "../Layout/Header";
import "./Ticket.css";

function Ticket() {
    const location = useLocation();
    const navigate = useNavigate();
    const ticketInfo = location.state?.ticketInfo;

    React.useEffect(() => {
        if (!ticketInfo) {
            navigate('/book');
        }
    }, [ticketInfo, navigate]);

    const handlePayment = () => {
        // Implement payment logic here
        console.log("Proceed to payment");
    };

    const handleDelete = () => {
        // Implement delete logic here
        console.log("Delete ticket");
        navigate('/');
    };

    if (!ticketInfo) {
        return (
            <div>
                <h2>No Ticket Available</h2>
                <p>There is no ticket information to display. Please make a booking first.</p>
            </div>
        );
    }

    return (
        <div className="ticket">
            <Header />
            <div className="ticket-content">
                <h2>Ticket Information</h2>
                <form className="form-ticket">
                    <div className="info-user">
                        <p>Car Model: {ticketInfo.carModel}</p>
                        <p>Spot Number: {ticketInfo.spotNumber}</p>
                        <p>Spot ID: {ticketInfo.spotId}</p>
                        <p>Start Date: {ticketInfo.startDate}</p>
                        <p>End Date: {ticketInfo.endDate}</p>
                    </div>
                    <div className="ticket-buttons">
                        <button className="ticket-button pay" onClick={handlePayment}>Pay</button>
                        <button className="ticket-button update" onClick={handlePayment}>Update</button>
                        <button className="ticket-button delete" onClick={handleDelete}>Delete</button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default Ticket;
