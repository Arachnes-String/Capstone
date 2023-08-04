<template>
<body>
  <div class="container" role="img" aria-label="Ginko Leaves Patter On Stained Glass">
         <div id="banner"><h1>Client Snapshot</h1>
       </div>
       <div v-show="showProgressBar" class="progress-bar">
         <h3>Credit Progress toward...</h3>
         <select name="prizes" id="prizes" v-model="prize">
           <option v-for="prize in this.$store.state.prizes" :key="prize.id" :value="prize">{{prize.name}}</option>
         </select>
          <progress class="credit-progress" :value="userBin.credits" :max="prize.creditValue"></progress>
       </div>
    <div class="links">
      <div class="link-container">
        <h2>Resources</h2>
          <a target="_blank" href="https://www.gpi.org/glass-recycling-facts" style="display: none;">Click Here for Glass Facts & Statistics</a>
          <a target="_blank" href="https://dublinohiousa.gov/city-services/trash-and-recycling-collection/" style="font-size:larger">Local Recyling Rules & Regulations</a>
          <a target="_blank" href="https://www.gpi.org/glass-recycling-facts" style="font-size:larger">Glass Facts & Statistics</a>
          <a target="_blank" href="https://remarkglass.com/" style="font-size:larger">Shop for Items Made With Recycled Glass</a>
          <a target="_blank" :href="generateURL()" class="link-with-icon">
         
          <span class="link-text"> <i class="fas fa-map-marker-alt"></i> Find the nearest Glass Recycling near you!</span>
          </a>
        </div>
    </div>
   
    <div class="client">           
        <pickups-container />
    </div>
    
    <div class="stats">
      <h2>Client Statistics:</h2>
      <credits :credits="this.userBin.credits" />
      <div class="credits"></div>

      <clear-bins :clearBins="this.userBin.clearBins" />
      <div class="clear-bin"></div>

      <green-bins :greenBins="this.userBin.greenBins" />
      <div class="green-bin"></div>

      <brown-bins :brownBins="this.userBin.brownBins" />
      <div class="brown-bin"></div>

      <total-weight :totalWeight="this.userBin.totalWeight" />
      <div class="total-weight"></div>

      <rejections :rejections="this.userBin.rejections" />
      <div class="rejections"></div>
     
    </div>
    
  </div>
</body>
</template>

<script>
import ClientService from "../services/ClientService";
import ClearBins from "./ClearBins.vue";
import GreenBins from "../components/GreenBins.vue";
import BrownBins from "../components/BrownBins.vue";
import Credits from "../components/Credits.vue";
import TotalWeight from "../components/TotalWeight.vue";
import Rejections from "../components/Rejections.vue";
import PickupsContainer from './PickupsContainer.vue';
import PrizeService from '../services/PrizeService';

 


export default {
  name: "ClientMain",

  components: {
    GreenBins,
    ClearBins,
    BrownBins,
    TotalWeight,
    Credits,
    Rejections,
    PickupsContainer,
  },

  data() {
    return {
      credits: 0, // Replace with actual credits value from the client's data
      bins: {},
      prize: {},
      clientZipCode: this.$store.state.clientAddress.zip,
    };
  },


  methods: {
    fetchBins() {
      ClientService.getClientBin(this.$store.state.user.id).then((response) => {
        this.bins = response.data;
      });
    },
      generateURL() {
      const baseURL = "https://recyclenation.com/find/?zip=";
      const familyID = "&family_id=5832708";
      return baseURL + this.clientZipCode + familyID;
    }

  },

  computed: {
    userBin: function () {
      return this.$store.state.userBin;
    },
    showProgressBar: function (){
      return this.$store.state.prizes.length > 0
    }
  },
  created() {
    ClientService.getClientBin(this.$store.state.user.id).then((response) =>
      this.$store.commit("SET_USER_BIN", response.data)
    );
    PrizeService.getPrizes()
    .then(response => {
      this.$store.commit("SET_PRIZES", response.data)
      if (this.$store.state.prizes.length > 0){
        this.prize = this.$store.state.prizes[0];
      }})
      ClientService.getClientAddress(this.$store.state.user.id)
      .then(response => this.$store.commit("SET_CLIENT_ADDRESS", response.data));
    

  },
};
</script>

<style scoped>
body {
 font-family: 'Open Sans', sans-serif'';
}

.credits {
  width: 100%;
  position: absolute;
  top: 20%;
  transform: translateY(-150%);
  text-align: center;
  font-size: 40px;
  font-weight: bold;
  margin: 20px auto;
  font-weight: 100;
  line-height: 25px;
  margin: 30px 20px;
}
.clear-bin {
  width: 100%;
  position: absolute;
  justify-content: space-evenly;
  top: 20%;
  text-align: center;
  font-size: 40px;
  font-weight: bolder;
  margin: 20px auto;
  font-weight: 100;
  line-height: 25px;
  margin: 30px 20px;
}
.green-bin {
  width: 100%;
  position: absolute;
  justify-content: space-evenly;
  transform: translateY(189%);
  top: 20%;
  text-align: center;
  font-size: 40px;
  font-weight: bold;
  margin: 20px auto;
  font-weight: 100;
  line-height: 25px;
  
}
.brown-bin {
  width: 100%;
  position: absolute;
  justify-content: space-around;
  top: 20%;
  text-align: center;
  font-size: 40px;
  font-weight: bold;
  font-weight: 100;
  line-height: 25px;
  
}
.total-weight {
  width: 100%;
  position: absolute;
  justify-content: space-between;
  top: 20%;
  text-align: center;
  font-size: 40px;
  font-weight: bold;
  font-weight: 100;
  line-height: 25px;
 
}

.rejections {
  width: 100%;
  position: absolute;
  justify-content: space-between;
  top: 20%;
  text-align: center;
  font-size: 40px;
  font-weight: bold;
  font-weight: 100;
  line-height: 25px;
  
}



.container {
  height: 100%;
  padding: 0px 20px;
   background-image: linear-gradient(
    rgba(242, 242, 242, 0.8),
    rgba(242, 242, 242, 0.8)
  ), url("../img/ginkoleavesglass.jpg");
  background-size: cover;
  background-position: center;
  display: grid;
  grid-template-rows: 1fr 4fr 2fr;
  grid-template-columns: 1fr 2fr 1fr;
  grid-template-areas: 
  "banner banner banner"
  "links client stats"
  "bar bar bar";
}

.links{
  display: grid;
  grid-area: links;
  margin: 1rem;
}

.stats {
  grid-area: stats;
  margin: 1rem;
}
.stats h2 {
  text-align: center;
}

.client {
 margin: 1rem;
}

#banner {
  grid-area: banner;
  text-align: center;
}

.clientSnapshot {
  width: 100%;
  position: center;
  top: 50%;
  transform: translateY(-100%);
  text-align: center;
  color: #0e461f;

}

.clientSnapshot h1 {
  font-size: 55px;
  text-decoration: underline;
  margin-bottom: 775px;
}

.clientSnapshot h2 {
  font-size: 20px;
  margin: 20px auto;
  font-weight: 100;
  line-height: 25px;
  margin: 30px 20px;
  text-shadow: 0 0 15px rgba(253, 252, 252, 0.99);
}

.counter-box {
  border: 2px solid #4a7c59;
  width: 180px;
  padding: 20px;
  text-align: center;
  margin: 20px auto;
  background-color: rgba(242, 242, 242, 0.8);
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
  margin: 20px auto;
  position: relative;
  overflow: hidden;
}

.counter-box i {
  font-size: 40px;
  margin-top: 0px;
  transition: transform 0.75s ease-in-out;
}

.counter-box:hover i {
  transform: rotate(360deg);
}

.counter-box p {
  font-size: 18px;
  margin-top: 15px;
}

.counter-box span {
  font-weight: bold;
}




.link-container a + a {
  display: grid;
  list-style-position: inside;
  margin: 50px;
  margin-bottom: -5px;
  padding: 0px 0px;
  font-weight: bolder;
  text-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
  text-decoration-color: darkgreen;
  
}

.link-container h2 {
  text-align: center;
}
.pickup-container {
  display: contents;
}

/* Progress bar styling Below */

.progress-bar {
  grid-area: bar;
  text-align: center;
  margin: 2rem;
  margin-right: 3rem;
}

.credit-progress {
  display: flex;
  width: 100rem;
  height: 70px;
  text-shadow: black;
 
}
@media (max-width: 768px){
  .credit-progress {
  margin: 0.5rem;
  }
  
}

h3 {
  /* Other styles... */
  display: flex;
  display: inline-flex;
  justify-content: center;
  font-size: 25px;
  padding: 0;
  text-align: center;
  color: rgb(70, 50, 246);
  text-shadow: black;


}

.credit-progress::-webkit-progress-bar {
  background-color: rgba(242, 242, 242, 0.15);
}

.credit-progress::-webkit-progress-value {
  background-color: #0e461f;
}

.credit-progress::-moz-progress-bar {
  background-color: #0e461f;
}

.credit-progress::-ms-fill {
  background-color: #0e461f;
}



.link-with-icon {
  display: inline-flex;
  align-items: center;
  text-decoration: none;
}

.link-with-icon i {
  vertical-align: middle;
}

.link-text {
  margin-left: 5px; /* Adjust the margin as needed */
  font-size: larger;
  vertical-align: middle;
}

</style>