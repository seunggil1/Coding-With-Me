<template>
    <!-- <div><p>{{ clientData }}</p></div> -->
    <video v-if="streamManager" ref="videoStream" autoplay/>
</template>

<script>

import { reactive, ref, computed, onMounted, onUpdated } from 'vue';
import { useVideoStore } from 'src/stores/video.js'

export default {
    name : 'UserVideo',

    props: {
		streamManager: Object,
	},

    setup(props) {

        const video = useVideoStore(); // store 가져오기
        const sub = video.state.subscribers;

        const state = reactive({
            clientData : computed(() =>{
                const { clientData } = getConnectionData();
                return clientData;
            })
        });

        const getConnectionData = () => {
            const { connection } = props.streamManager.stream;
			return JSON.parse(connection.data);
        }

        const videoStream = ref(null);
        onMounted(() => {
            console.log(videoStream);
            console.log(videoStream.value);
            props.streamManager.addVideoElement(videoStream.value);
        });

        onUpdated(() => {
            console.log(videoStream.value);
            props.streamManager.addVideoElement(videoStream.value);
        });
        

        return {
            videoStream,
            state,
            getConnectionData
        }
    }
}
</script>
