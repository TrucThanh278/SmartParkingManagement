// import React, { useState, useEffect } from 'react';
// import { useParams, useNavigate } from 'react-router-dom';
// import { authAPIs, endpoints } from '../../configs/APIs';
// import Hero from '../../asset/images/hero.jpg';
// import './ParkingDetail.css';
// import Header from '../Layout/Header';

// function ParkingDetail() {
//     const { id } = useParams(); // Get parking ID from URL
//     const [parking, setParking] = useState(null);
//     const [loading, setLoading] = useState(true);
//     const [error, setError] = useState(null);
//     const navigate = useNavigate();

//     useEffect(() => {
//         const fetchParkingDetail = async () => {
//             try {
//                 const response = await authAPIs().get(endpoints.parkingDetail(id));
//                 console.log("Parking detail response:", response);

//                 if (response.data) {
//                     setParking(response.data);
//                 } else {
//                     throw new Error("Invalid API response structure");
//                 }
//             } catch (err) {
//                 setError('Error fetching parking details');
//                 console.error("Error fetching data:", err);
//             } finally {
//                 setLoading(false);
//             }
//         };

//         fetchParkingDetail();
//     }, [id]);

//     const handleBackClick = () => {
//         navigate(-1); // Navigate back to the previous page
//     };

//     if (loading) return <div>Loading...</div>;
//     if (error) return <div>{error}</div>;

//     return (
//         <div className="parking-detail">
//             <Header />
//             <button onClick={handleBackClick}>Back</button>
//             {parking ? (
//                 <>
//                     <h1>{parking.name}</h1>
//                     <img src={parking.image || Hero} alt={parking.name} />
//                     <p>Address: {parking.address}</p>
//                     <p>Price: {parking.pricePerHour || 'N/A'}$ - hour</p>
//                     <p>Total Spots: {parking.totalSpots || 'N/A'}</p>
//                     <p>Start Time: {parking.startTime || 'N/A'}</p>
//                     <p>End Time: {parking.endTime || 'N/A'}</p>
//                     <p>{parking.description || 'No description'}</p>
//                 </>
//             ) : (
//                 <p>No details available</p>
//             )}
//         </div>
//     );
// }

// export default ParkingDetail;
