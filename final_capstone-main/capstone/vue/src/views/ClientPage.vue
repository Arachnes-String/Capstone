<template>
<div class="container">
<ClientMain/>
</div>
</template>



<script>
import ClientMain from '../components/ClientMain.vue';


export default {
  components:
   { ClientMain }
} 
</script>


<style scoped>


.custom-font {
 font-family: 'Open Sans';
}

.container {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center
  gap 20px;
}

template {
  margin: 0;
  padding: 0;
  display: grid;
  grid-template-columns: 300px 1fr 250px;
  gap: 15px;
}


@media (max-width: 768px) {
  .container{
    margin: 0 10px;
  }
  
}

</style>
