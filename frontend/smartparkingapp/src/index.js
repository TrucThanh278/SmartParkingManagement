import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import Routes from './Routes';
import reportWebVitals from './reportWebVitals';
<link
  rel="stylesheet"
  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
  integrity="sha384-k6RqeWeci5ZR/Lv4MR0sA0FfDOM/Tavco2PA9btVzQ+6lRJ4eo+PZ6q2x4+x6q7g"
  crossorigin="anonymous"
/>


ReactDOM.render(
  <React.StrictMode>
    <Routes />
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
