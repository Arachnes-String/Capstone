<template>
  <div class="driver-pickup-container">
      <div class="top">
        <h2>My Pickups</h2>
          <div class="options">
                <label for="filter" style="font-weight: 600;">Filter Pickups...</label>
                <select name="filter" id="filter" v-model="filter">
                    <option value="">Show All Pickups</option>
                    <option value="pending">Show Pending Pickups</option>
                    <option value="completed">Show Completed Pickups</option>
                    <option value="rejected">Show Rejected Pickups</option>
                </select>
            </div>
            </div>
            <div class="my-pickups-container">
                <driver-pickup-component v-for="driverPickup in myPickups" :key="driverPickup.pickupId" :driverPickup="driverPickup" />
            </div>
      </div>
</template>

<script>
import DriverPickupComponent from './DriverPickupComponent.vue';
export default {
    components: {
        DriverPickupComponent
    },

data(){
    return {
        filter: "",
    }
},

computed: {
  myPickups: function(){

       if (this.filter === "pending"){
                return this.$store.state.driverPickups.filter(
                  (pickup) => 
                pickup.status.toLowerCase() === "pending" && 
                pickup.driverId == this.$store.state.user.id);
            }
            else if (this.filter === "completed"){
                return this.$store.state.driverPickups.filter(
                  (pickup) => 
                pickup.status.toLowerCase() === "completed" && 
                pickup.driverId == this.$store.state.user.id);
            }
            else if (this.filter === "rejected"){
                return this.$store.state.driverPickups.filter(
                  (pickup) => 
                pickup.status.toLowerCase() === "rejected" && 
                pickup.driverId == this.$store.state.user.id);
            }
            

        return this.$store.state.driverPickups.filter(
            (pickup) => pickup.driverId == this.$store.state.user.id);

  },
}

}
</script>

<style scoped>



.driver-pickup-container {
        border: 2px solid #4a7c59;
        background-color: rgba(242, 242, 242, 0.8);
        box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.5);
        border-radius: 8px;
        padding: 20px;
        height: 80vh;
        display: grid;
        grid-template-columns: 1fr;
        grid-template-rows: 1fr 7fr;
        grid-template-areas: 
        "top"
        "pickups"


} 

.my-pickups-container {
    display: flex;
    flex-direction: column;
    overflow: auto;
    height: 100%;
    gap: 1rem;
    grid-area: pickups;
    width: 95%;
}

.top {
    grid-area: top;
}







</style>