import axios from 'axios';

export default {

    submitNewPickup(payload){
       return axios.post('/pickups/new-pick-up', payload)
    },

    getPickUpsByClientId(clientId){
        return axios.get(`/pickups/client/${clientId}`)
    },

    updateDriverPickup(pickupId, status){
        return axios.put(`/pickups/update/${pickupId}?status=${status}`)
    },

    updateAssignedDriver(pickupId, driverId){
        return axios.put(`/pickups/pickup/${pickupId}/${driverId}`)
    },

    adminGetAllPickups(){
        return axios.get('/admin/pickups-all')
    },

    adminUpdatePickup(updatedPickup){
        return axios.put('/pickups/update-dto', updatedPickup)
    }

}