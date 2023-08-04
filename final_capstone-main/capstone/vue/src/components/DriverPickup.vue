<template>
  <div>
<h2>Driver Pickup Page</h2>


<div class="driver-pickups">
    <h3>{{ client.address }}</h3>
    <button @click="completePickup">Complete</button>
    <button @click="rejectPickup">Reject</button>
    <button @click="toggleRouteAssignment">
        {{ assignedToDriver ? 'Unassign Route' :'Assign Route' }}
    </button>


    <!--iterate over pickups and render DriverPickup component for each -->
    <driver-pickup
    v-for="pickup in pickups" 
    :key="pickup.id"
    :client="pickup.client"
    :assignedToDriver="pickup.assignedToDriver"></driver-pickup>
</div>
  </div>
  
</template>

<script>
 
 import {  } from "module";

export default {

components: {

    //add driver components from imported
},


//array to store pickups data 
data(){
    return {
        pickups: [],
    };
},

//call a method to fetch the pickups data
// created(): {
//     this.fetchPickups();
// },

//fetch pickups from API and then assign that to pickups array
methods: {
    fetchPickups() {
        this.pickups = [
            {
                id: 0,
                client: {
                address: '321 address st'
                },
                assignedToDriver: true,
            },
        ];

    }
}
};


</script>

<style>

</style>