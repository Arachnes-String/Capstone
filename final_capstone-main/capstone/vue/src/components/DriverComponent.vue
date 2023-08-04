<template>
  <div class="driver-row">
      <div class="name">
          <p>Name:  {{driver.lastName}}, {{driver.firstName}}</p>
      </div>
      <div class="settings">
          <p>Zip:  {{driver.zip}}</p>
          <p>Radius: {{driver.radius}}</p>
      </div>
      <div class="update">
          <button @click="deleteDriver">Delete</button>
          <button @click="updateDriver">Update</button>
      </div>
  </div>
</template>

<script>
import DriverService from "../services/DriverService"

export default {
name: "driver-component",
props: ["driver"],
methods: {
    deleteDriver(){
        DriverService.adminDeleteDriver(this.driver.driverId)
        .then(response => {
                if (response.status){
                    let newDriverList = this.$store.state.allDrivers.filter(driver => driver.driverId != this.driver.driverId);
                    this.$store.commit("SET_ALL_DRIVERS", newDriverList);
                }
            })
    },
    updateDriver(){
        this.$router.push(`/admin/drivers/update/${this.driver.driverId}`)
    }
}

}
</script>

<style scoped>
    .driver-row {
        display: grid;
        grid-template-columns: 1fr 1fr 10%;
        grid-template-rows: 1fr;
    

        background-color: rgba(0, 144, 0, 0.1);
        border: 1px solid white;
        border-radius: 8px;
        padding: .5rem;
}

</style>