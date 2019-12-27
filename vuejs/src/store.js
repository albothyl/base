import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        token: ''
    },
    getters: {
        // token 을 사용하는 게터
        token(state) {return state.token}
    },
    mutations: {
        // token 을 변경하는 뮤테이션
        setToken(state, payload) {
            state.token = payload.token
        }
    },
    actions: {
        // token 변경 처리
        doUpdate({ commit }, token) {
            commit('setToken', { token })
        }
    }
})

export default store