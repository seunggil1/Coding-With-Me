<template>
  <div class="video-side-bar">
    <div class="chatShowArea"></div>
    <div class="chatTextarea"></div>
  </div>
</template>

<script>
export default {
  setup () {
    

    return {}
  }
}
</script>

<style scoped>
.video-side-bar {
  width: 20%;
  height: 850px;
  border: 1px solid yellow;
  display: flex;
  flex-direction: column;
  float: right;
}
.chatShowArea {
  width: 100%;
  height: 750px;
  border: 1px solid red;
}
.chatTextarea {
  width: 100%;
  height: 100px;
  border: 1px solid violet;
}
</style>