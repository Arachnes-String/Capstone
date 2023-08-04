<template>
  <div class="all-drivers" role="img" aria-label="Wildflowers in Multiple Colors">
      <div class="heading">
          <h1>All Drivers</h1>
      </div>
      <div class="driver-container">
          <driver-component v-for="driver in this.$store.state.allDrivers" :key="driver.driverId" :driver="driver" />
      </div>
  </div>
</template>

<script>
import DriverService from "../services/DriverService"
import DriverComponent from './DriverComponent.vue';

export default {
  components: { DriverComponent },



created(){
    DriverService.adminGetAllDrivers()
    .then(response => this.$store.commit("SET_ALL_DRIVERS", response.data));


}

}
</script>

<style scoped>
h1 {
  text-align: center ;
margin-left: -100px;
 
   
}

.all-drivers {
  background-image: linear-gradient(
      rgba(300, 300, 300, 0.8),
      rgba(300, 300, 300, 0.7)
    ),
    url(../img/wildflowers.jpg);
  background-size: cover;
  background-position: center;
  font-weight: 600;

}

</style>