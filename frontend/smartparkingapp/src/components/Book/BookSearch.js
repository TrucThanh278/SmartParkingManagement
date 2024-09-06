import React, { useState } from 'react';
import './BookSearch.css';
import { authAPIs, endpoints } from '../../configs/APIs';

function BookSearch({ onSearchResults, setTotalPages, setPage }) {
    const [name, setName] = useState('');
    const [address, setAddress] = useState('');
    const [sortBy, setSortBy] = useState('');
    const [error, setError] = useState('');

    const handleSearch = async () => {
        let query = `?name=${encodeURIComponent(name)}&address=${encodeURIComponent(address)}`;
        if (sortBy === 'price-asc') {
            query += `&sortByPriceAsc=true`;
        } else if (sortBy === 'price-desc') {
            query += `&sortByPriceAsc=false`;
        }

        try {
            const response = await authAPIs().get(`${endpoints.searchParkingLots}${query}`);
            onSearchResults(response.data.data); // Set the results
            setTotalPages(response.data.totalPages); // Set total pages from the search
            setPage(1); // Reset to the first page
        } catch (error) {
            console.error('Failed to fetch parking data', error);
            setError('Failed to fetch parking data');
        }
    };

    return (
        <div className="book-search">
            <form onSubmit={(e) => e.preventDefault()} className="search-form">
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
                <button type="submit" onClick={handleSearch}>Search</button>
                {error && <div className="error-message">{error}</div>}
            </form>
        </div>
    );
}

export default BookSearch;
