<template>

  <div class="account-main" role="img" aria-label="Wildflowers in Multiple Colors">
    <div class="account-left">
      <admin-contact-info
        v-if="!this.$store.state.editClient"
        :client="this.client"
      />
      <button v-if="!this.$store.state.editClient" @click="editContactInfo">
        Edit Contact Info
      </button>
      <admin-edit-contact-info
        :client="this.client"
        v-if="this.$store.state.editClient"
      />
    </div>
    <div class="account-right">
      <admin-client-address
        :clientAddress="this.clientAddress"
        v-if="!this.$store.state.editAddress"
      />
      <admin-edit-address v-if="this.$store.state.editAddress" />
      <button v-if="!this.$store.state.editAddress" @click="changeAddress">
        Change Address
      </button>
    </div>
  </div>
</template>

<script>
import ClientService from "../../services/ClientService";
import AdminClientAddress from './AdminClientAddress.vue';
import AdminContactInfo from './AdminContactInfo.vue';
import AdminEditContactInfo from './AdminEditContactInfo.vue';
import AdminEditAddress from './AdminEditAddress.vue';



export default {
  name: "admin-update-account-main",
  components: {
    AdminClientAddress,
    AdminContactInfo,
    AdminEditContactInfo,
    AdminEditAddress,



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
    ClientService.getClient(this.$route.params.id).then((response) =>
      this.$store.commit("SET_CLIENT", response.data)
    );

    ClientService.getClientAddress(this.$route.params.id).then((response) =>
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
  background-image: linear-gradient(
      rgba(300, 300, 300, 0.5),
      rgba(300, 300, 300, 0.5)
    ),
    url(/index/wildflowers.jpg);
  background-size: cover;
  background-position: center;
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