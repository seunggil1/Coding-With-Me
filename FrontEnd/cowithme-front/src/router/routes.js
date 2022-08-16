import IntroPage from '../pages/IntroPage.vue';
import MainLayout from '../layouts/MainLayout.vue';
import IndexPage from '../pages/IndexPage.vue';
import MakeClassPage from '../pages/teacher/MakeClassPage.vue';
import MypagePage from '../pages/account/MypagePage.vue';
import AddStudentPage from '../pages/teacher/AddStudentPage.vue';
import ClassDetailPage from '../pages/teacher/ClassDetailPage.vue';
import MakeExamPage from '../pages/teacher/MakeExamPage.vue';
import ScorePage from '../pages/student/ScorePage.vue';
import StudyRoomSearchPage from '../pages/student/StudyRoomSearchPage.vue';
import TestDetailPage from '../pages/teacher/TestDetailPage.vue';
import LoginPage from '../pages/account/LoginPage.vue';
import IDfindPage from '../pages/account/IDfindPage.vue';
import PWFindPage from '../pages/account/PWFindPage.vue';
import SignupPage from '../pages/account/SignupPage.vue';
import LoadingPage from '../pages/webRTC/LoadingPage.vue';
import TeacherLecturePage from '../pages/webRTC/TeacherLecturePage.vue';
import StudentLecturePage from '../pages/webRTC/StudentLecturePage.vue';
import TeacherExamPage from '../pages/webRTC/TeacherExamPage.vue';
import StudentExamPage from '../pages/webRTC/StudentExamPage.vue';
import ErrorNotFound from '../pages/ErrorNotFound.vue';

const routes = [
	{
		path: '/',
		name: 'intro',
		component: IntroPage,
	},
	{
		path: '/home',
		name: 'home',
		component: MainLayout,
		children: [
			{
				path: '/home',
				component: IndexPage,
				// redirect: '/',
			},
			{
				path: '/makeClass',
				component: MakeClassPage,
			},
			{
				path: '/mypage',
				component: MypagePage,
			},
			{
				path: '/addStudent',
				name: 'addStudent',
				props: true,
				component: AddStudentPage,
			},
			{
				path: '/classDetail/:classId',
				name: 'classDetail',
				// props: true,
				component: ClassDetailPage,
			},
			{
				path: '/makeExam/:classId',
				name: 'makeExam',
				props: true,
				component: MakeExamPage,
			},
			{
				path: '/reportCard/:userId',
				name: 'reportCard',
				props: true,
				component: ScorePage,
			},
			{
				path: '/studyRooms',
				name: 'studyRoom',
				component: StudyRoomSearchPage,
			},
			{
				path: '/testDetail/:testId',
				name: 'testDetail',
				props: true,
				component: TestDetailPage,
			},
		],
	},
	// 로그인/회원가입
	{
		path: '/login',
		name: 'login',
		component: LoginPage,
		beforeEnter: function (to, from, next) {
			if (localStorage.getItem('token')) {
				next('/home');
			}
			next();
		},
	},
	{
		path: '/IDfind',
		component: IDfindPage,
	},
	{
		path: '/PWfind',
		component: PWFindPage,
	},
	{
		path: '/join',
		name: 'join',
		component: SignupPage,
	},
	// Always leave this as last one,
	// but you can also remove it
	{
		path: '/loading',
		component: LoadingPage,
	},
	{
		path: '/teacherlecture',
		name: 'teacherlecture',
		component: TeacherLecturePage,
	},
	{
		path: '/studentlecture',
		component: StudentLecturePage,
	},
	{
		path: '/teacherexam',
		component: TeacherExamPage,
	},
	{
		path: '/studentexam',
		component: StudentExamPage,
	},
	{
		path: '/:catchAll(.*)*',
		component: ErrorNotFound,
	},
];

export default routes;
