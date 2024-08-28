// import React, { useState } from 'react';
// import Hero from '../../asset/images/hero.jpg';
// import './BookList.css';
// import BookForm from './BookForm';
// import BookSearch from './BookSearch';

// const parkingData = [
//     {
//         src: Hero,
//         alt: "Carrefour Parking",
//         text: "Carrefour Parking",
//         location: "123 Main St, Anytown",
//         startTime: "08:00",
//         endTime: "20:00",
//         price: 10,
//         rating: 4.5
//     },
//     {
//         src: Hero,
//         alt: "Downtown Parking",
//         text: "Downtown Parking",
//         location: "456 Center Rd, City",
//         startTime: "06:00",
//         endTime: "22:00",
//         price: 8,
//         rating: 4.2
//     },
//     // Add more parking data as needed
// ];

// function ClickableImage({ src, alt, text, onClick, location, startTime, endTime, price, rating }) {
//     return (
//         <div className="image-container" onClick={onClick}>
//             <img className="image-shape" src={src} alt={alt} />
//             <div className="text-overlay">
//                 <span>{text}</span>
//                 <p>Location: {location}</p>
//                 <p>Start Time: {startTime}</p>
//                 <p>End Time: {endTime}</p>
//                 <p>Price: {price}$ - hour</p>
//                 <p>Rating: {rating} ★</p>
//             </div>
//         </div>
//     );
// }

// function BookList() {
//     const [showForm, setShowForm] = useState(false);
//     const [selectedParking, setSelectedParking] = useState(null);
//     const [filteredData, setFilteredData] = useState(parkingData);

//     const handleImageClick = (parking) => {
//         setSelectedParking(parking);
//         setShowForm(true);
//     };

//     const handleCloseForm = () => {
//         setShowForm(false);
//         setSelectedParking(null);
//     };

//     const handleSearchResults = (results) => {
//         setFilteredData(results);
//     };

//     return (
//         <div>
//             <h1>Choose your parking</h1>
//             <h2>Other parking lots will be added soon. Stay tuned!!!</h2>
//             <BookSearch parkingData={parkingData} onSearchResults={handleSearchResults} />
//             <div className="img-container">
//                 {filteredData.map((parking, index) => (
//                     <ClickableImage
//                         key={index}
//                         src={parking.src}
//                         alt={parking.alt}
//                         text={parking.text}
//                         location={parking.location}
//                         startTime={parking.startTime}
//                         endTime={parking.endTime}
//                         price={parking.price}
//                         rating={parking.rating}
//                         onClick={() => handleImageClick(parking)}
//                     />
//                 ))}
//             </div>
//             {showForm && <BookForm parkingData={selectedParking} onClose={handleCloseForm} />}
//         </div>
//     );
// }

// export default BookList;


import React, { useState } from 'react';
import Hero from '../../asset/images/hero.jpg';
import './BookList.css';
import BookForm from './BookForm';
import BookSearch from './BookSearch';

const parkingData = [
    {
        src: Hero,
        alt: "Carrefour Parking",
        text: "Carrefour Parking",
        location: "123 Main St, Anytown",
        startTime: "08:00",
        endTime: "20:00",
        price: 10,
        rating: 4.5
    },
    {
        src: Hero,
        alt: "Downtown Parking",
        text: "Downtown Parking",
        location: "456 Center Rd, City",
        startTime: "06:00",
        endTime: "22:00",
        price: 8,
        rating: 4.2
    },
    // Add more parking data as needed
];

function ClickableImage({ src, alt, text, onClick, location, startTime, endTime, price, rating }) {
    return (
        <div className="image-container" onClick={onClick}>
            <img className="image-shape" src={src} alt={alt} />
            <div className="text-overlay">
                <span>{text}</span>
                <p>Location: {location}</p>
                <p>Start Time: {startTime}</p>
                <p>End Time: {endTime}</p>
                <p>Price: {price}$ - hour</p>
                <p>Rating: {rating} ★</p>
            </div>
        </div>
    );
}

function BookList() {
    const [showForm, setShowForm] = useState(false);
    const [selectedParking, setSelectedParking] = useState(null);
    const [filteredData, setFilteredData] = useState(parkingData);

    const handleImageClick = (parking) => {
        setSelectedParking(parking);
        setShowForm(true);
    };

    const handleCloseForm = () => {
        setShowForm(false);
        setSelectedParking(null);
    };

    const handleSearchResults = (results) => {
        setFilteredData(results);
    };

    return (
        <div>
            <h1>Choose your parking</h1>
            <h2>Other parking lots will be added soon. Stay tuned!!!</h2>
            <BookSearch parkingData={parkingData} onSearchResults={handleSearchResults} />
            <div className="img-container">
                {filteredData.map((parking, index) => (
                    <ClickableImage
                        key={index}
                        src={parking.src}
                        alt={parking.alt}
                        text={parking.text}
                        location={parking.location}
                        startTime={parking.startTime}
                        endTime={parking.endTime}
                        price={parking.price}
                        rating={parking.rating}
                        onClick={() => handleImageClick(parking)}
                    />
                ))}
            </div>
            {showForm && <BookForm parkingData={selectedParking} onClose={handleCloseForm} />}
        </div>
    );
}

export default BookList;
