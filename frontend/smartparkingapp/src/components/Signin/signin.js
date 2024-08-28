import React, { useContext, useState } from "react";
import { Link } from "react-router-dom";
import "./signin.css";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEnvelope, faLock } from '@fortawesome/free-solid-svg-icons';
import cookie from "react-cookies";
import { Navigate } from "react-router";
import { MyDispatchContext, MyUserContext } from "../../Routes";
import APIs, { authAPIs, endpoints } from "../../configs/APIs";

function SignIn() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState(""); // State to store the error message
  const user = useContext(MyUserContext);
  const dispatch = useContext(MyDispatchContext);

  const login = async (e) => {
    e.preventDefault();

    if (!username || !password) {
      setErrorMessage("Username or password is empty");
      return;
    }

    try {
      let res = await APIs.post(endpoints['signin'], {
        "username": username,
        "password": password
      });

      cookie.save("access-token", res.data);

      let user = await authAPIs().get(endpoints['current-user']);
      cookie.save("user", user.data);

      dispatch({
        "type": "login",
        "payload": user.data
      });

      setErrorMessage(""); // Clear the error message after successful login

    } catch (ex) {
      if (ex.response && ex.response.status === 400) {
        setErrorMessage("Invalid username or password. Please try again.");
      } else {
        setErrorMessage("An error occurred. Please try again later.");
      }
      console.error(ex);
    }
  }

  if (user !== null) {
    return <Navigate to="/home" />;
  }

  return (
    <div className="container-log">
      <h2>Sign In</h2>
      <form method="post" onSubmit={login}>
        <div className="form-group">
          <label className="label" htmlFor="username">
            Email
          </label>
          <div className="input-container">
            <FontAwesomeIcon icon={faEnvelope} className="icon" />
            <input
              className="input"
              type="text"
              id="username"
              name="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
        </div>
        <div className="form-group">
          <label className="label" htmlFor="password">
            Password
          </label>
          <div className="input-container">
            <FontAwesomeIcon icon={faLock} className="icon" />
            <input
              className="input"
              type="password"
              id="password"
              name="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
        </div>
        {errorMessage && <p className="error-message">{errorMessage}</p>} {/* Display error message */}
        <button type="submit" className="button">
          Sign In
        </button>
      </form>
      <p>
        Don't have an account? <Link to="/signup">Sign up here</Link>
      </p>
    </div>
  );
}

export default SignIn;
