// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'

// Components
import './components'

// Plugins
import './plugins'

// Sync router with store
import { sync } from 'vuex-router-sync'

// Application imports
import App from './App'
import i18n from '@/i18n'
import router from '@/router'
import store from '@/store'
import './utils/globalVal'

// Sync store with router
sync(store, router)

Vue.config.productionTip = false

// Check the authority, and forward
router.beforeEach((to, from, next) => {
    // if (to.meta.title) {
    //     document.title = to.meta.title
    // }
    // ユーザー名
    const role = localStorage.getItem('ms_username');
    // check the url whether is /login
    if(!role && to.path !== '/login') {
        next('/login');
    } else {  // almost login
        next();
    }
});

/* eslint-disable no-new */
new Vue({
    // i18n,
    router,
    store,
  render: h => h(App)
}).$mount('#app')
