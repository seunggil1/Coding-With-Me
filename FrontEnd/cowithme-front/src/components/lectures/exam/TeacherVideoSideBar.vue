<template>
    <div class="row parti-chat-box">
        <div class="col-5 column" style="border: 1px solid red;">
          <div class="col-2" style="border: 1px solid red;">
            <p>타이머</p>
            <p>dfs</p>
            <div class="q-pa-md">
                <div class="q-gutter-md">
                    <q-time v-model="timeStamp" />

                    <q-time
                        v-model="timeStamp"
                        with-seconds
                    />
                </div>
            </div>
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
</template>

<script>
import { date } from 'quasar';

export default {
    components : {
        
    },

    props : {

    },
    setup () {
        const timeStamp = Date.now();
        const formattedString = date.formatDate(timeStamp, 'YYYY-MM-DD HH:mm:ss')
        
        return {
            timeStamp,
            formattedString
        }
    }
}
</script>

<style lang="scss" scoped>

</style>