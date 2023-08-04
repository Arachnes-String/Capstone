<template>

    <div class="main-content" :class="getStatusClass(driverPickup.status)">
   
   
        <div class="address">
          <h3>{{ driverPickup.streetAddress }}, {{driverPickup.city}}, {{driverPickup.state}}, {{driverPickup.zip}}</h3>
         </div>

        <div class="time-requested">
          <p>Scheduled For:</p>
          <p>
            {{
              new Date(driverPickup.dateTimeRequested).toLocaleString("en-US", {
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
             <div v-if="this.driverPickup.greenBinFull" class="green">
                 <p>Green <i class="fa-solid fa-recycle" style="color: rgb(96, 189, 3)"></i></p>
              </div>
            <div v-if="this.driverPickup.brownBinFull" class="brown">
               <p>Brown <i class="fa-solid fa-recycle" style="color: rgb(144, 123, 112)"></i></p> 
            </div>
            <div v-if="this.driverPickup.clearBinFull" class="clear">
                <p>Clear <i class="fa-solid fa-recycle" style="color: rgb(191, 191, 238)"></i></p>
            </div>
            
          </div>

          <div class="weight">
            <h4>Weight: {{ driverPickup.weight }}</h4>
          </div>
        
        
        <div class="actions">
          <div class="update" v-if="driverPickup.driverId != 1 && driverPickup.status.toLowerCase() === 'pending'">
            <button  @click.prevent="markPickupComplete">Complete</button>      
            <button @click.prevent="rejectPickup">Reject</button>
          </div>
           <button class="assignbutton" @click="toggleAssignDriver" v-if="driverPickup.status === 'Pending'">{{driverPickup.driverId != 1 ? "Unassign" : "Assign Route to Me" }}</button>
          <div v-if="driverPickup.status.toLowerCase() == 'completed' | driverPickup.status.toLowerCase() =='rejected'">
          <p>Completed:</p>
          <p>{{
              new Date(driverPickup.dateTimeCompleted).toLocaleString("en-US", {
                year: "numeric",
                month: "long",
                day: "numeric",
                hour: "numeric",
                hour12: true,
              })
            }}
          </p>
          </div>
         </div>
        </div>

</template>

<script>
import PickupService from "../services/PickupService";

export default {
  components: {},

  props: ["driverPickup"],

  created() {
    console.log(this.driverPickup);
  },
  methods: {
    //adding colors to filtered lists of diff statuses
    getStatusClass(status) {
      if (status === "Pending") {
        return "pending";
      } else if (status === "Completed") {
        return "completed";
      } else if (status === "Rejected") {
        return "rejected";
      } else {
        return "";
      }
    },

    markPickupComplete() {
      PickupService.updateDriverPickup(
        this.driverPickup.pickUpId,
        "completed"
      ).then((response) => {
        if (response.status) {
          //filter our existing list of pickups...
          let updatedDriverPickups = this.$store.state.driverPickups.filter(
            (pickup) => pickup.pickUpId != this.driverPickup.pickUpId
          );
          //add the updated, returned response...
          updatedDriverPickups.push(response.data);
          //commit it to the store so it renders.
          this.$store.commit("SET_DRIVER_PICKUPS", updatedDriverPickups);
        }
      });
    },
    rejectPickup() {
      PickupService.updateDriverPickup(
        this.driverPickup.pickUpId,
        "rejected"
      ).then((response) => {
        if (response.status) {
          //filter our existing list of pickups...
          let updatedDriverPickups = this.$store.state.driverPickups.filter(
            (pickup) => pickup.pickUpId != this.driverPickup.pickUpId
          );
          //add the updated, returned response...
          updatedDriverPickups.push(response.data);

          //commit it to the store so it renders.
          this.$store.commit("SET_DRIVER_PICKUPS", updatedDriverPickups);
        }
      });
    },

     toggleAssignDriver() {
      // Get the driver's ZIP code from the Vuex store
      const driverZip = this.$store.state.driver.zip;
      // Get the pickup's ZIP code from the driverPickup object
      const pickupZip = this.driverPickup.zip;
      // Determine the driver ID based on the condition
      // If the driverId of driverPickup is not 1, assign 1 as the driverId, otherwise use the user's ID from the Vuex store
      let driverId =
        this.driverPickup.driverId != 1 ? 1 : this.$store.state.user.id;

      // Check if the driver and pickup ZIP codes match
      //Currently broken if you can get the zip to not be null this will work , JUST REMOVE THE !
      if (driverZip == pickupZip || this.driverPickup.driverId == this.$store.state.user.id) {
        this.updateAssignedDriver(driverId);
      } else {
        // If they don't match, display a confirmation dialog
        const confirmMessage =
          "The driver ZIP and pickup ZIP do not match. Are you sure you want to assign this route?";
        // If the user confirms, call the updateAssignedDriver function
        const confirmed = confirm(confirmMessage);

        if (confirmed) {
          this.updateAssignedDriver(driverId);
        }
      }
    },
    updateAssignedDriver(driverId) {
      // Call the PickupService's updateAssignedDriver method with the driverPickup ID and driverId
      PickupService.updateAssignedDriver(this.driverPickup.pickUpId, driverId)
        .then((response) => {
          if (response.status) {
            // Filter the existing list of driverPickups to remove the current pickup
            let updatedDriverPickups = this.$store.state.driverPickups.filter(
              (pickup) => pickup.pickUpId != this.driverPickup.pickUpId
            );
            //add the updated, returned response...
            updatedDriverPickups.push(response.data);

            //commit it to the store so it renders.
            this.$store.commit("SET_DRIVER_PICKUPS", updatedDriverPickups);
          }
        })
        .catch((error) => console.log(error));
    },
  },
};
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  max-width: 100%;
  
}

/* Styles for the main content container */
.main-content {
  display: grid;
  display: flex;
  flex-direction: row;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-rows: 1fr 1fr;
  grid-template-areas:
  "address address bins weight"
  "time time bins actions";
  background-color: rgba(0, 144, 0, 0.1);
  border: 1px solid white;
  border-radius: 8px;
  max-height: 50px;
  padding: 10px;

}
/* Styles for the actions section */
.actions {
  grid-area: actions;
  text-align: center;
  display: -webkit-box;
  
  
}
/* Styles for the address section */
.address {
  grid-area: address;
  text-align: left;
}

.time-requested {
  grid-area: time;
  text-align: left;
}

.bins {
 
  
  grid-area: bins;
  text-align: left;
  flex-direction: column;
}
/* Styles for the weight section */
.weight {
  grid-area: weight;
  text-align: right;
}
.update{
  display: flex;
}


button {
  margin-right: 10px;
}
/* Styles for the sidebar */
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

/* Styles for the pending status */
.pending {
  display: flex;
  scroll-behavior: smooth;
  justify-content:space-between;
  text-align:left;
  padding:-15px;
  max-height: 7rem;
  margin: 5px;
  overflow: none;
  background-color: rgb(151, 202, 226);
}
/* Styles for the completed status */
.completed {
  display: flex;
  scroll-behavior: smooth;
  justify-content: space-evenly;
  text-align:left;
  padding:3px;
  max-height: 7rem;
  margin: -5px;
  overflow: none;
  background-color: rgb(255, 249, 212);
}
/* Styles for the rejected status */
.rejected {
  display: flex;
  scroll-behavior: smooth;
  justify-content: space-evenly;
  text-align:left;
  padding:3px;
  max-height: 7rem;
  margin: -5px;
  overflow: none;
  background-color: rgb(226, 176, 164);
}

.main-content.pending {
 max-height: 100vh;
}

</style>