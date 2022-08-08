<template>
    <!-- <div><p>{{ clientData }}</p></div> -->
    <video ref="videoStream" style="width:100%" autoplay/>
</template>

<script>

import { reactive, ref, computed, onMounted, onUpdated } from 'vue';

export default {
    name : 'UserVideo',

    props: {
		streamManager: Object,
	},

    setup(props) {

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

        const videoStream = ref(undefined);
        onMounted(() => {
            console.log('onMounted: ', videoStream);
            console.log('onMounted: ', videoStream.value);
            console.log(props);
            if(videoStream.value && props.streamManager)
                props.streamManager.addVideoElement(videoStream.value);
        });

        onUpdated(() => {
            console.log('onUpdated: ', videoStream.value);
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
