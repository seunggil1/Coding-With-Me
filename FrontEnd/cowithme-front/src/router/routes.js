import VideoLayout from 'layouts/VideoLayout.vue';
import TeacherLectureLayout from 'src/layouts/TeacherLectureLayout.vue';
import TeacherExamLayout from 'src/layouts/TeacherExamLayout.vue';
import StudentExamLayout from 'src/layouts/StudentExamLayout.vue';

const routes = [
	{
		path: '/',
		name: 'home',
		component: () => import('layouts/MainLayout.vue'),
		children: [
			{
				path: '/',
				name: 'home',
				component: () => import('pages/IndexPage.vue'),
				// redirect: '/',
			},
			{
				path: '/makeClass',
				component: () => import('pages/teacher/MakeClassPage.vue'),
			},
			{
				path: '/mypage',
				component: () => import('pages/account/MyPagePage.vue'),
			},
			{
				path: '/addStudent',
				name: 'addStudent',
				props: true,
				component: () => import('pages/teacher/AddStudentPage.vue'),
			},
			{
				path: '/classDetail/:classId',
				name: 'classDetail',
				// props: true,
				component: () => import('pages/teacher/classDetailPage.vue'),
			},
			{
				path: '/makeExam/:classId',
				name: 'makeExam',
				props: true,
				component: () => import('pages/teacher/MakeExamPage.vue'),
			},
			{
				path: '/reportCard/:userId',
				name: 'reportCard',
				props: true,
				component: () => import('pages/student/ScorePage.vue'),
			},
			{
				path: '/studyRooms',
				name: 'studyRoom',
				component: () => import('pages/student/StudyRoomSearchPage.vue'),
			},
		],
	},
	// 로그인/회원가입
	{
		path: '/login',
		component: () => import('pages/account/LoginPage.vue'),
	},
	{
		path: '/IDfind',
		component: () => import('pages/account/IDFindPage.vue'),
	},
	{
		path: '/PWfind',
		component: () => import('pages/account/PWFindPage.vue'),
	},
	{
		path: '/join',
		name: 'join',
		component: () => import('pages/account/SignUpPage.vue'),
	},
	// home
	{
		path: '/homepage',
		name: 'homepage',
		component: () => import('pages/StudentHomePage.vue'),
	},
	// Always leave this as last one,
	// but you can also remove it
	{
		path: '/:catchAll(.*)*',
		component: () => import('pages/ErrorNotFound.vue'),
	},
	{
		path: '/video',
		component: VideoLayout,
	},
	{
		path: '/lecture',
		component: TeacherLectureLayout,
	},
	{
		path: '/exam',
		component: TeacherExamLayout,
	},
	{
		path: '/exam2',
		component: StudentExamLayout,
	},
	{
		path: '/layout',
		component: () => import('layouts/GoldenLayout.vue'),
	},
];

export default routes;
