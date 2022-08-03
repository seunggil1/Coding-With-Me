import MainLayout from 'layouts/MainLayout.vue';
import VideoLayout from 'layouts/VideoLayout.vue';

const routes = [
  {
    path: '/',
    component: MainLayout,
  },
  {
    path: '/video',
    component: VideoLayout,
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
