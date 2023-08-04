<template>
  <div class="main-content" :class="updatedAdminPickup.status.toLowerCase()">
   
   
        <div class="address">
          <h3>{{ adminPickup.streetAddress }}, {{adminPickup.city}}, {{adminPickup.state}}, {{adminPickup.zip}}</h3>
         </div>

        <div class="time-requested">
          <p>Pick Up Scheduled For:</p>
          <p>
            {{
              new Date(adminPickup.dateTimeRequested).toLocaleString("en-US", {
                year: "numeric",
                month: "long",
                day: "numeric",
                hour: "numeric",
                hour12: true,
              })
            }}
          </p>
        </div>
          <div class="bins">
             <h4>Bins:</h4>
             <div class="green">
                 <p>Green <i class="fa-solid fa-recycle" style="color: rgb(96, 189, 3)"></i><input type="checkbox" name="green" id="green"  v-model="updatedAdminPickup.greenBinFull"></p>
              </div>
            <div  class="brown">
               <p>Brown <i class="fa-solid fa-recycle" style="color: rgb(144, 123, 112)"></i><input type="checkbox" name="brown" id="brown" v-model="updatedAdminPickup.brownBinFull"></p> 
            </div>
            <div class="clear">
                <p>Clear <i class="fa-solid fa-recycle" style="color: rgb(191, 191, 238)"></i><input type="checkbox" name="clear" id="clear" v-model="updatedAdminPickup.clearBinFull"></p>
            </div>
          </div>
            <div class="client">
                 <p>Client</p>
                <select name="clients" id="clients" v-model="updatedAdminPickup.clientId">
                  <option :value="adminPickup.clientId">{{this.$store.state.allClients.find(client => client.userId == this.adminPickup.clientId).lastName}}, {{this.$store.state.allClients.find(client => client.userId == adminPickup.clientId).firstName}}</option>
                  <option v-for="client in this.$store.state.allClients.filter(client => client.userId != adminPickup.clientId)" :key="client.userId" :value="client.userId">
                  {{client.lastName}}, {{client.firstName}}
                  </option>
                </select>
            </div>
            <div class="driver">
              <p>Driver</p>
              <select name="drivers" id="drivers" v-model="updatedAdminPickup.driverId">
                  <option :value="adminPickup.driverId">{{this.$store.state.allDrivers.find(driver => driver.driverId == this.adminPickup.driverId).lastName}}, {{this.$store.state.allDrivers.find(driver => driver.driverId == adminPickup.driverId).firstName}}</option>
                  <option v-for="driver in this.$store.state.allDrivers.filter(driver => driver.driverId != adminPickup.driverId)" :key="driver.driverId" :value="driver.driverId">
                  {{driver.lastName}}, {{driver.firstName}}
                  </option>
              </select>
            </div>

          <div class="weight">
            <h4>Weight:</h4>
            <input type="number" name="weight" id="weight" v-bind:placeholder="this.updatedAdminPickup.weight" v-model="updatedAdminPickup.weight">
          </div>
        
        
        <div class="actions">
          <select name="status" id="status" v-model="updatedAdminPickup.status">
            <option :value="this.adminPickup.status">{{this.adminPickup.status}}</option>
            <option v-for="status in this.statuses.filter(status => status.lowercase != this.adminPickup.status.toLowerCase())" :key="status.id" :value="status.status">{{status.status}}</option>
          </select>
          <div v-show="this.adminPickup.status.toLowerCase() != 'pending'">
          <p>Completed:</p>
          <p>{{
              new Date(adminPickup.dateTimeCompleted).toLocaleString("en-US", {
                year: "numeric",
                month: "long",
                day: "numeric",
                hour: "numeric",
                hour12: true,
              })
            }}
          </p>
          </div>
          <button @click="updateAdminPickup">Update!</button>
         </div>
        </div>
</template>

<script>
import PickupService from '../services/PickupService'

export default {
  name: 'admin-component',
  props: ["adminPickup"],
  data(){
    return {
      updatedAdminPickup: {
        pickUpId: '',
        clientId: '',
        driverId: '',
        dateTimeCompleted: '',
        dateTimeRequested: '',
        streetAddress: '',
        city: '',
        state: '',
        zip: '',
        status: '',
        weight: '',
        brownBinFull: '',
        clearBinFull: '',
        greenBinFull: ''
      },
      statuses: [
        {lowercase: "pending",
        status: "Pending"},
        {lowercase: "completed",
        status: "Completed"},
        {lowercase:"rejected",
        status: "Rejected"}
      ],
      
    }

  },

  methods: {
    updateAdminPickup(){
       PickupService.adminUpdatePickup(this.updatedAdminPickup)
       .then((response) => {
          if (response.status){
            //filter our existing list of pickups...
            let updatedAdminPickups = this.$store.state.adminPickups.filter(pickup => pickup.pickUpId != this.updatedAdminPickup.pickUpId);
            //add the updated, returned response...
            updatedAdminPickups.push(response.data)

            //commit it to the store so it renders.
            this.$store.commit("SET_ADMIN_PICKUPS", updatedAdminPickups);
          }
       })
    }



  },

  created(){
    for (const key in this.updatedAdminPickup) {
          this.updatedAdminPickup[key] = this.adminPickup[key];
      }
  }

}
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  max-width: 100%;
}


.main-content {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  grid-template-rows: 1fr 1fr;
  grid-template-areas:
  "address address client bins weight"
  "time time driver bins actions";
  background-color: rgba(0, 144, 0, 0.1);
  border: 1px solid white;
  border-radius: 8px;
  padding: .5rem;

}

.actions {
  grid-area: actions;
}

.address {
  grid-area: address;
}

.time-requested {
  grid-area: time;
}
.bins {
  grid-area: bins;
}
.client{
  grid-area: client;
}
.driver{
  grid-area: driver;
}

.weigth {
  grid-area: weight;
}
.update{
  display: flex;
}


button {
  margin-right: 10px;
}

#sidebar {
  vertical-align: middle;
  display: inline-block;
  box-shadow: none;
  padding: calc(0.5rem + 6rem + 1rem) 1rem 0.5rem;
  font-size: 0.9rem;
  z-index: 4;
  left: 0;
  margin: 0;

  display: flex;
  flex-direction: column;
  vertical-align: baseline;
  white-space: normal;
}


.assign-button {
  background-color: mediumseagreen;
}
.other-content {
  border: 2px solid rgb(11, 129, 184);
}
.pending {
  background-color: rgb(151, 202, 226);
}
.completed {
  background-color: rgb(255, 249, 212);
}
.rejected {
  background-color: rgb(226, 176, 164);
}
</style>