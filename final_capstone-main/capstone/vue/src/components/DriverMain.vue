<template>
  <div class="driver-main" role="img" aria-label="Zoomed In Water Droplet on Green Leaf With Sharp, Red Edges">
    <div class="map-route-container">    
      <map-component />  
      <my-route-container />
    </div>
    <div class="unassigned">
      <unassigned-pickups-container />
    </div>
  </div>
</template>

<script>
import DriverService from "../services/DriverService";
import MapComponent from "../components/MapComponent.vue";
import MyRouteContainer from "./MyRouteContainer.vue";
import UnassignedPickupsContainer from "./UnassignedPickupsContainer.vue";

export default {
  name: "DriverMain",

  components: {
    MapComponent,
    MyRouteContainer,
    UnassignedPickupsContainer,
  },

  data() {
    return {
      pickups: [],
    };
  },

  computed: {
    pickupStatus() {
      return this.assignedToDriver ? "assigned" : "unassigned";
    },
  },

  created() {
    DriverService.getDriverPickupsForDriverId(this.$store.state.user.id).then(
      (response) => this.$store.commit("SET_DRIVER_PICKUPS", response.data)
    );

    DriverService.getDriverById(this.$store.state.user.id).then(
      response => this.$store.commit("SET_DRIVER", response.data)
    );
  },

  methods: {

  },
};
</script>

<style scoped>
.driver-main{
  height: 100%;
  max-height: 100vh;
  background-image: linear-gradient(
    rgba(300, 300, 300, 0.5),
    rgba(300, 300, 300, 0.5)
  ),
  url(../img/driver.jpg);
  background-size: cover;
  background-position: center;
}

.map-route-container{
  display: inline-flex;
}
.unassigned-pickups-container {
 margin-top: 0rem; /* Add 'rem' unit for margin-top */
  max-height: 20rem;
  overflow-y: auto; /* Enable vertical scrolling */
}
</style>