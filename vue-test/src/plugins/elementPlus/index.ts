import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { App } from 'vue';
import * as ElementPlusIconsVue from '@element-plus/icons-vue'



export function setupElementPlus(app: App) {
    app.use(ElementPlus)


    for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
        app.component(key, component)
    }
}