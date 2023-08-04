<template>
  <div class="client-row">
      <div class="contact">
          <p>Name:  {{client.lastName}}, {{client.firstName}}</p>
          <p>Email:  {{client.emailAddress}}</p>
          <p>Phone:  {{client.phoneNumber}}</p>
      </div>
      <div class="address">
          <p>Address:</p>
          <p>{{this.address.streetAddress}}</p>
          <p>{{this.address.city}}, {{this.address.state}} {{this.address.zip}}</p>
      </div>
      <div class="update">
        <button @click="updateClient">Update Info</button>
        <button @click="deleteClient">Delete Client</button>
      </div>
  </div>
</template>

<script>
import ClientService from "../services/ClientService"

export default {
    name: "client-component",
    props: ["client"],
    data(){
        return {
            address: {}
        }
    },
    methods: {
        deleteClient(){
            ClientService.adminDeleteClientById(this.client.userId)
            .then(response => {
                if (response.status){
                    let newClientsList = this.$store.state.allClients.filter(client => client.userId != this.client.userId);
                    this.$store.commit("SET_ALL_CLIENTS", newClientsList);
                }
            })
        },
        updateClient(){
            this.$router.push(`/admin/clients/update/${this.client.userId}`)
        }


    },
    created(){
        ClientService.getClientAddress(this.client.userId)
        .then(response => this.address = response.data)
    }



}
</script>

<style scoped>
        .client-row {
            display: grid;
            grid-template-columns: 1fr 1fr 10%;
            grid-template-rows: 1fr;
      
    
            background-color: rgba(0, 144, 0, 0.1);
            border: 1px solid white;
            border-radius: 8px;
            padding: .5rem;

        }

        .update {
            display: flex;
            flex-direction: column;
            gap: 1rem;
        }
</style>