import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}


export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    requestedDateTime: '',
    weight: '',
    bins: {
      green: false,
      brown: false,
      clear: false,
    },
    userBin: {},
    pickups: [],
    client: {},
    driver: {},
    editClient: false,
    clientAddress: {},
    editAddress: false,
    //DriverPickup includes address and everything
    driverPickups: [],
    driverPickupAddresses: [],
    adminPickups: [],
    allClients: [],
    allDrivers: [],
    prizes: [],


  },
  mutations: {
    setNewUser(state, user) {
      state.newUser = user;
    },
    SET_USER_BIN(state, userBin) {
      state.userBin = userBin;
    },
    SET_PICKUPS(state, pickups) {
      state.pickups = pickups;
    },
    SET_CLIENT(state, client) {
      state.client = client;
    },
    SET_DRIVER(state, driver){
      state.driver = driver;
    },
    SET_EDIT_CLIENT(state, boolean) {
      state.editClient = boolean;
    },

    SET_CLIENT_ADDRESS(state, clientAddress) {
      state.clientAddress = clientAddress;
    },
    SET_ALL_CLIENTS(state, allClients){
      state.allClients = allClients;
    },
    SET_ALL_DRIVERS(state, allDrivers){
      state.allDrivers = allDrivers;
    },
    SET_PRIZES(state, prizes){
      state.prizes = prizes;
    },

    SET_EDIT_ADDRESS(state, boolean) {
      state.editAddress = boolean;
    },

    SET_DRIVER_PICKUPS(state, driverPickups){
      state.driverPickups = driverPickups;
    
    },
    SET_ADMIN_PICKUPS(state, adminPickups){
      state.adminPickups = adminPickups;
    },

    setRequestedDateTime(state, dateTime) {
      state.requestedDateTime = dateTime;
    },
    setWeight(state, weight) {
      state.weight = weight;
    },
    setBin(state, { bin, value }) {
      state.bins[bin] = value;
    },
    SAVE_SHELTERS(state, shelterArray) {
      state.shelters - shelterArray;

    },
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    }
  },

  
});



