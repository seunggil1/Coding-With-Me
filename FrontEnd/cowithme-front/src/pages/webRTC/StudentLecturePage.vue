<template>
  <q-layout view="lHr lpr fFf">

    <q-drawer v-model="rightDrawerOpen" side="right" overlay bordered>
      <video-side-bar-vue :piniaData="studentVideo">

      </video-side-bar-vue>
    </q-drawer>

    <q-page-container>
      <div class="column main-container">
        <div v-if="showSubScribers && (studentVideo.state.session !== undefined)" class="col-2">
            
            <q-scroll-area 
                style="height: 100%; 
                max-width: 100vw;"
            >
                <div class="row no-wrap">
                    <div style="width: 150px" class="q-pa-sm">
                        <user-video 
                            :stream-manager="studentVideo.state.publisher" 
                            @click="studentVideo.updateMainVideoStreamManager(studentVideo.state.publisher)"
                        />
                    </div>
                    <div 
                        style="width: 150px" 
                        class="q-pa-sm" 
                        v-for="(sub, idx) in studentVideo.state.subscribers"
                        :key="idx"
                    >
                        <user-video 
                            :stream-manager="sub"
                            @click="studentVideo.updateMainVideoStreamManager(sub)"
                        />
                    </div>
                </div>
            </q-scroll-area>
        </div>
        <div :class="((showSubScribers && studentVideo.state.session) ? 'col-10' : 'col-12')">
          <div>
            <q-splitter
              v-model="splitterModel"
              style="height: 90vh"
            >
                <template v-slot:before>
                <div class="q-pa-md flex-height">
                    <!-- 모드 1 : 왼쪽은 강사 얼굴, 오른쪽은 학생 IDE -->
                    <span v-if="mode == 1">
                        <div class="text-h4 q-mb-md">강사 화면 부분</div>
                        <div class="flex" style="background-color: white;">
                            <user-video :stream-manager="studentVideo.state.mainStreamManager"/>
                        </div>
                    </span>
                    
                    

                    <!-- 모드 2 : 왼쪽은 강사 IDE, 오른쪽은 학생 IDE  -->
                    <span v-if="mode == 2">
                        <div class="q-pa-md flex-height column">
                            <div class="col-1">
                                <div class="text-h4 q-pl-sm q-pb-xs q-pt-xs">강사 IDE</div>
                            </div>
                            <div class="col-10">
                                <web-editor 
                                    ref="teacherIde" 
                                    :code="studentVideo.state.teacherCode" 
                                    language="java" 
                                    :readOnly="true"
                                />
                            </div>
                        </div>
                    </span>
                    
                </div>
                </template>
                <template v-slot:after>
                <div class="q-pa-md flex-height column">
                    <div class="col-1">
                        <div class="row flex-height">
                            <div class="col-10" style="background-color: white;">
                                <div class="text-h4 q-pl-sm q-pb-xs q-pt-xs">IDE</div>
                            </div>
                            <div class="col-2 " style="background-color: red;">
                                <div class="row justify-center flex">
                                    <q-btn 
                                        class="col q-ma-sm" 
                                        color="secondary" 
                                        icon-right="send" 
                                        label="Run" 
                                        @click="runCode"
                                        :loading="isRunning"
                                    />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-7">
                        <web-editor 
                            ref="studentIde" 
                            :code="studentVideo.state.myCode" 
                            language="java" 
                            :readOnly="false"
                        />
                    </div>
                    <div class="col-4 row">
                        <div class="col" style="background-color: white;">
                            <q-card flat bordered class="my-card flex-height">
                                <q-card-section>
                                    <div class="text-h6">Input</div>
                                </q-card-section>
                                <q-separator inset />
                                <q-card-section>
                                    <q-input
                                    type="textarea"
                                    float-label="Textarea"
                                    :max-height="40"
                                    :min-rows="1"
                                    v-model="inputData"
                                    />
                                </q-card-section>
                            </q-card>
                        </div>
                        <div class="col" style="background-color: white;">
                            <q-card flat bordered class="my-card flex-height">
                                <q-card-section>
                                    <div class="text-h6">Result</div>
                                </q-card-section>
                                <q-separator
                                    inset
                                />
                                <q-card-section>
                                    <q-input
                                    type="textarea"
                                    float-label="Textarea"
                                    :max-height="50"
                                    :min-rows="1"
                                    v-model = "outputData"
                                    readonly
                                    />
                                </q-card-section>
                            </q-card>
                        </div>
                    </div>
                </div>
                
                </template>
            </q-splitter>
          </div>
        </div>
      </div>

    </q-page-container>

    <q-footer elevated class="bg-grey-8 text-white">
      <q-toolbar>

        <div class="row full-width">
            <div class="col-1">
                <q-avatar>
                    <img src="src/assets/logo/logo.svg">
                </q-avatar>
            </div>
            <div class="col-10" align="center">
                <q-btn 
                    class="micBtn" 
                    rounded 
                    push 
                    :icon="studentVideo.isAudio ? 'mic' : 'mic_off'" 
                    :label="studentVideo.isAudio ? '음소거' : '음소거 해제'" 
                    @click="studentVideo.isAudio ? studentVideo.muteAudio() : studentVideo.unmuteAudio()"
                />

                <q-btn 
                    class="camBtn"
                    rounded
                    push
                    :icon="studentVideo.isVideo ? 'videocam_off' : 'videocam'"
                    :label="studentVideo.isVideo ? '카메라 끄기' : '카메라 켜기'"
                    @click="studentVideo.isVideo ? studentVideo.muteVideo() : studentVideo.unmuteVideo()"
                />
                
                <q-btn 
                    class="screenBtn" 
                    rounded 
                    push 
                    icon="screen_share" 
                    :label="studentVideo.isScreen ? '화면공유 중지' : '화면공유'" 
                    @click="studentVideo.isScreen ? studentVideo.stopScreenShare() : studentVideo.startScreenShare()"
                />

                <q-btn 
                    class="examBtn" 
                    rounded 
                    push 
                    icon="quiz" 
                    label="시험 입장"
                    :disable="true" 
                    @click="startExam"
                />
                <q-btn 
                    class="leaveBtn" 
                    rounded 
                    push 
                    color="red" 
                    icon="logout" 
                    label="나가기" 
                    @click="leaveSession"
                />
            </div>
            <div class="col-1" align="end">
                <q-btn 
                    class="modeBtn"
                    flat 
                    round
                    icon="swap_horiz" 
                    @click="(mode == 1) ? mode = 2 : mode = 1;">
                </q-btn>

                <q-btn 
                    class="subCamBtn" 
                    flat 
                    round 
                    :icon="showSubScribers ? 'o_view_agenda' :'view_agenda'" 
                    @click="showSubScribers = !showSubScribers"
                />
                
                <q-btn 
                    class="drawerBtn" 
                    flat 
                    @click="rightDrawerOpen = !rightDrawerOpen" 
                    round 
                    :icon="rightDrawerOpen ? 'keyboard_double_arrow_right' : 'keyboard_double_arrow_left'"
                />
            </div>
        </div>
      </q-toolbar>
    </q-footer>

  </q-layout>
</template>

<script>
import { onMounted, onBeforeUnmount, ref, watch } from 'vue'
import { studentVideoStore } from 'src/stores/studentVideo.store.js'
import WebEditor from 'src/components/lectures/WebEditor.vue';
import UserVideo from 'src/components/lectures/UserVideo.vue';
import VideoSideBarVue from 'src/components/lectures/VideoSideBar.vue';
import axios from 'axios';
export default {
    components : {
        WebEditor,
        UserVideo,
        VideoSideBarVue
    },
    setup () {
        const studentVideo = studentVideoStore();
        let showSubScribers = ref(true);
        let rightDrawerOpen = ref(false);
        
        // IDE
        const teacherIde = ref(undefined);
        const studentIde = ref(undefined);

        const updateEditor = () => {
            if(teacherIde.value)
                teacherIde.value.updateEditor();
            studentIde.value.updateEditor();
        };

        const getTeacherCode = () => {
            if(teacherIde.value)
                teacherIde.value.updateCode(studentVideo.state.teacherCode);
        }
        // 모드 변환
        const mode = ref(1);

        watch(mode, () =>{
            if(teacherIde.value)
                teacherIde.value.saveCode();
            studentIde.value.saveCode(true);
        });

        const splitterModel = ref(50);
        watch(splitterModel, () =>{
            updateEditor();
        }); 
        watch(rightDrawerOpen, ()=>{
            updateEditor();
        });


        let isRunning = ref('false');
        let inputData = ref('');
        let outputData = ref('');

        const runCode = async () => {
            isRunning.value = true;
            studentIde.value.saveCode(true);
            
            const res = await axios.post('http://i7a304.p.ssafy.io:8080/api/v1/users/compile',{
                code : studentVideo.state.myCode,
                lang : 'java',
                testcase : {
                    input : inputData.value,
                    output : '0'
                }
            });
            if(res.data.status === "success"){
                outputData.value = `Time : ${res.data.result[0].time}ms\n${res.data.result[0].output}`;
            }else{
                outputData.value = `서버 오류가 발생했습니다.`;
            }
            isRunning.value = false;
        };

        
        let repeater = undefined;
        onMounted(() => {
            studentVideo.joinSession();
            window.addEventListener('resize', updateEditor);
            repeater = setInterval(getTeacherCode, 2000);
        });

        onBeforeUnmount(()=>{
            window.removeEventListener('resize', updateEditor);
            if(repeater){
                clearInterval(repeater);
                repeater = undefined;
            }
        });

        // 시험 시작
        const startExam = () => {
            router.push('/exam');
        };

        const leaveSession = () => {
            studentVideo.leaveSession();
            router.push('/');
        }

        return {
            studentVideo,
            showSubScribers,
            teacherIde,
            studentIde,
            rightDrawerOpen,
            updateEditor,
            getTeacherCode,

            mode,

            // IDE
            splitterModel,
            isRunning,
            inputData,
            outputData,
            runCode,
            repeater,

            // 시험
            startExam,
            leaveSession
        }
    }
}
</script>

<style scoped>
.main-container{
    height: 100vh; 
    width: 100vw; 
    background-color: aquamarine;
}

.flex{
    width: 100%;
    height: 100%;
    
    background-color: white;
}

.flex-width{
    width: 100%
}

.flex-height{
    height: 100%
}

</style>