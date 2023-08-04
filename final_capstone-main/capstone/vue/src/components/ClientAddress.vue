<template>
<div>
  <h2>Address</h2>
  <div class="client-address">
      <div><span>Street Address:</span> <span>{{clientAddress.streetAddress}}</span></div>
      <br/>
      <div><span>City:</span> <span>{{clientAddress.city}}</span></div>
      <br/>
      <div><span>State:</span> <span>{{clientAddress.state}}</span></div>
      <br/>
      <div><span>Zip:</span> <span>{{clientAddress.zip}}</span></div>
  </div></div>
</template>

<script>
export default {
    name: "ClientAddress",
    props: ['clientAddress'],


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

h2 {
  text-align: center;
}
</style>