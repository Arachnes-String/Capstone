<template>
<div class="container">
<DriverPickupComponent/>
<DriverPickupContainer/>
</div>
</template>



<script>
import DriverPickupComponent from '../components/DriverPickupComponent.vue';
import DriverPickupContainer from '../components/DriverPickupContainer.vue'


export default {
  components:
   { DriverPickupComponent, DriverPickupContainer }
} 
</script>


<style scoped>


.custom-font {
 font-family: 'Open Sans', sans-serif;
}

template {
  margin: 0;
  padding: 0;
  display: grid;
  grid-template-columns: 300px 1fr 250px;
  gap: 15px;
}


@media (max-width: 768px) {
  .container{
    margin: 0 10px;
  }
  
}

</style>
