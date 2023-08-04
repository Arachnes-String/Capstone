<template>
  <div class="container">
    <h1>Prizes:</h1>
    <div v-for="prize in prizes" :key="prize.prizeId" class="prize">
      <div class="name">{{ prize.name }}
      </div>
      <div>{{ prize.creditValue }} Credits</div>
      <div><button class="delete" @click="deletePrize(prize.prizeId)">Delete</button>
        <button class="update" @click="populateEditContainer(prize)">Update</button>
      </div>
    </div>
    <div class="edit-prize-form" v-if="toggleEdit">
    <div class="header"><h2>Edit Prize:</h2></div>
          <label for="Name">Edit Prize Name</label>
          <input type="text" id="name"  v-model="editPrize.name">
          <label for="credits">Edit Credit Value</label>
          <input type="number" id="credits" v-model="editPrize.creditValue">
          <button class="cancel" @click="cancelEdit">Cancel Edit</button>
          <button class="update" @click="submitEdit">Save Changes</button>
    </div>
    <div class="add-prize-form">
      <div class="header"><h2>Add A Prize:</h2></div>
      <label for="Name">Prize Name</label>
      <input type="text" id="name" placeholder="Enter Prize Name" v-model="newPrize.name">
      <label for="credits" class="value">Credit Value</label>
      <input type="number" id="credits" placeholder="Credit Amount" v-model="newPrize.creditValue">
      <button @click="addPrize">Add Prize</button>
    </div>
  </div>
</template>

<script>
import PrizeService from "../services/PrizeService";

export default {
  data() {
    return {
      prizes: [],
      newPrize: {
        name: '',
        creditValue: ''
      },
      toggleEdit: false,
      editPrize: {
        prizeId: '',
        name: '',
        creditValue: ''
      },
    };
  },
  mounted() {
    this.fetchPrizes();
  },
  methods: {
    fetchPrizes() {
      PrizeService.getPrizes()
        .then(response => {
          this.prizes = response.data; 
        })
        .catch(error => {
          console.error(error);
        });
    },
    deletePrize(id){
      PrizeService.deletePrize(id)
      .then(response => {
        if (response.status){
          this.fetchPrizes();
        }
      });
    },
    addPrize(){
      PrizeService.createPrize(this.newPrize)
      .then(response => {
        if (response.status){
          for (const key in this.newPrize){
            this.newPrize[key] = '';
          }
          this.fetchPrizes();
        }
      })
    },
    populateEditContainer(prize){
      
      for(const key in prize){
        this.editPrize[key] = prize[key];
      }
        this.toggleEdit = true;
    },
    cancelEdit(){
      this.toggleEdit = false;
    },
    submitEdit(){
      PrizeService.updatePrize(this.editPrize)
      .then(response => {
        if (response.status) {
          this.toggleEdit = false;
          this.fetchPrizes();
        }
      })
    }
  },
};
</script>

<style scoped>
.container{
  padding: 20px
}

.prize{
  display:flex;
  justify-content: space-between;
  padding: 20px;
  border: 2px solid #4a7c59;
  text-align: center;
  margin: 20px 20px;
  background-color: rgba(242, 242, 242, 0.8);
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.5);
  border-radius: 8px;
  position: relative;
  overflow: hidden;
}

.update {
  margin: 10px;
}

button{
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

button:hover{
  background-color: #45a049;
}

.add-prize-form {

  padding: 20px;
  border: 2px solid #4a7c59;

  margin: 20px 20px;
  background-color: rgba(242, 242, 242, 0.8);
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.5);
  border-radius: 8px;

}

.edit-prize-form {
 padding: 20px;
  border: 2px solid #4a7c59;

  margin: 20px 20px;
  background-color: rgba(242, 242, 242, 0.8);
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.5);
  border-radius: 8px;

}

.add-prize-form div {
  display: flex;
  justify-content: space-evenly;
}

.add-prize-form{
  display: flex;
  flex-direction: column;
  justify-content: ;
}
input#name{
  width: 200px;
}
input#credits {
  width: 200px;
}






</style>