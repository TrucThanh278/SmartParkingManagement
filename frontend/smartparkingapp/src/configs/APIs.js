import axios from "axios"
import cookie from "react-cookies"

const BASE_URL = 'http://localhost:8080/SmartParkingManagementApp/api'

export const endpoints = {
    'signin': '/login',
    'current-user': '/current-user',
    'signup': '/users',
    'updateUser': (userId) => `/users/${userId}`,
    'changePassword': (userId) => `/${userId}/change-password`,
    'parkinglots': (page = 1) => `/parkinglots?page=${page}`,
    'searchParkingLots': '/parkinglots/search',
    vehiclesForUser: (userId) => `/vehicles/user/${userId}`,
    existingBookingsForSpot: (spotId) => `/bookinginfo/current-date/${spotId}`,
    createBooking: '/bookinginfo/booking',
    vehicleCategories: '/vehicle-categories',
    addVehicle: '/vehicles/add',
    bookingInfo: '/bookinginfo',
    updateBooking: (bookingId) => `/update/${bookingId}`,
    payment: (orderId, amount) => `${BASE_URL}/payment/initiate?orderId=${orderId}&amount=${amount}`

}

export const authAPIs = () => {
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            'Authorization': cookie.load('access-token')
        }
    })
}

export default axios.create({
    baseURL: BASE_URL
});