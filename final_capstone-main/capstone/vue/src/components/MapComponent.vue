<template>
  <div class="container">
    <!-- The map container -->
    <div class="map-container">
    <div id="map"></div>
    </div>

    <!-- Button to generate the route -->
    <button @click="generateRoute">Create Route</button>

    <!-- Error component to display any errors -->
    <error-component v-if="error" :error-message="error" />
  </div>
</template>

<script>
import ErrorComponent from "./ErrorComponent.vue";

export default {
  components: {
    ErrorComponent,
  },
  name: "map-component",

  data() {
    return {
      map: null,
      mapCenter: { lat: 39.9612, lng: -82.9988 },
      //we could make the above button say "update route" once they already have a route
      error: null,
    };
  },
  computed: {
    addresses: function () {
      // Get the driver pickups from the state
      let pickups = this.$store.state.driverPickups;

      // Start with the address of the recycling center in Dublin
      //this is the address of a recyling center in dublin, it will, currently, always be the starting location.
      let addresses = ["6555 Shier Rings Rd, Dublin, OH, 43016"];

      // Loop through the pickups and add their addresses to the addresses array
      for (let i = 0; i < pickups.length; i++) {
        if (
          (pickups[i].driverId == this.$store.state.user.id) &
          (pickups[i].status.toLowerCase() == "pending")
        ) {
          addresses.push(
            `${pickups[i].streetAddress}, ${pickups[i].city}, ${pickups[i].state}, ${pickups[i].zip}`
          );
        }
      }

      return addresses;
    },
  },
  methods: {
    initMap() {
      // Initialize the map with the provided map center coordinates
      console.log(this.addresses);
      this.map = new window.google.maps.Map(document.getElementById("map"), {
        center: this.mapCenter,
        zoom: 14,
        maxZoom: 20,
        minZoom: 3,
        streetViewControl: true,
        mapTypeControl: true,
        fullscreenControl: true,
        zoomControl: true,
      });

      // Set custom styles to hide points of interest on the map
      let noPOIStyle = [
        {
          featureType: "poi",
          elementType: "labels",
          stylers: [{ visibility: "off" }],
        },
      ];
      this.map.setOptions({ styles: noPOIStyle });
      //thanks Andy
    },

    generateRoute() {
      //   for(let i=0; i < this.locations.length; i++) {

      //     if (this.locations[i].trim().length === 0) {
      //       window.alert("Location cannot be empty");
      //       return;
      //     }
      //   }

      // Initialize the map
      this.initMap();

      // Create the DirectionsService and DirectionsRenderer
      this.routeService = new window.google.maps.DirectionsService();
      this.routeRendererService = new window.google.maps.DirectionsRenderer();
      this.routeRendererService.setMap(this.map);

      let myWaypoints = [];

      /*
        The API expects a single waypoint to be an object like this:
        
        {
          location: "123 somewhere St...",
          stopover: true
        }
      */

      // Create waypoints from the addresses array
      for (let i = 1; i < this.addresses.length - 1; i++) {
        myWaypoints.push({
          location: this.addresses[i],
          stopover: true,
        });
      }
      try {
        // Request the route from the DirectionsService
        this.routeService
          .route({
            origin: this.addresses[0],
            destination: this.addresses[this.addresses.length - 1],
            waypoints: myWaypoints,
            travelMode: window.google.maps.TravelMode.DRIVING,
            avoidTolls: true,
            optimizeWaypoints: true,
          })
          .then((result) => {
            // Display the route on the map using the DirectionsRenderer
            this.routeRendererService.setDirections(result);
          })
          .catch((error) => {
            console.log(error + "Could not generate route");
            this.error = "Could not generate route";
          });
      } catch (error) {
        // Simple error catch. Display in console log and in the component
        console.log("An error occurred while generating the route:", error);
        this.error = "An error occured while generating the route.";
      }
    },
  },
  mounted() {
    // Initialize the map on component mount
    this.initMap();
  },
};
</script>

<style scoped>
.map-container {
  height: 35rem;
  width: 60vw;
}

#map {
  margin: 25px;
  height: 100%;
  border-radius: 8px;
}

button {
  background-color: #4a7c59;
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  font-weight: bold;
  text-transform: uppercase;
  display: block;
  margin: 10px 20px;
  width: 500px;
  margin-left: 350px;
}

button:hover {
  background-color: #45a049;
}
</style>