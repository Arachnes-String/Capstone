<template>
<div class="container" role="img" aria-label="Wildflowers in Multiple Colors">
  <div class="edit-driver">
      <div class="heading"><h1>Edit Driver</h1>
        <p>Any fields left unedited will not be changed</p>
      </div>

      <div class="name">
          <label for="firstName"> First Name: </label>
          <input id="firstName" type="text" :placeholder="this.$store.state.driver.firstName" v-model="updatedDriver.firstName">
          <label for="lastName"> Last Name: </label>
          <input type="text" id="lastName" :placeholder="this.$store.state.driver.lastName" v-model="updatedDriver.lastName">
      </div>
      <div class="settings">
          <label for="zip"> Zip: </label>
          <input type="text" id="zip" :placeholder="this.$store.state.driver.zip" v-model="updatedDriver.zip">
          <label for="radius"> Radius </label>
          <input type="number" id="radius" :placeholder="this.$store.state.driver.radius" v-model="updatedDriver.radius">
      </div>
      <div class="submit">
          <button @click="updateDriver">Update!</button>
      </div>
  </div></div>
</template>

<script>
import DriverService from '../../services/DriverService'
export default {

data(){
    return {
        updatedDriver: {
            driverId: '',
            firstName: '',
            lastName: '',
            radius: '',
            zip: ''
        }
        }
    },


created(){
    DriverService.getDriverById(this.$route.params.id)
    .then(response => this.$store.commit("SET_DRIVER", response.data));
},
methods: {
    updateDriver: function(){

        //first fill in any values the user left blank...
      for (const key in this.updatedDriver) {
        if (this.updatedDriver[key] === "") {
          this.updatedDriver[key] = this.$store.state.driver[key];
        }
      }

      DriverService.adminUpdateDriver(this.updatedDriver)
      .then(response => {
          if (response.status){
              this.$router.push('/admin/drivers')
          }
      })

    }
}


}
</script>

<style scoped>

.container {
    
    height: 85vh;
    background-image: linear-gradient(
      rgba(300, 300, 300, 0.5),
      rgba(300, 300, 300, 0.5)
    ),
    url(/index/wildflowers.jpg);
  background-size: cover;
  background-position: center;
    display: flex;


}

.edit-driver {
    text-align: center;
    border: 1px solid green;
    border-radius: 8px;
    display: flex; 
    flex-direction: column;
    gap: 2rem;
    width: 80%;
    background: lightgrey;
    margin: 15rem auto;
    

}


</style>