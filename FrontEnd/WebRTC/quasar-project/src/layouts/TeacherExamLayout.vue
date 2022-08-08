<template>
  <!-- v-if="session" -->
  <q-layout view="hHh lpR fFf">
    <!-- 오른쪽 참여자 + 채팅 부분 start -->
    <q-drawer :width="600" show-if-above v-model="video.rightDrawerOpen" side="right" elevated>
      <div class="row parti-chat-box">
        <div class="col-5 column" style="border: 1px solid red;">
          <div class="col-2" style="border: 1px solid red;">
            <p>타이머</p>
            <p>총 인원 (25/25)</p>
          </div>
          <div class="col-5" style="border: 1px solid red;">
            <p>오프라인</p>
            <p>온라인</p>
          </div>
          <div class="col-5" style="border: 1px solid red;">
            <p>제출 완료</p>
            <p>제출 미완료</p>
          </div>
        </div>
        <div class="col-7 column" style="border: 1px solid red;">
          <div class="col-9" style="border: 1px solid red;">
            <p>채팅 내역</p>
          </div>
          <div class="col-1" style="border: 1px solid red;">
            <p>dm 선택</p>
          </div>
          <div class="col-2" style="border: 1px solid red;">
            <p>textarea</p>
          </div>
        </div>
      </div>
    </q-drawer>
    <!-- 오른쪽 참여자 + 채팅 부분 end -->

    <!-- 메인캠 부분 start -->
    <q-page-container>
      <div class="row main-container justify-center items-center">
        <div class="main-cam-box row justify-center items-center">
          <div v-if="pub">
            <UserVideo class="video" :stream-manager="pub"></UserVideo>
          </div>
          <template v-if="subs">
            <div v-for="sub in subs" :key="sub">
              <UserVideo class="video" :stream-manager="sub"></UserVideo>
            </div>
          </template>
        </div>
      </div>
    </q-page-container>
    <!-- 메인캠 부분 end -->

    <!-- 하단바 부분 start -->
    <q-footer elevated class="bg-grey-8 text-white">
      <q-toolbar>
        <div class="col-11" align="center">
          <q-btn v-if="video.isAudio" class="micBtn" rounded push icon="mic" label="음소거" @click="clickMic"></q-btn>
          <q-btn v-else class="micBtn" rounded push icon="mic_off" label="음소거 해제" @click="clickMic"></q-btn>
          <q-btn v-if="video.isVideo" class="camBtn" rounded push icon="videocam" label="카메라 끄기" @click="clickVideo"></q-btn>
          <q-btn v-else class="camBtn" rounded push icon="videocam_off" label="카메라 켜기" @click="clickVideo"></q-btn>
          <q-btn class="examBtn" rounded push icon="quiz" label="시험 종료"></q-btn>
          <q-btn class="leaveBtn" rounded push color="red" icon="logout" label="나가기" @click="leaveSession"></q-btn>
        </div>
        <div class="col-1" align="end">
          <q-btn v-if="!video.rightDrawerOpen" flat @click="clickRightDrawer" round icon="keyboard_double_arrow_left"></q-btn>
          <q-btn v-else flat @click="clickRightDrawer" round icon="keyboard_double_arrow_right"></q-btn>
        </div>
      </q-toolbar>
    </q-footer>
    <!-- 하단바 부분 end -->

  </q-layout>
</template>

<script setup>
import { ref } from 'vue';
import UserVideo from 'src/components/lectures/UserVideo.vue';
import { useVideoStore } from 'src/stores/video.js'
import { useRouter } from 'vue-router';

  const router = useRouter();
  const video = useVideoStore(); // store 가져오기
  const session = video.state.session;
	const pub = video.state.publisher;
	const subs = video.state.subscribers;
	const main = video.state.mainStreamManager;

  // 우측 바 펼치기, 접기
  const clickRightDrawer = () => {
    video.setRightDrawer();
  }
  // 마이크 켜기, 끄기
  const clickMic = () => {
    if(video.isAudio) {
      video.muteAudio();
    } else {
      video.unmuteAudio();
    }
  }
  // 카메라 켜기, 끄기
  const clickVideo = () => {
    if(video.isVideo) {
      video.muteVideo();
    } else {
      video.unmuteVideo();
    }
  }
  // 화면 공유 켜기, 끄기
  const clickScreenShare = () => {
    if(video.isScreen) {
      video.stopScreenShare();
    } else {
      video.startScreenShare();
    }
  }
  // 세션 나가기
  const leaveSession = () => {
    video.leaveSession();
    router.push('/');
  }

</script>

<style scoped>
div {
  box-sizing: border-box;
}
.parti-chat-box {
  height: 94.71vh;
  border: 1px solid black;
}
.drawer-container, .main-container {
  height: 94.71vh;
}
.main-cam-box {
  width: 95%;
  height: 95%;
  border: 1px solid red;
}
.sub-cams {
  border: 1px solid red;
  height: 100%;
}
.sub-cam {
  height: 100%;
}
.micBtn, .camBtn, .screenBtn, .leaveBtn, .examBtn {
  margin-left: 20px;
}
.participant-box, .chat-box {
  width: 95%;
  height: 95%;
}
.textarea {
  width: 100%;
  height: 100%;
  resize: none;
}
video {
  width: 250px;
}
</style>