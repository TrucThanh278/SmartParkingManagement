import React, { useState } from 'react';
import './BookSearch.css';

function BookSearch({ parkingData, onSearchResults }) {
    const [location, setLocation] = useState('');
    const [startTime, setStartTime] = useState('');
    const [endTime, setEndTime] = useState('');
    const [sortBy, setSortBy] = useState('');

    const handleSearch = (e) => {
        e.preventDefault();

        let filtered = parkingData;

        // Lọc theo vị trí
        if (location) {
            filtered = filtered.filter(parking => 
                parking.location.toLowerCase().includes(location.toLowerCase())
            );
        }

        // Lọc theo thời gian
        if (startTime && endTime) {
            const [startHour, startMinute] = startTime.split(':').map(Number);
            const [endHour, endMinute] = endTime.split(':').map(Number);

            filtered = filtered.filter(parking => {
                const [parkingStartHour, parkingStartMinute] = parking.startTime.split(':').map(Number);
                const [parkingEndHour, parkingEndMinute] = parking.endTime.split(':').map(Number);

                const parkingStartTotalMinutes = parkingStartHour * 60 + parkingStartMinute;
                const parkingEndTotalMinutes = parkingEndHour * 60 + parkingEndMinute;
                const startTotalMinutes = startHour * 60 + startMinute;
                const endTotalMinutes = endHour * 60 + endMinute;

                return parkingStartTotalMinutes <= startTotalMinutes && parkingEndTotalMinutes >= endTotalMinutes;
            });
        }

        // Sắp xếp theo tiêu chí
        if (sortBy) {
            filtered = filtered.sort((a, b) => {
                if (sortBy === 'price-asc') return a.price - b.price;
                if (sortBy === 'price-desc') return b.price - a.price;
                if (sortBy === 'rating-asc') return a.rating - b.rating;
                if (sortBy === 'rating-desc') return b.rating - a.rating;
                return 0;
            });
        }

        onSearchResults(filtered);
    };

    return (
        <div className="book-search">
            <form onSubmit={handleSearch} className="search-form">
                <div className='inputTime'>
                    <input 
                        type="text" 
                        placeholder="Location" 
                        value={location} 
                        onChange={(e) => setLocation(e.target.value)} 
                    />
                    <input 
                        type="time" 
                        value={startTime} 
                        onChange={(e) => setStartTime(e.target.value)} 
                    />
                    <input 
                        type="time" 
                        value={endTime} 
                        onChange={(e) => setEndTime(e.target.value)} 
                    />
                    <select value={sortBy} onChange={(e) => setSortBy(e.target.value)}>
                        <option value="">Sort By</option>
                        <option value="price-asc">Price (Low to High)</option>
                        <option value="price-desc">Price (High to Low)</option>
                        <option value="rating-asc">Rating (Low to High)</option>
                        <option value="rating-desc">Rating (High to Low)</option>
                    </select>
                </div>
                <button type="submit">Search</button>
            </form>
        </div>
    );
}

export default BookSearch;
