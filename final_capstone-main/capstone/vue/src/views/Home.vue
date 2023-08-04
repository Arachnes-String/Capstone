<template>
  <div class="home-container" role="img" aria-label="Corked Glass Jar with Clear Water and Light Purple Flowers Inside, Laying on Green Mossy Bed">
    <div class="home-content">
      <h1>Welcome to Glass Roots Recycling</h1>
      <p><em>We want to recycle glass. Give us your glass. WE NEED YOUR GLASS.</em></p>
      <div class="counter-container">
        <counter-box-component action="Recyled" icon="fa-recycle" type="Pounds" animation="fa-spin" :counter="totalWeight"/> 
        <counter-box-component action="Gathered" icon="fa-trash" type="Bins" animation="fa-bounce" :counter="totalPickUp"/>
      </div>
    </div>
  </div>
</template>

<script>
import CounterBoxComponent from '../components/CounterBoxComponent.vue';
import ClientService from '../services/ClientService'

export default {
  name: "home",
  components: { CounterBoxComponent },

  data() {
    return {
      totalWeight: 0,
      totalPickUp: 0,
    }
  },
  created() {
    ClientService.getTotal().then(response => this.totalWeight = response.data);
    ClientService.getTotalAllBins().then(response => this.totalPickUp = response.data);
  }
};
</script>

<style scoped>
.home-container {
  height: 100vh;
  background-image: linear-gradient(
    rgba(300, 300, 300, 0.5),
    rgba(300, 300, 300, 0.5)
  ),
  url(../img/plant_background.jpg);
  background-size: cover;
  background-position: center;
}

.home-content {
  width: 100%;
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  text-align: center;
  color: #4a7c59;
}

.home-content h1 {
  font-family: "Open Sans", sans-serif;
  text-shadow: black;
  font-size: 70px;
  margin-top: 80px;
}

p{
  font-size: 25px;
  font-family: "Open Sans", sans-serif;
  font-weight: 700;
}

.counter-container{
  display: inline-flex;
}

</style>