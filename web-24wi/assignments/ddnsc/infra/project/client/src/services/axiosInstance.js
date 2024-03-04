import axios from 'axios';

const axiosInstance = axios.create({
    baseURL: 'https:dd.arcology.builderst:5000',
});

export default axiosInstance;
