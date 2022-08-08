<template>
	<div>
		<button @click="joinSession">Join!</button>
	</div>

  <!-- <div id="main-container" class="container">
		<div id="join" v-if="state.session === undefined">
			<h1>Join a video session</h1>
				<div class="form-group">
					<p>
						<label>Participant</label>
						<input v-model="state.myUserName" class="form-control" type="text" required>
					</p>
					<p>
						<label>Session</label>
						<input v-model="state.mySessionId" class="form-control" type="text" required>
					</p>
					<p class="text-center">
						<button class="btn btn-lg btn-success" @click="joinSession">Join!</button>
					</p>
				</div>
		</div>
		
		<div id="session" v-if="state.session !== undefined">
				<div id="session-header">
					<h1 id="session-title">{{ state.mySessionId }}</h1>
					<input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="Leave session">
				</div>
				<div id="session-header-screenShare">
					<input v-if ="!state.screenOV" class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="startScreenShare" value="Start Screen Share">
					<input v-if ="state.screenOV" class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="stopScreenShare" value="End Screen Share">
				</div>

				<div id="main-video" class="col-md-6">
					<user-video v-if="state.mainStreamManager" :stream-manager="state.mainStreamManager"/>
				</div>
			
			<div id="video-container" class="col-md-3">
				<user-video v-if="state.publisher" :stream-manager="state.publisher" @click="updateMainVideoStreamManager(state.publisher)"/>
				<user-video v-for="sub in state.subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click="updateMainVideoStreamManager(sub)"/>
			</div>
		</div>
    
  </div> -->

</template>

<script>
import { useVideoStore } from 'src/stores/video.js'
import { useRouter } from 'vue-router' 

export default {
  name: 'MainLayout',

  setup() {
		const router = useRouter()
		const video = useVideoStore(); // store 가져오기

		video.setMyUserName();
		video.setScreenShareName();

		const joinSession = () => {
			router.push('/lecture');
		}

		return {
			video,
			joinSession,
    }
	}
}
</script>

<style>
@font-face {
	font-family: 'GmarketSansLight';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansLight.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}
* {
	font-family: 'Elice Digital Baeum', sans-serif;
	font-size: 18px;
	box-sizing: border-box;
}
</style>