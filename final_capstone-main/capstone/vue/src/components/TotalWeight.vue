<template>
  <div class="counter-box">
<i class="fa-solid fa-weight-scale fa-bounce:hover" style="color: rgb(189, 140, 17)"></i>
      Total Weight: {{ totalWeight }}
  </div>
</template>

<script>
export default {
    props: ["totalWeight"],
    

}
</script>

<style scoped>
.counter-box {
  border: 2px solid #4a7c59;
  width: 180px;
  padding: 20px;
  text-align: center;
  margin: 20px auto;
  background-color: rgba(242, 242, 242, 0.8);
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
  border-radius: 8px;
  position: relative;
  overflow: hidden;
  
}

.counter-box i {
  font-size: 40px;
  margin-top: 10px;
  transition: transform 0.75s ease-in-out;
}

.counter-box:hover i {
  transform: rotate(360deg);
 }

.counter-box p {
  font-family: "Open Sans", sans-serif;
  font-size: 18px;
  margin-top: 15px;
}

.counter-box span {
  font-weight: bold;
}
</style>