<template>
  <div class="container">
    <admin-container />
  </div>
</template>

<script>
import AdminContainer from './AdminContainer.vue'
import PickupService from '../services/PickupService'
import DriverService from '../services/DriverService'
import ClientService from '../services/ClientService'

export default {
  components: { AdminContainer },


  created(){
    PickupService.adminGetAllPickups()
    .then(response => this.$store.commit("SET_ADMIN_PICKUPS", response.data));

    DriverService.adminGetAllDrivers()
    .then(response => this.$store.commit("SET_ALL_DRIVERS", response.data));

    ClientService.adminGetAllClients()
    .then(response => this.$store.commit("SET_ALL_CLIENTS", response.data));

  }

}
</script>

<style scoped>
.container {
  background-image: linear-gradient(
      rgba(300, 300, 300, 0.2),
      rgba(300, 300, 300, 0.5)
    ),
    url(../img/wildflowers.jpg);
  background-size: cover;
  background-position: center;
  height: 100%;

}


</style>