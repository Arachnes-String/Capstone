<template>
  <div>
    <h2>---- Pick Ups! ----</h2>
    <div class="options">
      <label for="filter" style="font-weight: 600">Filter Pickups...</label>
      <select name="filter" id="filter" v-model="filter" @change="applyFilter">
        <option value="">Show All Pickups</option>
        <option value="pending">Show Pending Pickups</option>
        <option value="completed">Show Completed Pickups</option>
        <option value="rejected">Show Rejected Pickups</option>
      </select>
    </div>
    <div class="pickup-container">
      <p v-if="pickups.length === 0 && filter === 'pending'">
        No pending pickups!
      </p>
      <p v-if="pickups.length === 0 && filter === 'completed'">
        No completed pickups!
      </p>
      <p v-if="pickups.length === 0 && filter === 'rejected'">
        No rejected pickups!
      </p>
      <pickup-component
        v-for="pickup in pickups"
        :key="pickup.id"
        :pickup="pickup"
      />
    </div>
  </div>
</template>

<script>
import PickupComponent from "./PickupComponent.vue";
import PickupService from "../services/PickupService.js";
export default {
  components: {
    PickupComponent,
  },
  data() {
    return {
      filter: "",
  
    };
  },
  computed: {
    pickups: function () {
      if (this.filter === "pending") {
        return this.$store.state.pickups.filter(
          (pickup) => pickup.status.toLowerCase() === "pending"
        );
      }
      if (this.filter === "completed") {
        return this.$store.state.pickups.filter(
          (pickup) => pickup.status.toLowerCase() === "completed"
        );
      }
      if (this.filter === "rejected") {
        return this.$store.state.pickups.filter(
          (pickup) => pickup.status.toLowerCase() === "rejected"
        );
      }
      return this.$store.state.pickups;
    },
  },
  created() {
    PickupService.getPickUpsByClientId(this.$store.state.user.id).then(
      (response) => {
        this.$store.commit("SET_PICKUPS", response.data);
        this.applyFilter();
      }
    );
  },
  methods: {
    applyFilter() {
      // Force the filteredPickups computed property to update
      this.filteredPickups = this.pickups;
    },
  },
};
</script>
<style scoped>
h2 {
  text-align: center;
  margin: 0;
}
.pickup-container {
  display: flex;
  justify-content: flex-start;
  flex-direction: column;
  align-items: center;
  background-color: rgba(0, 144, 0, 0.1);
  border: 1px solid white;
  border-radius: 8px;
  overflow-y: scroll;
  max-height: 650px;
  height: 564px;
}
</style>\