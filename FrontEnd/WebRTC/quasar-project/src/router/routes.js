import MainLayout from 'layouts/MainLayout.vue';
import VideoLayout from 'layouts/VideoLayout.vue';
import TeacherLectureLayout from 'src/layouts/TeacherLectureLayout.vue';
import TeacherExamLayout from 'src/layouts/TeacherExamLayout.vue';
import StudentExamLayout from 'src/layouts/StudentExamLayout.vue';
import TestLayout from 'src/layouts/TestLayout.vue';

const routes = [
  {
    path: '/',
    component: TestLayout,
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
		component: () => import('layouts/GoldenLayout.vue')
	},

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue')
  }
]

export default routes
