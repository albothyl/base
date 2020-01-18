import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        token: ''
    },
    getters: {
        // token 을 사용하는 게터
        // token(state) {return state.token}
        loginedUsername(state) {return localStorage.username},
        token(state) {return localStorage.accessToken},
        logined(state) {return localStorage.accessToken?true:false}
    },
    mutations: {
        // token 을 셋팅하는 뮤테이션
        setToken(state, payload) {
            state.token = payload.loginedData.accessToken
            localStorage.username = payload.loginedData.username
            localStorage.accessToken = payload.loginedData.accessToken
        },
        // token 을 삭제하는 뮤테이션
        deleteToken(state) {
            state.token = ''
            delete localStorage.username
            delete localStorage.accessToken
        }
    },
    actions: {
        // token 저장
        doLogin({ commit }, loginedData) {
            commit('setToken', { loginedData })
        },
        // token 삭제
        doLogout ({commit}) {
            commit('deleteToken')
        }
    }
})

export default store