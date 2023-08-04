import axios from "axios";



export default {
    createDriver(driver) {
        return axios.post('/drivers', driver)
    },
    getDriverById(driverId){
        return axios.get(`/drivers/driver/${driverId}`)
    },

    getDriverPickupsForDriverId(driverId){
        return axios.get(`/pickups/all/${driverId}`)

    },
    adminGetAllDrivers(){
        return axios.get('/drivers/all')
    },
    adminDeleteDriver(driverId){
        return axios.delete(`/drivers/delete/${driverId}`)
    },
    adminUpdateDriver(updatedDriver){
        return axios.put(`/drivers/${updatedDriver.driverId}`, updatedDriver)
    }
  
}