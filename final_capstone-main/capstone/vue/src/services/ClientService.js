import axios from "axios";

export default {

    createClient(client){
        return axios.post('/clients', client)
    },


    
    getTotal(){
        return axios.get('/total')
    },

    getClient(clientId){
        return axios.get(`/clients/${clientId}`)
    },

    updateClient(clientId, updatedClient){
        return axios.put(`/clients/${clientId}`, updatedClient)
    },

    updateAddress(clientId, updatedAddress){
        return axios.put(`/clients/${clientId}/address`, updatedAddress)
    },

    getClientAddress(clientId){
       return axios.get(`/clients/${clientId}/address`)
    },

    getTotalGreenBins(){
        return axios.get('/total/green')
    },

    getTotalClearBins(){
        return axios.get('/total/clear')
    },

    getTotalBrownBins(){
        return axios.get('/total/brown')
    },

    getTotalAllBins(){
        return axios.get('/total/all')
    },

    getClientBin(id){
        return axios.get(`/clients/${id}/stats`)
    },

    adminGetAllClients(){
        return axios.get('/clients/all');
    },
    adminDeleteClientById(clientId){
        return axios.delete(`/client/${clientId}`);
    }
}