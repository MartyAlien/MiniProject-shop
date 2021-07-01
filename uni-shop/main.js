import Vue from 'vue'
import App from './App'
import {api} from "request/api.js"
import { pathToBase64, base64ToPath } from './js_sdk/mmmm-image-tools/index.js'

// 挂在自定义的函数
Vue.prototype.$api=api
Vue.prototype.$pathToBase64=pathToBase64
Vue.prototype.$base64ToPath=base64ToPath

Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
