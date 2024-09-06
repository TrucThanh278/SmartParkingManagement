import React, { useContext, useState } from "react";
import { Link } from "react-router-dom";
import "./signin.css";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEnvelope, faLock } from '@fortawesome/free-solid-svg-icons';
import cookie from "react-cookies";
import { Navigate } from "react-router";
import { MyDispatchContext, MyUserContext } from "../../Routes";
import APIs, { authAPIs, endpoints } from "../../configs/APIs";
import Swal from 'sweetalert2';

function SignIn() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [loading, setLoading] = useState(false);
  const user = useContext(MyUserContext);
  const dispatch = useContext(MyDispatchContext);

  const login = async (e) => {
    e.preventDefault();

    if (!username || !password) {
      setErrorMessage("Username or password is empty");
      return;
    }

    setLoading(true);
    try {
      let res = await APIs.post(endpoints['signin'], {
        "username": username,
        "password": password
      });

      cookie.save("access-token", res.data);

      let userRes = await authAPIs().get(endpoints['current-user']);
      const userData = userRes.data;

      if (userData.enabled === true) {
        cookie.save("user", userData);

        dispatch({
          "type": "login",
          "payload": userData
        });

        setErrorMessage("");

        Swal.fire({
          icon: 'success',
          title: 'Login Successful',
          text: 'Welcome back!',
          confirmButtonText: 'OK'
        });

      } else {
        setErrorMessage("Your account is not verified. Please verify your account.");
      }

    } catch (ex) {
      if (ex.response && ex.response.status === 400) {
        setErrorMessage("Invalid username or password. Please try again.");
      } else {
        setErrorMessage("An error occurred. Please try again later.");
      }
      console.error(ex);
    }
    setLoading(false); 
  }

  if (user !== null) {
    return <Navigate to="/home" />;
  }

  return (
    <div className="container-log">
      <h2 className="signIn">Sign In</h2>
      <form method="post" onSubmit={login}>
        <div className="form-group">
          <label className="label" htmlFor="username">
            Username
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
        {errorMessage && <p className="error-message">{errorMessage}</p>}
        <button type="submit" className="button" disabled={loading}>
          {loading ? "Signing in..." : "Sign In"}
        </button>
      </form>
      <p className="haveAccount">
        Don't have an account? <Link to="/signup">Sign up here</Link>
      </p>
    </div>
  );
}

export default SignIn;
