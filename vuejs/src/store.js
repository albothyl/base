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
        token(state) {return localStorage.accessToken}
    },
    mutations: {
        // token 을 셋팅하는 뮤테이션
        setToken(state, payload) {
            state.token = payload.token
            localStorage.accessToken = payload.token
        },
        // token 을 삭제하는 뮤테이션
        deleteToken(state) {
            state.token = ''
            delete localStorage.accessToken
        }
    },
    actions: {
        // token 저장
        doLogin({ commit }, token) {
            commit('setToken', { token })
        },
        // token 삭제
        doLogout ({commit}) {
            commit('deleteToken')
        }
    }
})

export default store