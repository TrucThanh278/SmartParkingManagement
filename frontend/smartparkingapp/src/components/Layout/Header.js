import React, { useState, useContext } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { MyDispatchContext, MyUserContext } from '../../Routes';
import { Button } from 'react-bootstrap';
import { FaBars, FaTimes } from 'react-icons/fa'; // Import các icon
import SP from '../../asset/images/logo.png';
import './header.css'; 

const Header = () => {
    const [menuOpen, setMenuOpen] = useState(false);
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);
    const navigate = useNavigate();

    const handleLogout = () => {
        dispatch({ type: 'logout' });
        navigate('/signin');
    };

    const toggleMenu = () => {
        setMenuOpen(!menuOpen);
    };

    return (
        <div className="top-bar">
            <div className="logo">
                <img src={SP} alt="Smart Parking Logo" />
            </div>
            <div className={`nav-links ${menuOpen ? 'open' : ''}`}>
                <Link to="/home">
                    <button className="top-bar-button">Home</button>
                </Link>
                <Link to="/book">
                    <button className="top-bar-button">Book</button>
                </Link>
                <Link to="/account">
                    <button className="top-bar-button">Account</button>
                </Link>
                <Link to="/ticket">
                    <button className="top-bar-button">Ticket</button>
                </Link>
                <Link to="/contact">
                    <button className="top-bar-button">Contact</button>
                </Link>
                {user ? (
                    <Button className="top-bar-button" variant='danger' onClick={handleLogout}>
                        Logout
                    </Button>
                ) : (
                    <Link className='nav-link text-success' to="/signin">

                    </Link>
                )}
            </div>
            <div className="hamburger" onClick={toggleMenu}>
                {menuOpen ? <FaTimes /> : <FaBars />} {/* Icon tùy theo trạng thái menu */}
            </div>
        </div>
    );
};

export default Header;
