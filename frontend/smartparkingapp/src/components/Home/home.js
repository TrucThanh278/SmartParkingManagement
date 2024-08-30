import React from 'react';
import './home.css';
import Header from '../Layout/Header';
import UserEasy from '../../asset/images/use-easy.webp';
import Secure from '../../asset/images/secure.jpg';
import Convenient from '../../asset/images/Convenient.webp';
import Footer from '../Layout/Footer';
import Carousel from 'react-bootstrap/Carousel';
import imageCarRed from '../../asset/images/HomeCar.png';
import imageCarWhite from '../../asset/images/Car.png';
import imageCarBlack from '../../asset/images/carBlack.png';
import { Link } from 'react-router-dom';

function Home() {
  return (
    <div className="home">
      <div className="hero-section">
        <Header/>
        <div className='hero-section-des'>
          <Carousel data-bs-theme="dark">
            <Carousel.Item>
              <img
                className="d-block w-100"
                src={imageCarRed}
                alt="First slide"
              />
              <Carousel.Caption>
                <h1>Welcome to Smart Parking</h1>
                <p>Find and reserve parking spots in seconds!</p>
                <Link to="/book">
                  <button className="hero-button">Book Now</button>
                </Link>
              </Carousel.Caption>
            </Carousel.Item>

            <Carousel.Item>
              <img
                className="d-block w-100"
                src={imageCarWhite}
                alt="Second slide"
              />
              <Carousel.Caption>
                <h1>Easy To Use</h1>
                <p>Find and reserve parking spots in seconds!</p>
                <Link to="/book">
                  <button className="hero-button">Book Now</button>
                </Link>
              </Carousel.Caption>
            </Carousel.Item>

            <Carousel.Item>
              <img
                className="d-block w-100"
                src={imageCarBlack}
                alt="Third slide"
              />
              <Carousel.Caption>
                <h1>Convenient</h1>
                <p>Find and reserve parking spots in seconds!</p>
                <Link to="/book">
                  <button className="hero-button">Book Now</button>
                </Link>
              </Carousel.Caption>
            </Carousel.Item>
          </Carousel>
        </div>
      </div>
      <div className="info-section">
        <div className="info-card">
          <img src={UserEasy} alt="Icon 1" />
          <h3>Easy to Use</h3>
          <p>Our platform is user-friendly and makes parking a breeze.</p>
        </div>
        <div className="info-card">
          <img src={Secure} alt="Icon 2" />
          <h3>Secure</h3>
          <p>Your safety is our top priority, and our platform is designed with the latest security measures.</p>
        </div>
        <div className="info-card">
          <img src={Convenient} alt="Icon 3" />
          <h3>Convenient</h3>
          <p>Our service allows you to find and reserve parking spots from anywhere, at any time.</p>
        </div>
      </div>

      <Footer />
    </div>
  );
}

export default Home;
