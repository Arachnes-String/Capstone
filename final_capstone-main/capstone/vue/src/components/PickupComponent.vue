<template>
  <!-- we could add a class to the parent div that changes the color based on status maybe? -->
  <div class="pickup" :class="pickup.status.toLowerCase()">
    <div class="time-created">
      <p>Request Created:</p>
      <p>
        {{
          new Date(pickup.dateTimeCreated).toLocaleString("en-US", {
            year: "numeric",
            month: "long",
            day: "numeric",
          })
        }}
      </p>
    </div>
    <div class="time-requested">
      <p>Pick Up Scheduled For:</p>
      <p>
        {{
          new Date(pickup.dateTimeRequested).toLocaleString("en-US", {
            weekday: "long",
            year: "numeric",
            month: "long",
            day: "numeric",
            hour: "numeric",
            hour12: true,
          })
        }}
      </p>
    </div>
    <div class="bins">
      <!-- maybe these could be something from font awesome? we could use v-if to only show ones with a true -->
      <div v-if="this.pickup.greenBinFull" class="green">
        <i class="fa-solid fa-recycle" style="color: rgb(96, 189, 3)"></i>
      </div>
      <div v-if="this.pickup.brownBinFull" class="brown">
        <i class="fa-solid fa-recycle" style="color: rgb(144, 123, 112)"></i>
      </div>
      <div v-if="this.pickup.clearBinFull" class="clear">
        <i class="fa-solid fa-recycle" style="color: rgb(191, 191, 238)"></i>
      </div>
    </div>
    <div class="weight">
      <p>Weight:</p>
      <p>{{ pickup.weight }} lbs</p>
    </div>
    <div class="status">
      <p>Status:</p>
      <p>{{ pickup.status }}</p>
    </div>
  </div>
</template>
<script>
export default {
  props: ["pickup"],
  created() {
    console.log(this.pickup);
  },
};
</script>
<style scoped>
.pickup {
  border: 2px solid #4a7c59;
  padding: 20px;
  text-align: center;
  margin: 20px 20px;
  background-color: rgba(242, 242, 242, 0.8);
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
  border-radius: 8px;
  display: flex;
  gap: 1rem;
}
.bins {
}
.pending {
  border: 2px solid rgb(151, 202, 226);
}
.completed {
  background-color: rgb(171, 248, 171);
}
.rejected {
  background-color: rgb(251, 192, 158);
}
</style>