# Coding With Me

## 🗓️프로젝트 개요

- **진행 기간 : 2022.07.05 ~ 2022.08.19 (5주)**
- **목표**
  - 화상 수업, 강사-학생간 코드 전송, 코딩 테스트, 채팅 등 프로그래밍 수업에 필요한 기능들을 한 곳에 모은 프로그래밍 교육 서비스



## 👯팀원 정보 및 업무 분담 내역

강승길(FE, BE) - webRTC, web compiler 구현, 서버 개발

김동욱(팀장, BE) - ERD 설계, 서버 개발

김혜린(FE) - User & LMS 기능 개발, UI 설계, UI 구현

이성민(BE) - ERD 설계, 서버 개발

최태순(FE) - webRTC, UI 설계, UI 구현



## :paperclip: 발표 자료

https://drive.google.com/file/d/18LT0MX8mZmogb03t9eP4HJiSg1luOV0I/view?usp=sharing



## :information_desk_person: 시연 영상

https://drive.google.com/file/d/1k2rmB0Yzx4SGuA6UBA97MS54xq1HDmev/view?usp=sharing



## :video_camera: 광고 영상

https://drive.google.com/file/d/1-V-glNym-LMATyguBIpyaBFGU1gzE6C-/view?usp=sharing

![image-20220820221536834](README.assets/image-20220820221536834.png)



## :computer: 서비스 내용

### 초기 화면

![로그인](README.assets/%EB%A1%9C%EA%B7%B8%EC%9D%B8.gif)



### 학생 관리

![학생 제거,추가](README.assets/%ED%95%99%EC%83%9D%20%EC%A0%9C%EA%B1%B0,%EC%B6%94%EA%B0%80.gif)



### 시험 생성

![시험 등록](README.assets/%EC%8B%9C%ED%97%98%20%EB%93%B1%EB%A1%9D.gif)



### 강의 시작

![강의 접속, 출결 확인](README.assets/%EA%B0%95%EC%9D%98%20%EC%A0%91%EC%86%8D,%20%EC%B6%9C%EA%B2%B0%20%ED%99%95%EC%9D%B8.gif)



### code 전송

![코드 보내기](README.assets/%EC%BD%94%EB%93%9C%20%EB%B3%B4%EB%82%B4%EA%B8%B0.gif)



### 코드 받아보기

![코드 보내기](README.assets/%EC%BD%94%EB%93%9C%20%EB%B3%B4%EB%82%B4%EA%B8%B0-16612210305399.gif)



### 시험 응시하기

![문제 풀기, 제출하기](README.assets/%EB%AC%B8%EC%A0%9C%20%ED%92%80%EA%B8%B0,%20%EC%A0%9C%EC%B6%9C%ED%95%98%EA%B8%B0.gif)



## 기술 스택

![image-20220823113947380](README.assets/image-20220823113947380.png)

1. Sprint/Quasar (Vue) Server: AWS EC2 Ubuntu 20.04 LTS
2. Openvidu Server: Oracle Cloud Ubuntu 20.04 LTS (Openvidu Server의 경우 ARM CPU 사용불가)
3. Compiler Server: Oracle Cloud Ubuntu 20.04 LTS
4. Mysql: 20.04.2
5. Spring Boot: 2.4.5
6. Quasar: 1.0.5
7. Openvidu: 2.22.0
8. Nginx: 1.18.0
9. Node.js: 10.19.0
10. java: 1.8.0
11. gcc: 9.4.0
12. g++: 9.4.0
13. Python: 3.8.10



## ERD

![image-20220823111429633](README.assets/image-20220823111429633.png)



## 기능 정의서/API 명세서/테스트 시나리오

https://docs.google.com/spreadsheets/d/1hJ3F-pyaeCHFWGd1NqcsIKce6SUxkpg3S4VU9M1ses4/edit?usp=sharing



## 기획 자료(figma)

https://www.figma.com/file/xYiCDWV2GYnX3pQCz61hbz/%EC%BD%94%EC%9C%97%EB%AF%B8-%EA%B8%B0%ED%9A%8D-by-A304?node-id=0%3A1
