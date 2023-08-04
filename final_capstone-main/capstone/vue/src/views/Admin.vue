<template>
<div>  
    <AdminMain />
  </div>
</template>

<script>
import AdminMain from '../components/AdminMain.vue';

export default {
  components: {
    AdminMain,
  },
};
</script>

<style scoped>

h1 {
  font-family: "Open Sans", sans-serif;
  font-size: 2.5rem;
  margin-top: 2rem;
  text-align: center;
  background-color: rgb(197, 232, 246);
  padding: 1rem;
  border-radius: 8px;
}

.admin-view {
  margin: 2rem;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  text-align: center;
  background-size: cover;
  background-position: center;
}


.admin-components {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 2rem;
  margin-top: 2rem;
}

@media (max-width: 768px) {
  .admin-components {
    grid-template-columns: 1fr;
  }
  
}

</style>