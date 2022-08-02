import { boot } from 'quasar/wrappers';
import { SmartWidget, SmartWidgetGrid } from 'vue-smart-widget';

// "async" is optional;
// more info on params: https://v2.quasar.dev/quasar-cli/boot-files
export default boot(async (/* { app, router, ... } */ { app }) => {
	// something to do

	app.component(SmartWidget.name, SmartWidget);
	app.component(SmartWidgetGrid.name, SmartWidgetGrid);
});

export { SmartWidget, SmartWidgetGrid };
