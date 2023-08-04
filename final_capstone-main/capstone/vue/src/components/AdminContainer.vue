<template>
  <div class="admin-container" role="img" aria-label="Field of Wildflowers in Multiple Colors">
    <h2>All Pickups Pickups</h2>
          <div class="options">
                <label for="filter" style="font-weight: 600;">Filter Pickups...</label>
                <select name="filter" id="filter" v-model="status" class="select-dropdown">
                    <option value="">Show All Pickups</option>
                    <option value="pending">Show Pending Pickups</option>
                    <option value="completed">Show Completed Pickups</option>
                    <option value="rejected">Show Rejected Pickups</option>
                </select>
                  <select name="drivers" id="drivers" v-model="driver" class="select-dropdown">
                  <option value="">All Drivers</option>
                  <option v-for="driver in this.$store.state.allDrivers" :key="driver.driverId" :value="driver.driverId">
                  {{driver.lastName}}, {{driver.firstName}}
                  </option>
              </select>
                <select name="clients" id="clients" v-model="client" class="select-dropdown">
                  <option value="">All Clients</option>
                  <option v-for="client in this.$store.state.allClients" :key="client.userId" :value="client.userId">
                  {{client.lastName}}, {{client.firstName}}
                  </option>
                </select>
            </div>
            <div class="pickups-container">
               <admin-component v-for="adminPickup in adminPickupsByEverything" :key="adminPickup.pickUpId" :adminPickup="adminPickup" /> 
            </div>
    </div>
</template>

<script>

import AdminComponent from './AdminComponent.vue'


export default {
  components: {
     AdminComponent 
  
  },
  data(){
    return {
      status: "",
      client: "",
      driver: "",

    }
  },

  computed: {
      adminPickupsByStatus: function(){
          if (this.status === "pending"){
                return this.$store.state.adminPickups.filter((pickup) => pickup.status.toLowerCase() === "pending");
            }
            else if (this.status === "completed"){
                return this.$store.state.adminPickups.filter((pickup) => pickup.status.toLowerCase() === "completed");
            }
            else if (this.status === "rejected"){
                return this.$store.state.adminPickups.filter((pickup) => pickup.status.toLowerCase() === "rejected");
            }
            
            return this.$store.state.adminPickups;

      },
      adminPickupsByClient: function(){
          if (this.client != ""){
            return this.adminPickupsByStatus.filter(pickup => pickup.clientId == this.client)
          }
            
            return this.adminPickupsByStatus;
      },
      adminPickupsByEverything: function(){
          if (this.driver != ""){
            return this.adminPickupsByClient.filter(pickup => pickup.driverId == this.driver)
          }
            return this.adminPickupsByClient;
      },


      drivers: function(){
          return this.$store.state.allDrivers;
      }, 

      clients: function(){
          return this.$store.state.allClients;
      }
  }

}
</script>

<style scoped>

.admin-container{
  height: 100%;
  display: flex;
  flex-direction: column;
}
.options {
  background-color: white;
  border: 1px solid green;
  border-radius: 8px; 
}

.pickups-container {
  display: flex;
  flex-direction: column;
  overflow: auto;
}

.select-dropdown {
  /* Add or modify the following styles */
  font-family: "Open Sans", sans-serif; /* Set the desired font-family */
  font-size: 15px; /* Set the desired font-size */
}


</style>
 

