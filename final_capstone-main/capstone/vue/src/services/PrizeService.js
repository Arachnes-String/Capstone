import axios from 'axios';

export default {
    
  getPrizes() {
    return axios.get('/admin/prizes');
  },

  getPrizeById(prizeId) {
    return axios.get(`/admin/prizes/${prizeId}`);
  },

  createPrize(prizeData) {
    return axios.post('/admin/new-prize', prizeData);
  },

  updatePrize(prizeData) {
    return axios.put(`/admin/prizes/update/${prizeData.prizeId}`, prizeData);
  },

  deletePrize(prizeId) {
    return axios.delete(`/admin/prizes/delete/${prizeId}`);
  },
};