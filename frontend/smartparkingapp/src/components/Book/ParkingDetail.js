import React from 'react';
import { useLocation } from 'react-router-dom';

function ParkingDetail() {
    const location = useLocation();
    const { parking } = location.state || {};

    return (
        <div>
            <h1>{parking?.name}</h1>
            <img src={parking?.image} alt={parking?.name} />
            <p>Address: {parking?.address}</p>
            <p>Description: {parking?.description}</p>
            <p>Price: {parking?.price_per_hour}$ - hour</p>
            <p>Total Spots: {parking?.total_spots}</p>
            <p>Start Time: {parking?.start_time}</p>
            <p>End Time: {parking?.end_time}</p>

            <h2>User Reviews and Ratings</h2>
            {/* Placeholder for user reviews and ratings */}
            <div>
                <p>Rating: ★★★★☆</p>
                <p>User: John Doe</p>
                <p>Comment: Great place to park, very convenient!</p>
            </div>
            {/* Add more reviews and ratings as needed */}
        </div>
    );
}

export default ParkingDetail;
