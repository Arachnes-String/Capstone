<template>
  <div>
      <h2>Contact Info</h2>
  <div class="contact-info">
      <div><span>First Name:</span> <span>{{client.firstName}}</span></div>
      <br/>
      <div><span>Last Name:</span> <span>{{client.lastName}}</span></div>
       <br/>
      <div><span>Phone Number:</span> <span>{{client.phoneNumber}}</span></div>
       <br/>
      <div><span>Email Address:</span> <span>{{client.emailAddress}}</span></div>
    </div>
  </div>
</template>

<script>
export default {
    name: "admin-contact-info",
    props: ['client'],


}
</script>

<style scoped>
.contact-info div{
  display: flex;
  justify-content: space-between;
  font-weight: 400;
  font-size: 25px;
      height: 40px;
}

h2 {
  text-align: center;
}

</style>