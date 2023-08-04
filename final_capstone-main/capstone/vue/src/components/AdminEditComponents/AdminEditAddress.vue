<template>
<div>
  <h2>Enter New Address..</h2>
  <form class="client-address" @submit.prevent="handleSubmit">
      <div>
      <label class="first-name">Street Address:</label>
      <input type="text" placeholder="Street Address" v-model="updatedAddress.streetAddress" class="input-field"/>
      </div>
      <br/>
      <div>
      <label class="last-name">City:</label>
      <input type="text" placeholder="City" v-model="updatedAddress.city" class="input-field"/>
      </div>
      <br/>
      <div>
      <label class="phone-number">State:</label>
      <input type="text" placeholder="State" v-model="updatedAddress.state" class="input-field"/>
      </div>
      <br/>
      <div>
      <label class="email-address">Zip:</label>
      <input type="text" placeholder="Zip" v-model="updatedAddress.zip" class="input-field"/>
      </div>
      <br/>
      <div class="edit-address-button">
      <input type="submit" />
      <button class="cancel" @click.prevent="cancelEdit">Cancel</button>
      </div>

  </form>
  </div>
</template>

<script>
import ClientService from '../../services/ClientService'

export default {
    name: "admin-edit-address",
    data(){
        return {
            updatedAddress: {
                clientId: this.$router.params.clientId,
                streetAddress: '',
                city: '',
                state: '',
                zip: '',
            }
        }
    },
    methods: {
        handleSubmit(){
            
            console.log(this.updatedAddress);
            ClientService.updateAddress(this.$router.params.id, this.updatedAddress)
            .then(response => this.$store.commit("SET_CLIENT_ADDRESS", response.data))
            

            this.$store.commit("SET_EDIT_ADDRESS", false);
        },
        cancelEdit(){
            this.$store.commit("SET_EDIT_ADDRESS", false);
        }
    }


}
</script>

<style scoped>
.client-address div{
  display: flex;
  justify-content: space-between;
  font-weight: 400;
  font-size: 25px;
    height: 40px;
}

button, input[type=submit] {
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

button:hover, input[type=submit]:hover {
  background-color: #45a049;
}

.edit-address-button{
    position: relative;
    bottom: -5px;
}


.client-address .input-field {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  width: 300px;
  font-size: 16px;
}

h2 {
  text-align: center;
}

</style>