<template>
<div>
  <h2>
    Edit Contact Info...
  </h2>
  <p>
    Any fields left empty will not be updated...
  </p>
  <form class="contact-info" @submit.prevent="handleSubmit">
    <div>
      <label class="first-name">First Name:</label>
      <input
        type="text"
        placeholder="First Name"
        v-model="updatedClient.firstName"
        class="input-field"
      />
    </div>
    <br />
    <div>
      <label class="last-name">Last Name:</label>
      <input
        type="text"
        placeholder="Last Name"
        v-model="updatedClient.lastName"
        class="input-field"
      />
    </div>
    <br />
    <div>
      <label class="phone-number">Phone Number:</label>
      <input
        type="text"
        placeholder="Phone Number"
        v-model="updatedClient.phoneNumber"
        class="input-field"
      />
    </div>
    <br />
    <div>
      <label class="email-address">Email Address:</label>
      <input
        type="text"
        placeholder="Email Address"
        v-model="updatedClient.emailAddress"
        class="input-field"
      />
    </div>
    <br />
    <div class="edit-button">
      <input type="submit" /><button class="cancel" @click.prevent="cancelEdit">
        Cancel
      </button>
    </div>
    </form>
  </div>
  
</template>

<script>
import ClientService from "../../services/ClientService";

export default {
  name: "admin-edit-contact-info",
  props: ["client"],
  data() {
    return {
      updatedClient: {
        firstName: "",
        lastName: "",
        addressId: "",
        phoneNumber: "",
        emailAddress: "",
      },
    };
  },
  methods: {
    handleSubmit() {
      //first fill in any values the user left blank...
      for (const key in this.updatedClient) {
        if (this.updatedClient[key] === "") {
          this.updatedClient[key] = this.client[key];
        }
      }

      ClientService.updateClient(
        this.$route.params.id,
        this.updatedClient
      ).then((response) => this.$store.commit("SET_CLIENT", response.data));

      this.$store.commit("SET_EDIT_CLIENT", false);
    },
    cancelEdit() {
      this.$store.commit("SET_EDIT_CLIENT", false);
    },
  },
};
</script>

<style scoped>
.contact-info div {
  display: flex;
  justify-content: space-between;
  font-weight: 400;
  font-size: 25px;
  height: 40px;
}

button,
input[type="submit"] {
  background-color: #4a7c59;
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  font-weight: bold;
  text-transform: uppercase;
  margin-top: inherit;
  display: block;
  margin-left: auto;
  margin-right: auto;
}

button:hover,
input[type="submit"]:hover {
  background-color: #45a049;
}

.edit-button {
  position: relative;
  bottom: -5px;
}

.contact-info .input-field {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: 300px;
  font-size: 16px;
}

h2 {
  text-align: center;
}
p {
  text-align: center;
}
</style>