import { api } from 'src/boot/axios.js';

/**
 * conferenceKey(Int), userKey(Int)
 * 
 * 회의 key값과 사용자에 대한 key 값을 받아 spring에 출석 기록 요청을 보낸다.
 */
export const entry = async (conferenceKey, userKey) => {
    try {
        const response = await api.post(`/records/attendances`, {
            conferenceId: conferenceKey,
            userId: userKey
        });
        console.log('출입기록 생성 성공', response.data);
    } catch (error) {
        console.error('출입기록 생성 실패', error);
    }
    
}

/**
 * conferenceKey(Int), userKey(Int)  
 * 
 * 회의 key값과 사용자에 대한 key 값을 받아 spring에 퇴실 기록 요청을 보낸다.
 */
export const exit = async (conferenceKey, userKey) => {
    try {
        const response = await api.put(`/records/attendances`, {
            conferenceId: conferenceKey,
            userId: userKey
        });
        console.log('퇴실기록 생성 성공', response.data);
    } catch (error) {
        console.error('퇴실기록 생성 실패', error);
    }
    
}
