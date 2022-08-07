<template>
  <!-- v-if="session" -->
  <q-layout view="hHh lpR fFf">
    <!-- 오른쪽 참여자 + 채팅 부분 start -->
    <q-drawer show-if-above v-model="video.rightDrawerOpen" side="right" elevated>
      <div class="column drawer-container">
        <div class="col-4 column justify-center items-center" style="border: 1px solid blue;">
          <div class="participant-box">
            <p>참여자 목록</p>
            <p>총 인원 (25/25)</p>
            <p>오프라인</p>
            <p>온라인</p>
          </div>
        </div>
        <div class="col-8 column justify-center items-center" style="border: 1px solid blue;">
          <div class="chat-box column">
            <div class="col-10" style="border: 1px solid blue;"></div>
            <div class="col-2">
              <textarea class="textarea"></textarea>
            </div>
          </div>
        </div>
      </div>
    </q-drawer>
    <!-- 오른쪽 참여자 + 채팅 부분 end -->

    <!-- 서브캠 + 메인캠 부분 start -->
    <q-page-container>
      <div class="column sub-container">
        <div class="col-10 row sub-cams justify-center items-center" style="border: 1px solid red;">
          <div class="col-2 sub-cam row justify-center items-center" v-if="pub">
            <UserVideo class="video" :stream-manager="pub"></UserVideo>
          </div>
          <template v-if="subs">
            <div class="col-2 sub-cam row justify-center items-center" v-for="sub in subs" :key="sub">
              <UserVideo class="video" :stream-manager="sub"></UserVideo>
            </div>
          </template>
        </div>
        <div class="col-2 q-gutter-x-xs" style="border: 1px solid red">
          <q-btn size="xs" color="primary" label="모드1" />
          <q-btn size="xs" color="primary" label="모드2" />
        </div>
      </div>

      <!-- <router-view /> -->
      <!-- 모드에 따라 라우터 뷰로 전환? -->

      <!-- 상하로 나눈거 -->
      <!-- <div class="column main-container">
        <q-scroll-area style="width: 100%; height: 100%;">
          <div class="column" style="height: 112.065vh; border: 1px solid green;">
            <div class="col-4" style="background-color: teal;"></div>
            <div class="col-4" style="background-color: tan;"></div>
            <div class="col-4" style="background-color: yellow;"></div>
          </div>
        </q-scroll-area>
      </div> -->
      <!-- 좌우로 나누기 -->
      <div class="column main-container">
        <q-scroll-area style="width: 100%; height: 100%;">
          <div class="column" style="height: 149.42vh; border: 1px solid green;">
            <div class="col-6" style="background-color: teal;">
              <div class="row" style="width: 100%; height: 100%;">
                <div class="col-6" style="background-color: antiquewhite;"></div>
                <div class="col-6" style="background-color: beige;"></div>
              </div>
            </div>
            <div class="col-6" style="background-color: tan;"></div>
          </div>
        </q-scroll-area>
      </div>
    </q-page-container>
    <!-- 서브캠 + 메인캠 부분 end -->

    <!-- 하단바 부분 start -->
    <q-footer elevated class="bg-grey-8 text-white">
      <q-toolbar>
        <div class="col-11" align="center">
          <q-btn v-if="video.isAudio" class="micBtn" rounded push icon="mic" label="음소거" @click="clickMic"></q-btn>
          <q-btn v-else class="micBtn" rounded push icon="mic_off" label="음소거 해제" @click="clickMic"></q-btn>
          <q-btn v-if="video.isVideo" class="camBtn" rounded push icon="videocam" label="카메라 끄기" @click="clickVideo"></q-btn>
          <q-btn v-else class="camBtn" rounded push icon="videocam_off" label="카메라 켜기" @click="clickVideo"></q-btn>
          <q-btn v-if="video.isScreen" class="screenBtn" rounded push icon="screen_share" label="화면공유 중지" @click="clickScreenShare"></q-btn>
          <q-btn v-else class="screenBtn" rounded push icon="screen_share" label="화면공유" @click="clickScreenShare"></q-btn>
          <q-btn class="examBtn" rounded push icon="quiz" label="시험 시작" @click="startExam"></q-btn>
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

  // 시험 시작
  const startExam = () => {
    router.push('/exam')
  }

</script>

<style scoped>
div {
  box-sizing: border-box;
}
.sub-container {
  height: 20vh;
}
.drawer-container {
  height: 94.71vh;
}
.main-container {
  height: 74.71vh;
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
  width: 205px;
}
</style>