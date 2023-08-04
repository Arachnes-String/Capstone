<template>
  <div class="pickup-container" role="img" aria-label="Pink Roses with Tall Shoots of Purple Flowers Throughout">
    <div class="pickup-overlay">
      <h1 class="pickup-h1">Schedule Pick Up</h1>
      <br/>
      <pickup-form />
    </div>
  </div>
</template>

<script>
import PickupForm from "../components/PickupForm.vue";
export default {
  components: { PickupForm },
};
</script>

<style scoped>
.pickup-container {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background-image: linear-gradient(
    rgba(242, 242, 242, 0.8),
    rgba(242, 242, 242, 0.8)
  ), url(../img/pickup.jpg);
  background-size: cover;
  background-position: center;
}

.pickup-overlay {
  background-color: rgba(242, 242, 242, 0.8);
  border: 2px solid #4a7c59;
  padding: 20px;
  text-align: center;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.5);
  border-radius: 8px;
  position: relative;
  overflow: hidden;
}

.pickup-h1 {
  font-family: "Open Sans", sans-serif;
  font-size: 24px;
  margin: 0px;
}
</style>

