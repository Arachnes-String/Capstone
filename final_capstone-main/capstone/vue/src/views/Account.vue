<template>
<div class="account-container" role="img" aria-label="Meadow with a Focused View of Light Pink Wildflowers">
  <account-main />
  </div>
</template>

<script>
import AccountMain from '../components/AccountMain.vue'
export default {
  components: { AccountMain },

}
</script>

<style scoped>
.account-container {
  /* height: calc(100vh - 212px); */
  height: 100vh;
  background-image: linear-gradient(
    rgba(300, 300, 300, 0.5),
    rgba(300, 300, 300, 0.5)
  ),
  url(../img/account-background.jpg);
  background-size: cover;
  background-position: center;
}

</style>