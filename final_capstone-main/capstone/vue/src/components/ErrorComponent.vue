<template>
  <div v-if="errorMessage" class="error-container">
    <p class="error-message">{{ errorMessage }}</p>
  </div>
</template>

<script>
export default {
  props: {
    errorMessage: {
      type: String,
      required: true,
    },
  },
};
</script>

<style scoped>
.error-container {
  background-color: #ffcccc;
  border: 1px solid #ff0000;
  color: #ff0000;
  padding: 10px;
  margin-bottom: 10px;
}

.error-message {
  margin: 0;
}
</style>
