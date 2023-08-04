<template>
  <div class="page" role="img" aria-label="A Bush with Pink Roses, Half-Closed">
      <my-driver-pickups-container />
  </div>
</template>

<script>
import DriverService from "../services/DriverService";
import MyDriverPickupsContainer from '../components/MyDriverPickupsContainer.vue';

export default {
  components: { MyDriverPickupsContainer },

created(){
     DriverService.getDriverPickupsForDriverId(this.$store.state.user.id)
        .then(response => this.$store.commit("SET_DRIVER_PICKUPS", response.data));

}

}
</script>

<style scoped>
.page{
  height: 100vh;
  background-image: linear-gradient(
    rgba(300, 300, 300, 0.5),
    rgba(300, 300, 300, 0.5)
  ),
  url(../img/mypickups-background.jpg);
  background-size: cover;
  background-position: center;
  padding: 10px;
}

    .page{
        height: 90vh;
    }
</style>