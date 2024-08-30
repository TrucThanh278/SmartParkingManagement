
import logo from './asset/images/logo.png';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link } from 'react-router-dom';

function App() {

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <h1>Welcome to SmartParking</h1>
        <div className="button-container">
        <Link to="/signup"><button className="button">Sign up</button></Link>     
        <Link to="/signin"><button className="button">Sign in</button></Link>
        </div>
      </header>
    </div>
  );
}

export default App;
