    <template>
   <div class="unassigned-pickups-container">
          <h2>Available / Unassigned Pickups</h2>
          <div class="unassigned-pickups">
                <driver-pickup-component v-for="driverPickup in unassignedPickups" :key="driverPickup.pickupId" :driverPickup="driverPickup" />
                <p v-if="unassignedPickups.length < 1">No Unassigned Pickups Available</p>
        </div>
      </div>
</template>

<script>
import DriverPickupComponent from './DriverPickupComponent.vue';
export default {
     components: {
        DriverPickupComponent
    },
computed: {
    unassignedPickups: function(){
    return this.$store.state.driverPickups.filter(pickup => pickup.driverId == 1 & pickup.status.toLowerCase() == 'pending');
  },
}
}

</script>

<style scoped>

.unassigned-pickups-container  {
        border: 2px solid #4a7c59;
        background-color: rgba(242, 242, 242, 0.8);
        box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.5);
        border-radius: 8px;
        padding: 25px;
        text-align: center;
         margin: -15px -5px;
        
        max-height: 0%;
        
        
  
}
.unassigned-pickups{
    display: flex;
    flex-direction: column;
    max-height: 80px;
    gap: .5rem;
    overflow-y: scroll;
    }

    .unassigned-pickups-container div {
      display: flex;
      justify-items: flex-start;
      border: inherit;
      margin-top: -10px;
      max-height: 85px;
    }

    h2 {
      margin-top: -17px;
    }

    .div.unassigned-pickups-container{
      justify-self: normal;
    }

</style>