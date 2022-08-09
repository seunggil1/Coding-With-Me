<template>
<div class="q-pa-md column justify-center" style="height: 100vh;">
    <q-scroll-area 
        class="col-11"
        style="width: 100%"
    >
        <span 
            v-for="(chat, index) in piniaData.state.chatting"
            :key = "index">
            <q-chat-message
                v-if="chat.sender == piniaData.state.myUserName"
                :name="chat.sender"
                avatar="https://cdn.quasar.dev/img/avatar3.jpg"
                :text="[chat.message]"
                stamp="just second"
                sent
                bg-color="amber-7"
            />
            <q-chat-message
                v-else
                :name="chat.sender"
                avatar="https://cdn.quasar.dev/img/avatar3.jpg"
                :text="[chat.message]"
                stamp="just second"
                bg-color="amber-7"
            />
        </span>
        <q-chat-message
            v-for="(chat, index) in piniaData.state.chatting"
            :key = "index"
            :name="chat.sender"
            avatar="https://cdn.quasar.dev/img/avatar3.jpg"
            :text="[chat.message]"
            stamp="just second"
            sent
            bg-color="amber-7"
        />
    </q-scroll-area>

    <div
        class="q-pa-md justify-center col-1"
    >
        <q-input 
            bottom-slots 
            v-model="myChatInput" 
            label="채팅" 
            :dense="true" 
            @keydown.enter.prevent="piniaData.sendMessage(myChatInput); myChatInput = ''">
                <template v-slot:before>
                    <q-avatar>
                        <img src="https://cdn.quasar.dev/img/avatar5.jpg">
                    </q-avatar>
                </template>

                <template v-slot:append>
                    <q-icon 
                        v-if="myChatInput !== ''" 
                        name="close" 
                        @click="myChatInput = ''" 
                        class="cursor-pointer"
                    />
                </template>

            <template v-slot:after>
                <q-btn 
                    round 
                    dense 
                    flat 
                    icon="send"
                    @click="piniaData.sendMessage(myChatInput); myChatInput = ''"
                />
            </template>
        </q-input>
    </div>
</div>

</template>

<script>
import { ref, watch } from 'vue';

export default {
    //store 객체를 props로 받음.
    props: {
        piniaData : Object
    },
    setup (props) {
        let myChatInput = ref('');

        watch(()=> props.piniaData.state.chatting,()=>{
            console.log(props.piniaData.state.chatting);
        });

        return {
            myChatInput
        }
    }
}
</script>

<style scoped>

</style>