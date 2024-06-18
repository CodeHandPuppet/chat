import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
import { App } from 'vue';


export function setupAntDesignVue(app: App) {
    app.use(Antd)
}