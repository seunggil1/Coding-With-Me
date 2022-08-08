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
      <div v-if="video.subCamsOpen && video.state.session" class="column main-container">
        <div class="col-2">
          <div class="col-12 row justify-center items-center" style="border: 1px solid red;">
            <div class="col-2 sub-cam row justify-center items-center" v-if="video.state.publisher">
              <UserVideo class="video" :stream-manager="video.state.publisher"></UserVideo>
            </div>
            <template v-if="video.state.subscribers">
              <div class="col-2 sub-cam row justify-center items-center" v-for="sub in video.state.subscribers" :key="sub">
                <UserVideo class="video" :stream-manager="sub"></UserVideo>
              </div>
            </template>
          </div>
        </div>
        <div class="col-10 row">
          <div>
            <q-splitter v-if="video.mode === 1"
              v-model="splitterModel"
              :limits="[50, 60]"
              style="height: 78.9vh"
            >
              <template v-slot:before>
                <div class="q-pa-md">
                  <div class="text-h4 q-mb-md">강사 화면 부분</div>
                  <div v-for="n in 20" :key="n" class="q-my-md">{{ n }}. Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quis praesentium cumque magnam odio iure quidem, quod illum numquam possimus obcaecati commodi minima assumenda consectetur culpa fuga nulla ullam. In, libero.</div>
                </div>
              </template>
              <template v-slot:after>
                <div class="q-pa-md">
                  <div class="text-h4 q-mb-md">학생 웹 IDE 부분</div>
                  <div v-for="n in 20" :key="n" class="q-my-md">{{ n }}. Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quis praesentium cumque magnam odio iure quidem, quod illum numquam possimus obcaecati commodi minima assumenda consectetur culpa fuga nulla ullam. In, libero.</div>
                </div>
              </template>
            </q-splitter>
            <q-splitter v-if="video.mode === 2"
              v-model="splitterModel"
              style="height: 78.9vh"
            >
              <template v-slot:before>
                <div class="q-pa-md">
                  <div class="text-h4 q-mb-md">강사 웹 IDE 부분</div>
                  <div v-for="n in 20" :key="n" class="q-my-md">{{ n }}. Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quis praesentium cumque magnam odio iure quidem, quod illum numquam possimus obcaecati commodi minima assumenda consectetur culpa fuga nulla ullam. In, libero.</div>
                </div>
              </template>
              <template v-slot:after>
                <div class="q-pa-md">
                  <div class="text-h4 q-mb-md">학생 웹 IDE 부분</div>
                  <div v-for="n in 20" :key="n" class="q-my-md">{{ n }}. Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quis praesentium cumque magnam odio iure quidem, quod illum numquam possimus obcaecati commodi minima assumenda consectetur culpa fuga nulla ullam. In, libero.</div>
                </div>
              </template>
            </q-splitter>
          </div>
        </div>
      </div>
      <div v-else class="column main-container">
        <div class="col-12 row">
          <div>
            <q-splitter v-if="video.mode === 1"
              v-model="splitterModel"
              :limits="[50, 60]"
              style="height: 94.71vh"
            >
              <template v-slot:before>
                <div class="q-pa-md">
                  <div class="text-h4 q-mb-md">강사 화면 부분</div>
                  <div v-for="n in 20" :key="n" class="q-my-md">{{ n }}. Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quis praesentium cumque magnam odio iure quidem, quod illum numquam possimus obcaecati commodi minima assumenda consectetur culpa fuga nulla ullam. In, libero.</div>
                </div>
              </template>
              <template v-slot:after>
                <div class="q-pa-md">
                  <div class="text-h4 q-mb-md">학생 웹 IDE 부분</div>
                  <div v-for="n in 20" :key="n" class="q-my-md">{{ n }}. Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quis praesentium cumque magnam odio iure quidem, quod illum numquam possimus obcaecati commodi minima assumenda consectetur culpa fuga nulla ullam. In, libero.</div>
                </div>
              </template>
            </q-splitter>
            <q-splitter v-if="video.mode === 2"
              v-model="splitterModel"
              style="height: 94.71vh"
            >
              <template v-slot:before>
                <div class="q-pa-md">
                  <div class="text-h4 q-mb-md">강사 웹 IDE 부분</div>
                  <div v-for="n in 20" :key="n" class="q-my-md">{{ n }}. Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quis praesentium cumque magnam odio iure quidem, quod illum numquam possimus obcaecati commodi minima assumenda consectetur culpa fuga nulla ullam. In, libero.</div>
                </div>
              </template>
              <template v-slot:after>
                <div class="q-pa-md">
                  <div class="text-h4 q-mb-md">학생 웹 IDE 부분</div>
                  <div v-for="n in 20" :key="n" class="q-my-md">{{ n }}. Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quis praesentium cumque magnam odio iure quidem, quod illum numquam possimus obcaecati commodi minima assumenda consectetur culpa fuga nulla ullam. In, libero.</div>
                </div>
              </template>
            </q-splitter>
          </div>
        </div>
      </div>
      <!-- <router-view /> -->
      <!-- 모드에 따라 라우터 뷰로 전환? -->
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
          <q-btn class="modeBtn" flat round icon="swap_horiz" @click="clickMode"></q-btn>
          <q-btn v-if="video.subCamsOpen" class="subCamBtn" flat round icon="view_agenda" @click="clickSubCams"></q-btn>
          <q-btn v-else class="subCamBtn" flat round icon="o_view_agenda" @click="clickSubCams"></q-btn>
          <q-btn v-if="video.rightDrawerOpen" class="drawerBtn" flat @click="clickRightDrawer" round icon="keyboard_double_arrow_right"></q-btn>
          <q-btn v-else class="drawerBtn" flat @click="clickRightDrawer" round icon="keyboard_double_arrow_left"></q-btn>
        </div>
      </q-toolbar>
    </q-footer>
    <!-- 하단바 부분 end -->

  </q-layout>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import UserVideo from 'src/components/lectures/UserVideo.vue';
import { useVideoStore } from 'src/stores/video.js'
import { useRouter } from 'vue-router';

  const router = useRouter();
  const video = useVideoStore(); // store 가져오기

  onMounted(() => {
    video.joinSession();
  });

  watch([() => video.state.session], () => {
    console.log(video.state.session);
  });

  const splitterModel = ref(50);
  const clickMode = () => {
    if(video.mode === 1) {
      splitterModel.value = 50;
    } else if(video.mode === 2) {
      splitterModel.value = 60;
    }
    video.setMode();
  }

  // 우측 바 펼치기, 접기
  const clickRightDrawer = () => {
    video.setRightDrawer();
  }
  // 서브캠 펼치기, 접기
  const clickSubCams = () => {
    video.setSubCams();
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
  height: 94.71vh;
}
.full-main-container {
  height: 80vh;
}
.sub-cam {
  height: 100%;
}
.micBtn, .camBtn, .screenBtn, .leaveBtn, .examBtn {
  margin-left: 20px;
}
.subCamBtn, .drawerBtn, .modeBtn {
  margin-left: 10px;
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
  width: 195px;
}
</style>