<template>
    <div class="row parti-chat-box">
        <div class="col-5" style="border: 1px solid red;">
            <div 
                class="column"
                style="border: 1px solid red; height: 100%;"
            >
                <div 
                    class="col-2" 
                    style="border: 1px solid red;"
                >
                    <q-input filled v-model="formattedString" mask="time" :rules="['time']" style="height: 100%;">
                        <template v-slot:append>
                            <q-icon name="access_time" class="cursor-pointer">
                            </q-icon>
                        </template>
                    </q-input>
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
        </div>

        <div class="col-7" style="border: 1px solid red;">
            <div class="column" style="height:100%">
                <div 
                    class="col-9" 
                    style="border: 1px solid red;"
                >
                    <p>채팅 내역</p>
                </div>

                <div 
                    class="col-1" 
                    style="border: 1px solid red;"
                >
                    <p>dm 선택</p>
                </div>

                <div class="col-2" style="border: 1px solid red;">
                    <p>textarea</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { date } from 'quasar';
import { ref } from 'vue-demi';

export default {
    components : {
        
    },

    props : {

    },
    setup () {
        const timeLimit = ref(3600 * 0 + 60 * 7 + 1 * 50);

        // const 
        const formattedString = ref(date.formatDate(timeStamp, 'HH:mm:ss'));
        
        return {
            timeStamp,
            formattedString
        }
    }
}
</script>

<style lang="scss" scoped>

</style>