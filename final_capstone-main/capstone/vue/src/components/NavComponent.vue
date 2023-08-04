<template>
  <div class="home-navbar">
        <router-link class="router-logo" to="/" exact><img src="../img/logo.png" class="logo" aria-label="Checkered Backgrounf with Underground Roots on the Left and Glass Roots Text on the Right"/></router-link>
      <!-- <a href="/"><img src="../img/logo.png" class="logo" /></a> -->
      <ul>
        <li><router-link to="/" exact>Home</router-link></li>
        <li v-if="this.user.authorities[0].name === 'NO_ONE'"><router-link to="/login">Login</router-link></li>
        <li v-if="this.user.authorities[0].name === 'NO_ONE'"><router-link to="/register">Sign Up</router-link></li>
        <li v-if="this.user.authorities[0].name === 'ROLE_USER'"><router-link to="/client" >Client</router-link></li>
        <li v-if="this.user.authorities[0].name === 'ROLE_USER'"><router-link to="/account" >Account Details</router-link></li>
        <li v-if="this.user.authorities[0].name === 'ROLE_USER'"><router-link to="/pickup">Schedule Pick Up</router-link></li>
        <li v-if="this.user.authorities[0].name === 'ROLE_DRIVER'"><router-link to="/driver">Driver</router-link></li>
        <li v-if="this.user.authorities[0].name === 'ROLE_DRIVER'"><router-link to="/mypickups">My Pickups</router-link></li>
        <li v-if="this.user.authorities[0].name === 'ROLE_DRIVER'"><router-link :to="`/admin/drivers/update/${this.$store.state.user.id}`">Edit Driver Info</router-link></li>
        <li v-if="this.user.authorities[0].name === 'ROLE_ADMIN'"><router-link to="/admin">Admin</router-link></li>
        <li v-if="this.user.authorities[0].name === 'ROLE_ADMIN'"><router-link to="/admin/clients">Clients</router-link></li>
         <li v-if="this.user.authorities[0].name === 'ROLE_ADMIN'"><router-link to="/admin/drivers">Drivers</router-link></li>
         <li v-if="this.user.authorities[0].name === 'ROLE_ADMIN'"><router-link to="/prizes">Prizes</router-link></li>

        <li v-if="this.user.authorities[0].name !== 'NO_ONE'"><router-link to="/logout">Logout</router-link></li>
      </ul>
    </div>
</template>

<script>
export default {

computed: {
  user: function() {
    
    let obj = this.$store.state.user;

    if (Object.keys(obj).length === 0){
      obj.authorities = [];
      obj.authorities.push({name: "NO_ONE"});
  }
  return obj;

  }
},


}
</script>

<style scoped>
.home-navbar {
  width: 100%;
  margin: auto;
  padding: 15px 0;
  display: flex;
  align-items: center;
  justify-content: space-between;
  background-color: rgba(0, 255, 0, 0.1);
}

.logo {
  padding-left: 5%;
  width: 150px;
}

.home-navbar ul li {
  list-style: none;
  display: inline-block;
  margin: 0 20px;
  position: relative;
}

.home-navbar ul li a {
  font-size: 25px;
  text-decoration: none;
  color: #4a7c59;
  text-transform: uppercase;
}

.home-navbar ul li::after {
  content: "";
  height: 3px;
  width: 0;
  background: #907b70;
  position: absolute;
  left: 0;
  bottom: -5px;
  transition: 0.5s;
}

.home-navbar ul li:hover::after {
  width: 100%;
}

li .router-link-active{
  padding: 0px !important ;
}

.router-logo.router-link-active{
  padding: 0px !important;
  min-height: unset;
  margin-bottom: 0px  ;
}

</style>