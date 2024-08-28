import React from 'react';
import { Routes, Route } from 'react-router-dom';
import "./book.css";
import Header from '../Layout/Header.js';
import BookList from './BookList.js';
import BookForm from './BookForm.js';
import BookTicket from './BookTicket';
import Ticket from '../Ticket/ticket';

function Book() {
  return (
    <div className="book">
      <div className='book-container'>
        <Header />
        <Routes>
          <Route path="/" element={<BookList />} />
          <Route path="/book-form" element={<BookForm />} />
          <Route path="/book-ticket" element={<BookTicket />} />
          <Route path="/ticket" element={<Ticket />} />
        </Routes>
      </div>
    </div>
  );
}

export default Book;
