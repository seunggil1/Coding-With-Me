// import { route } from 'quasar/wrappers';
import {
	createRouter,
	// createMemoryHistory,
	createWebHistory,
	createWebHashHistory,
} from 'vue-router';
import routes from './routes';
// import { useAuthStore } from 'src/stores';
const createHistory =
	process.env.VUE_ROUTER_MODE === 'history'
		? createWebHistory
		: createWebHashHistory;

/*
 * If not building with SSR mode, you can
 * directly export the Router instantiation;
 *
 * The function below can be async too; either use
 * async/await or return a Promise which resolves
 * with the Router instance.
 */

export default createRouter({
	scrollBehavior: () => ({ left: 0, top: 0 }),
	routes,

	// Leave this as is and make changes in quasar.conf.js instead!
	// quasar.conf.js -> build -> vueRouterMode
	// quasar.conf.js -> build -> publicPath
	history: createHistory(process.env.VUE_ROUTER_BASE),
});
// 로그인 되어있지 않을 시 조치하는 부분
// Router.beforeEach(async to => {
// 	// redirect to login page if not logged in and trying to access a restricted page
// 	const publicPages = ['/login', '/join'];
// 	const authRequired = !publicPages.includes(to.path);
// 	const auth = useAuthStore();

// 	if (authRequired && !auth.user) {
// 		auth.returnUrl = to.fullPath;
// 		return '/join';
// 	}
// });
