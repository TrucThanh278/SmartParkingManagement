import React, { useState } from 'react';
import axios from 'axios';
import './BookSearch.css';
import cookie from 'react-cookies';

function BookSearch({ onSearchResults }) {
    const [name, setName] = useState('');
    const [address, setAddress] = useState('');
    const [sortBy, setSortBy] = useState('');
    const [error, setError] = useState('');

    const handleSearch = async (e) => {
        e.preventDefault();

        let query = `?name=${encodeURIComponent(name)}&address=${encodeURIComponent(address)}`;


        if (sortBy === 'price-asc') {
            query += `&sortByPriceAsc=true`;
        } else if (sortBy === 'price-desc') {
            query += `&sortByPriceAsc=false`;
        }

        const token = cookie.load('access-token');

        try {
            const response = await axios.get(`http://localhost:8080/SmartParkingManagementApp/api/parkinglots/search${query}`, {
                headers: {
                    'Authorization': `${token}`
                }
            });
            onSearchResults(response.data);
        } catch (error) {
            console.error('Failed to fetch parking data', error);
            setError('Failed to fetch parking data');
        }
    };

    return (
        <div className="book-search">
            <form onSubmit={handleSearch} className="search-form">
                <div className='inputFields'>
                    <input
                        type="text"
                        placeholder="Name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                    />
                    <input
                        type="text"
                        placeholder="Address"
                        value={address}
                        onChange={(e) => setAddress(e.target.value)}
                    />
                    <select value={sortBy} onChange={(e) => setSortBy(e.target.value)}>
                        <option value="">Sort By</option>
                        <option value="price-asc">Price (Low to High)</option>
                        <option value="price-desc">Price (High to Low)</option>
                    </select>
                </div>
                <button type="submit">Search</button>
                {error && <div className="error-message">{error}</div>}
            </form>
        </div>
    );
}

export default BookSearch;
