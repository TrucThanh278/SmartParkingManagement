import React from "react";
import { createContext, useReducer } from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MyUserReducer from './reducers/MyUserReducer';

import App from "./App";
import SignIn from "./components/Signin/signin";
import SignUp from "./components/Signup/signup";
import Home from "./components/Home/home"
import Book from "./components/Book/book"
import Account from "./components/Account/account"
import Contact from "./components/Contact/contact"
import Ticket from "./components/Ticket/ticket";
import BookSearch from "./components/Book/BookSearch";

export const MyUserContext = createContext();
export const MyDispatchContext = createContext();
export const MyCartContext = createContext();

function AppRoutes() {
  const [user, dispatch] = useReducer(MyUserReducer, null);

  return (
    <MyUserContext.Provider value={user}>
      <MyDispatchContext.Provider value={dispatch}>
        <Router>
          <Routes>
            <Route path="/" element={<App />} />
            <Route path="/signin" element={<SignIn />} />
            <Route path="/signup" element={<SignUp />} />
            <Route path="/home" element={<Home />} />
            <Route path="/book" element={<Book />} />
            <Route path="/account" element={<Account />} />
            <Route path="/ticket" element={<Ticket />} />
            <Route path="/contact" element={<Contact />} />
            <Route path="/booksearch" element={<BookSearch />} />
          </Routes>
        </Router>
      </MyDispatchContext.Provider>
    </MyUserContext.Provider>
  );
}

export default AppRoutes;
