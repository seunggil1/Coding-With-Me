import { store } from 'quasar/wrappers';
import { createPinia } from 'pinia';

export * from './alert.store';
export * from './auth.store';
export * from './users.store';
export * from './class.store';
export * from './exam.store';
export * from './teacherVideo.store.js';
export * from './studentVideo.store.js';
export * from './ExamProgress/common.js';
export * from './ExamProgress/student.js';
export * from './ExamProgress/teacher.js';
export * from './Video/common.js';
export * from './Video/student.js';
export * from './Video/teacher.js';
/*
 * If not building with SSR mode, you can
 * directly export the Store instantiation;
 *
 * The function below can be async too; either use
 * async/await or return a Promise which resolves
 * with the Store instance.
 */

export default store((/* { ssrContext } */) => {
	const pinia = createPinia();

	// You can add Pinia plugins here
	// pinia.use(SomePiniaPlugin)

	return pinia;
});
