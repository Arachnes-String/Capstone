<template>
  <form @submit.prevent="submitForm" class="pickup-form">
    <div class="form-group">
      <label for="requestedDateTime">Date and Time:</label>
      <input
        type="datetime-local"
        id="requestedDateTime"
        v-model="requestedDateTime"
        required
      />
    </div>
    <div class="form-group">
      <label for="weight">Weight of Pickup (lbs):</label>
      <input type="number" id="weight" v-model="weight" required />
    </div>
    <div class="form-group">
      <label>Bins (At least one must be full to schedule pickup):</label>
      <div class="checkbox-group">
        <div class="checkbox-item">
          <label for="greenBin" class="checkbox-label">
            Green Bin
            <br />
            <i class="fa-solid fa-trash-can fa-xl" style="color: #4a7c59"></i>
          </label>
          <input type="checkbox" id="greenBin" v-model="bins.green" />
        </div>
        <div class="checkbox-item">
          <label for="brownBin" class="checkbox-label">
            Brown Bin
            <br />
            <i class="fa-solid fa-trash-can fa-xl" style="color: #795548"></i>
          </label>
          <input type="checkbox" id="brownBin" v-model="bins.brown" />
        </div>
        <div class="checkbox-item">
          <label for="clearBin" class="checkbox-label">
            Clear Bin
            <br />
            <i class="fa-solid fa-trash-can fa-xl" style="color: #bebebe"></i>
          </label>
          <input type="checkbox" id="clearBin" v-model="bins.clear" />
        </div>
      </div>
    </div>
    <button class="submit-button" :disabled="!isAtLeastOneBinFull()">
      Schedule Pickup
    </button>
    <error-component v-if="error" :error-message="error" />
  </form>
</template>

<script>
import PickupService from "../services/PickupService";
import ErrorComponent from "./ErrorComponent.vue";

export default {
  components: {
    ErrorComponent,
  },

  data() {
    return {
      requestedDateTime: "",
      weight: "",
      bins: {
        green: false,
        brown: false,
        clear: false,
      },
      error: "", //Line to initialize the error property
    };
  },

  methods: {
    isDateValid() {
      if (!this.requestedDateTime) {
        return false;
      }
      const selectedDate = new Date(this.requestedDateTime);
      const currentDate = new Date();
      const minDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000);

      return selectedDate >= minDate;
    },
    isWeightValid() {
      if (!this.weight) {
        return false;
      }
      const maxWeight = 250;
      return this.weight <= maxWeight;
    },
    schedulePickup() {
      if (!this.isDateValid()) {
        window.alert(
          "Invalid pickup date. Please select a date and time that is at least 24 hours in advance."
        );
        return;
      }
    },
    submitForm() {
      if (!this.isAtLeastOneBinFull()) {
        this.error = "At least one bin must be full to schedule pickup.";
        return;
      }
      if (!this.requestedDateTime || !this.weight) {
        window.alert("Please fill in all the required fields.");
        return;
      }

      if (!this.isDateValid()) {
        this.error =
          "Invalid pickup date. Please select a date and time that is at least 24 hours in advance.";
        return;
      }

      if (!this.isWeightValid()) {
        this.error = "The weight must not exceed 250 lbs.";
        return;
      }

      let payload = {
        dateTimeRequested: this.requestedDateTime,
        clientId: this.$store.state.user.id,
        clearBinFull: this.bins.clear,
        greenBinFull: this.bins.green,
        brownBinFull: this.bins.brown,
        weight: this.weight,
      };

      PickupService.submitNewPickup(payload)
        .then((response) => {
          if (response.status) {
            this.$router.push("/client");
          }
        })
        .catch((error) => {
          console.error("Error submitting the pickup form:", error);
          // Handle the error here, such as displaying an error message to the user
        });
    },
    isAtLeastOneBinFull() {
      return Object.values(this.bins).some((bin) => bin === true);
    },
  },
};
</script>

<style scoped>
.pickup-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 400px;
  margin: 0 auto;
}

.form-group {
  margin-bottom: 20px;
  text-align: center;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  color: #000;
  font-weight: bold;
}

.checkbox-group {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}

.checkbox-item {
  margin-bottom: 10px;
}

.checkbox-label {
  display: flex;
  align-items: center;
  color: #000;
}

.checkbox-label input[type="checkbox"] {
  margin-left: 5px;
}

.pickup-form input[type="date"],
.pickup-form input[type="number"],
.pickup-form input[type="checkbox"] {
  width: 100%;
  padding: 10px;
  margin: 10px 0px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

.pickup-form input[type="checkbox"] {
  width: 100px;
  height: 20px;
}

.submit-button {
  background-color: #4a7c59;
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  font-weight: bold;
  text-transform: uppercase;
}

.submit-button:hover {
  background-color: #45a049;
}

.submit-button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
  opacity: 0.7;
}

.pickup-form input[type="datetime-local"] {
  /* Existing styles */

  /* Add or modify the following styles */
  font-family: inherit; /* Inherit the font-family from the parent element */
  font-size: 14px; /* Set the desired font-size */
 
}
</style>
