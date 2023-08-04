<template>
<div class="container" role="img" aria-label="Jumbled Clear and Green Glass Bottles">
  <div class="register-box">
    <form class="user">
        <div class="form-group">
            <label for="username">Username:</label>
        <input type="text" v-model="newUser.username" placeholder="Username">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
        <input type="text" v-model="newUser.password" placeholder="Password">
        </div>
        <div class="form-group">
             <label for="confirmPassword">Confirm Password:</label>
        <input type="text" v-model="newUser.confirmPassword" placeholder="Confirm Password">
        </div>
    </form>

    <div class="register-buttons">
    <div class="form-group">
        <label for="showClient">New Client</label>
        <input type="checkbox" v-model="showClient" @change="toggleCheckbox('client')">
        
    </div>
    <div class="form-group">
        <label for="showDriver">New Driver</label>
        <input type="checkbox" v-model="showDriver" @change="toggleCheckbox('driver')">
    </div>
    </div>

    <form class="clientForm" v-if="showClient">
        <!-- to-do -->
        <!-- add inputs here for client... -->
        <div class="form-group">
        <label for="firstName">First Name:</label>
        <input type="text" v-model="newClient.firstName" placeholder="First Name">
        </div>
        <div class="form-group">
        <label for="lastName">Last Name:</label>
        <input type="text" v-model="newClient.lastName"  placeholder="Last Name">
        </div>
        <div class="form-group">
         <label for="streetAddress">Street Address:</label>
        <input type="text" v-model="newClient.streetAddress"  placeholder="Street Address">
        </div>
        <div class="form-group">
        <label for="city">City:</label>
        <input type="text" v-model="newClient.city"  placeholder="City">
        </div>
        <div class="form-group">
        <label for="state">State:</label>
        <input type="text" v-model="newClient.state"  placeholder="State">
        </div>
        <div class="form-group">
        <label for="zipCode">Zip Code:</label>
        <input type="text" v-model="newClient.zip"  placeholder="Zip">
        </div>
        <div class="form-group">
        <label for="phoneNumber">Phone Number:</label>
        <input type="text" v-model="newClient.phoneNumber" placeholder="Phone Number">
        </div>
        <div class="form-group">
        <label for="emailAddress">Email Address:</label>
        <input type="text" v-model="newClient.emailAddress"  placeholder="Email Address">
        </div>
        
       
        
        
        
        
        
    </form>
    <form class="driverForm" v-if="showDriver">
        <!-- to-do -->
        <!-- add inputs here for driver... -->
        <div class="form-group">
        <label for="firstName">First Name:</label>
        <input type="text" v-model="newDriver.firstName"  placeholder="First Name">
        </div>
        <div class="form-group">
        <label for="lastName">Last Name:</label>
        <input type="text" v-model="newDriver.lastName" placeholder="Last Name">
        </div>
        <div class="form-group">
        <label for="zipcode">Zip Code:</label>
        <input type="text" v-model="newDriver.zip" placeholder="Zip Code">
        </div>
        <div class="form-group">
        <label for="radius">Radius:</label>
        <input type="text" v-model="newDriver.radius" placeholder="Radius">
        </div>
    </form>
    
    <div class="form">
        <label for="serviceTerms">Terms of Service:
          <br/>
        <input type="checkbox" v-model="termsChecked">You acknowledge & agree to have at least one full recycling bin 
        & requests must be made at least 24 hours in advance, before scheduling a pickup.
        
        <button @click="handleSubmit" :disabled="!termsChecked">Submit</button> </label>
        
    </div>
            
    </div>
    
  </div>

  
  
</template>

<script>
import AuthService from "../services/AuthService";
import ClientService from "../services/ClientService";
import DriverService from "../services/DriverService";

export default {
  data() {
    return {
      termsChecked: false,
      //we need a way to only allow one of these two to be true...
      showClient: true,
      showDriver: false,

      //the commented values below are set by the forms above ^
      newUser: {
        //userName
        //password
      },

      newClient: {
        //firstName
        //lastName
        //streetAddress
        //city
        //state
        //zip
        //phoneNumber
        //emailAddress
      },
      newDriver: {
        //firstName
        //lastName
      },
    };
  },
  methods: {
    toggleCheckbox(selectedRole) {
      if (selectedRole === "client") {
        this.showDriver = false;
      } else if (selectedRole === "driver") {
        this.showClient = false;
      }
    },
    handleSubmit() {
      this.newUser.role = this.showClient
        ? "User"
        : this.showDriver & !this.showClient
        ? "Driver"
        : " ";

      AuthService.register(this.newUser)

        .then((response) => {
          if (response.status) {
            if (this.showClient) {
              this.newClient.username = this.newUser.username;
              ClientService.createClient(this.newClient).then((response) => {
                if (response.status) {
                  this.$router.push("/login");
                }
              });
            }

            if (this.showDriver) {
              this.newDriver.username = this.newUser.username;
              DriverService.createDriver(this.newDriver).then((response) => {
                if (response.status) {
                  this.$router.push("/login");
                }
              });
            }
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>


<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  /* height: calc(100vh - 212px); */
  height: 100vh;
  padding: 40px 20px;
  background-image: url("../img/glass-g0e50b85e7_1280111.jpg");
  background-size: cover;
  background-position: center;
}

.register-box {
  width: 600px;
  background-color: rgba(255, 255, 255, 0.7);
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.3);
}

.form-group {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 10px;
  padding: 5px 1px;
  font-weight: 1000;
  font-size: 17px;
}

label {
  width: 150px;
  margin-right: 10px;
}

input[type="checkbox"] {
  margin-right: 5px;
}

button {
  display: block;
  margin: 0 auto;
  margin-top: 40px;
  padding: 15px 20px;
  font-weight: bolder;
  font-size: 17px;
  background-color: darkgrey;
  border-radius: 10%;
}

.form {
  text-align: center;
  margin-top: 20px;
  padding: 5px 1px;
  font-weight: 1000;
  font-size: 17px;
}

.register-buttons{
  display: flex;
  justify-content: space-evenly;
}

.register-buttons .form-group{
  flex-direction: column;
  text-align: center;
}
</style>