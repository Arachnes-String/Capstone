<template>
  <div class="counter-box">
    <span>
      <i class="fa-solid animated" :class="[icon, animation]"></i>
      <i class="fa-solid fixed" :class="icon"></i>
      </span>
    <p>We've {{ action }}</p>
    <p>{{ counter }}</p>
    <p>{{ type }} Together</p>
  </div>
</template>

<script>
export default {
  name: "CounterBox",
  props: {

    type: String,
    icon: String,
    action: String,
    animation: String,

    counter: {
      type: Number,
      default: 0
    }
  }
};
</script>

<style scoped>
.counter-box {
  border: 2px solid #4a7c59;
  width: 180px;
  padding: 20px;
  text-align: center;
  margin: 20px 20px;
  background-color: rgba(242, 242, 242, 0.8);
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.5);
  border-radius: 8px;
  position: relative;
  overflow: hidden;
}

.counter-box .animated{
  display: none;

}

.counter-box:hover .animated{
  display: inline-block;
}

.counter-box:hover .fixed{
  display: none;
}


.counter-box i {
  font-size: 40px;
  margin-top: 10px;
  transition: transform 0.75s ease-in-out;
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
