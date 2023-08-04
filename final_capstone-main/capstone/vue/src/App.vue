<template>
  <div id="app">
    <nav-component />
    <router-view  class="router"/>
    <footer-component class="footer"/>
  </div>
</template>

<script>
import FooterComponent from './components/FooterComponent.vue'
import NavComponent from './components/NavComponent.vue'

export default {
  components: { FooterComponent, NavComponent},
  }

</script>


<style>
.router-link-exact-active{
  min-height: 250px;
}

.router-link-exact-active {
  font-size: 20px;
  font-weight: bolder;
  margin-bottom: 2px;
  padding: 0px 10px;
  
}
.custom-font {
 font-family: 'Open Sans';
}

.router {
  height: 86vh;
}


body{
  margin: 0px;
  font-family: "Open Sans", sans-serif;
}

/* .footer{  
  position: sticky;
  bottom: 0px;
} */
</style>
