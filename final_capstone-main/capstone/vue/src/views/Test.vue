<template>
  <map-component />
</template>

<script>
import MapComponent from '../components/MapComponent.vue'
import DriverService from '../services/DriverService'

export default {
    components: {
        MapComponent,
        
    },
    created(){
         DriverService.getDriverPickupsForDriverId(this.$store.state.user.id)
        .then(response => this.$store.commit("SET_DRIVER_PICKUPS", response.data));

    }
}
</script>

<style scoped>

</style>