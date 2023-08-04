<template>
  <div class="prizes-container" role="img" aria-label="Wire Mesh Basket With Wooden Handle Laying On Grass With Pink Rose Boquet Inside">
<prizes-component />
  </div>
</template>

<script>
import PrizesComponent from '../components/PrizesComponent.vue'

export default {
  components: { PrizesComponent },

}
</script>

<style scoped>
.prizes-container {
  height: 100vh;
  background-image: linear-gradient(
    rgba(300, 300, 300, 0.5),
    rgba(300, 300, 300, 0.5)
  ),
  url(../img/prize.jpg);
  background-size: cover;
  background-position: center;
  font-size: 25px;
  font-family: "Open Sans", sans-serif;
  font-weight: 600;
}
</style>