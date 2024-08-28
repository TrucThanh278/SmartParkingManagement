import axios from "axios"
import cookie from "react-cookies"

const BASE_URL = 'http://localhost:8080/SmartParkingManagementApp/api'

export const endpoints = {
    'signin': '/login',
    'current-user': '/current-user',
    'signup': '/users'
}

export const authAPIs = () => {
    return axios.create({
        baseURL:BASE_URL,
        headers: {
            'Authorization': cookie.load('access-token')
        }
    })
}

export default axios.create({
    baseURL: BASE_URL
});