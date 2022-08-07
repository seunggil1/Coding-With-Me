<template>
  <div class="sub-videos">
    <!-- <div class="sub-video">
      <UserVideo :streamManager="props.streamManager"></UserVideo>
    </div> -->
    <div class="sub-video" v-for="(subcam, index) in sub" :key="index">
      <UserVideo></UserVideo>
    </div>
  </div>
</template>

<script>
import UserVideo from 'src/components/lectures/UserVideo.vue'
import { useVideoStore } from 'src/stores/video.js'

export default {
    components: { UserVideo, },
    setup() {
      const video = useVideoStore(); // store 가져오기
      const sub = video.state.subscribers;
      return {
        video,
        sub,
      };
    },
}
</script>

<style>
.sub-videos {
  width: 100%;
  height: 20vh;
  border: 1px solid #EEEEEE;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.sub-video {
  width: 16%;
  height: 95%;
  border: 1px solid #EEEEEE;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>