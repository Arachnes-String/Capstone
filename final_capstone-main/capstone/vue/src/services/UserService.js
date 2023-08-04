import axios from "axios";

const UserService = {
  getAllUsers() {
    return axios.get("/users");
  },

  getUser(user) {
    return axios.get('/users', user)
},
  
  getUserId(userId) {
    return axios.get(`/users/${userId}`);
  },
  
  createUser(user) {
    return axios.post("/users", user);
  },
  
  updateUser(userId, user) {
    return axios.put(`/users/${userId}`, user);
  },
  
  deleteUser(userId) {
    return axios.delete(`/users/${userId}`);
  }
};

export default UserService;