<template>
  <div class="video-bottom-bar">
    <div>
      <q-btn class="micOnOffBtn" rounded push icon="mic" label="음소거" @click="clickMic"></q-btn>
    </div>
    <div>
      <q-btn class="videoOnOffBtn" rounded push icon="videocam" label="카메라 끄기" @click="clickVideo"></q-btn>
    </div>
    <div>
      <!-- <q-btn class="screenShareOnOffBtn" rounded v-if="!props.state.screenOV" push icon="screen_share" label="화면공유" @click="clickShare"></q-btn>
      <q-btn class="screenShareOnOffBtn" rounded v-else @click="clickShare"  push color="red" icon="exit_to_app" label="화면공유 중지"></q-btn> -->
    </div>
    <div>
      <q-btn class="leaveBtn" rounded @click="leaveSession" push color="red" icon="logout" label="나가기"></q-btn>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'

export default {
    props: {
        state: Object,
    },
  setup (props) {
    const mic = ref('true');
    const video = ref('true');
    const share = ref('false');

    const clickMic = () => {
      if(mic.value) {
        return
      }
      mic.value = !mic.value;
    }
    return {props, mic, video, share, clickMic}
  }
}
</script>

<style>
.video-bottom-bar {
  width: 100%;
  height: 10vh;
  /* border: 1px solid turquoise; */
  display: flex;
  justify-content: center;
  align-items: center;
}
.micOnOffBtn, .screenShareOnOffBtn, .videoOnOffBtn, .leaveBtn {
  margin-left: 8px;
  margin-right: 8px;
  background-color: #00ADB5;
  color: white;
}
</style>