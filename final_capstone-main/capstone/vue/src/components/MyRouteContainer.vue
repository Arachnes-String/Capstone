<template>
   <div class="my-pickups-container">
          <h2>My Route</h2>
            <div class="my-pickups">
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

     
                return this.$store.state.driverPickups.filter(pickup => pickup.status.toLowerCase() === "pending" & pickup.driverId == this.$store.state.user.id);
  },
}

}
</script>

<style scoped>
    .my-pickups-container{
        display: flex;
        flex-direction: column;
        border: 2px solid #4a7c59;
        background-color: rgba(242, 242, 242, 0.8);
        box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.5);
        border-radius: 8px;
        max-height: 539px;
        padding: 10px;
        margin-top: 20px;
        max-width: 100vw;
        
    }

    .my-pickups{
        margin-top: 1rem;
        display: flex;
        flex-direction: column;
        gap: 10px;
        overflow-y:auto;
        max-width: 100vw;
        height: 100%;
        
        
    }

    .my-pickups-container h2{
       text-align: center;
       height: 0.5px;
        
    }

    .my-pickups-container div{
        display: flex;
        flex-direction: column;
        border: 2px solid #4a7c59;
        background-color: rgba(242, 242, 242, 0.8);
        box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.5);
        border-radius: 8px;
        max-height: 539px;
        padding: 10px;
        margin-top: 20px;
        max-width: 100vw;
        background-color: rgb(151, 202, 226);
        
    }
  

</style>