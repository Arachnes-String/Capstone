<template>
  <div class="all-clients" role="img" aria-label="Wildflowers in Multiple Colors">
      <div class="heading">
          <h1>All Clients</h1>
      </div>
      <div class="client-container">
          <client-component v-for="client in this.$store.state.allClients" :key="client.userId" :client="client" />
      </div>
  </div>
</template>

<script>
import ClientComponent from "./ClientComponent.vue"
import ClientService from "../services/ClientService"

export default {
  name: 'all-clients',
  components: { ClientComponent },
  created(){
      ClientService.adminGetAllClients()
    .then(response => this.$store.commit("SET_ALL_CLIENTS", response.data));


  }

}
</script>

<style>
.all-clients {
  background-image: linear-gradient(
      rgba(300, 300, 300, 08),
      rgba(300, 300, 300, 0.7)
    ),
    url(../img/wildflowers.jpg);
  background-size: cover;
  background-position: center;
  font-weight: 600;
}
</style>