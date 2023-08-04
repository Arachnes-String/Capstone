<template>

  <div class="account-main">
    <div class="account-left">
      <contact-info
        v-if="!this.$store.state.editClient"
        :client="this.client"
      />
      <button v-if="!this.$store.state.editClient" @click="editContactInfo">
        Edit Contact Info
      </button>
      <edit-contact-info
        :client="this.client"
        v-if="this.$store.state.editClient"
      />
    </div>
    <div class="account-right">
      <client-address
        :clientAddress="this.clientAddress"
        v-if="!this.$store.state.editAddress"
      />
      <edit-address v-if="this.$store.state.editAddress" />
      <button v-if="!this.$store.state.editAddress" @click="changeAddress">
        Change Address Info
      </button>
    </div>
  </div>
</template>

<script>
import ClientService from "../services/ClientService";
import ContactInfo from "../components/ContactInfo.vue";
import ClientAddress from "../components/ClientAddress.vue";
import EditContactInfo from "./EditContactInfo.vue";
import EditAddress from "./EditAddress.vue";
export default {
  name: "AccountMain",
  components: {
    ContactInfo,
    ClientAddress,
    EditContactInfo,
    EditAddress,
  },

  computed: {
    client: function () {
      return this.$store.state.client;
    },
    clientAddress: function () {
      return this.$store.state.clientAddress;
    },
  },

  methods: {
    editContactInfo() {
      this.$store.commit("SET_EDIT_CLIENT", true);
    },

    changeAddress() {
      this.$store.commit("SET_EDIT_ADDRESS", true);
    },
  },

  created() {
    ClientService.getClient(this.$store.state.user.id).then((response) =>
      this.$store.commit("SET_CLIENT", response.data)
    );

    ClientService.getClientAddress(this.$store.state.user.id).then((response) =>
      this.$store.commit("SET_CLIENT_ADDRESS", response.data)
    );
  },
};
</script>

<style scoped>
.account-main {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
}

.account-main > div {
  min-height: 250px;
  width: 50vw;
  margin: 50px;
  border: 2px solid #4a7c59;
  padding: 20px;
  background-color: rgba(242, 242, 242, 0.8);
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.5);
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
  margin-top: inherit;
  display: block;
  margin-left: auto;
  margin-right: auto;
}

button:hover {
  background-color: #45a049;
}

.admin-main {
 display: flex;
  grid-template-rows: 5% 80% 15%;
  grid-template-columns: 1fr 1fr;
  grid-template-areas: 
  "accountleft-account-right"
  "account-left account-right";
  background-color: #98fccd;
  height: 100vh;
}

.account-left {
   background-color: #7b45a0;
}

.account-right {
   background-color: #f80e2d;
}
</style>