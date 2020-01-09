<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="dark">
        <b-navbar-brand href="/"><i class="fas fa-home"></i>Home</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
            <b-nav-item href="/boards">board</b-nav-item>
            </b-navbar-nav>

            <!-- Right aligned nav items -->
            <b-navbar-nav class="ml-auto">
            <!-- <b-nav-item href="/boards">login</b-nav-item> -->

            <!-- login-->
            <div v-if="logined == false">
                <b-button id="show-btn" pill variant="primary" @click="$bvModal.show('bv-modal-login')">Login</b-button>
                <b-modal id="bv-modal-login" hide-footer>
                <template v-slot:modal-title>
                    Login
                </template>
                <div class="d-block text-center">
                    <b-form-group>
                    <b-form-input v-model="login.username" placeholder="username" />
                    </b-form-group>
                    <b-form-group>
                    <b-form-input v-model="login.password" placeholder="password" />
                    </b-form-group>
                </div>
                <b-button block variant="primary" @click="signin">login</b-button>
                <b-button class="mt-3" block @click="$bvModal.hide('bv-modal-login')">close</b-button>
                </b-modal>
            </div>

            <!-- logout -->
            <div v-if="logined == true">
                <b-button pill @click="onClickLogout">Logout</b-button>
            </div>

            <!-- register -->
            <div v-if="logined == false">
                <b-button id="show-btn" pill variant="outline-danger" @click="$bvModal.show('bv-modal-register')">Register</b-button>
                <b-modal id="bv-modal-register" hide-footer>
                <template v-slot:modal-title>
                    Register
                </template>
                <div class="d-block text-center">
                    <b-form-group>
                    <b-form-input v-model="register.username" placeholder="username" />
                    </b-form-group>
                    <b-form-group>
                    <b-form-input v-model="register.email" placeholder="email" />
                    </b-form-group>
                    <b-form-group>
                    <b-form-input v-model="register.password" placeholder="password" />
                    </b-form-group>
                </div>
                <b-button block variant="danger" @click="signup">register</b-button>
                <b-button class="mt-3" block @click="$bvModal.hide('bv-modal-register')">close</b-button>
                </b-modal>
            </div>

            </b-navbar-nav>
        </b-collapse>
        </b-navbar>
        token : {{token}}
    </div>
</template>
<script>
import router from '../router'
import store from '../store.js'

router.beforeEach((to, from, next) => {
  // to : 이동할 url
  // from : 현재 url
  // next : to에서 지정한 url로 이동하기 위해 꼭 호출해야 하는 함수
  if (to.matched.some((routeInfo) => {
    return routeInfo.meta.loginRequired;
  })) {
    if(!store.getters.token?true:false) {
        alert('Login Please!');
        router.push('/')
    } else {
        next()
    }
  } else {
    next()
  }

})

export default {
    name: 'main-header',
    data() {
        return {
            login: {
                username: '',
                password: '',
            },
            register: {
                username: '',
                email: '',
                password: '',
            }
        }
    },
    computed: {
        token() { return this.$store.getters.token },
        logined() { return  this.$store.getters.token?true:false }
    },
    methods: {
        signin() {
            this.$http.post('http://localhost:8082/api/auth/signin',{
			    username:this.login.username,
                password:this.login.password
		    }).then((response) => {
                if(response.data.accessToken !== null) {
                    alert('로그인 성공')
                    this.login = ''
                    console.log(response.data)

                    // 로그인. vuex store 에 token 저장
                    this.$store.dispatch('doLogin', response.data.accessToken)

                    this.$router.go(this.$router.currentRoute)
                } else {
                    alert('로그인 실패! ')
                    console.log(response.data)
                }
		    }).catch(error => {
                alert('로그인 실패! '+error)
                console.log(error)
            })

            this.$bvModal.hide('bv-modal-login')
        },
        signup() {
            this.$http.post('http://localhost:8082/api/auth/signup',{
			    username:this.register.username,
                email:this.register.email,
                password:this.register.password
		    }).then((response) => {
                if(response.data.message === 'User registered successfully!') {
                    alert('등록 성공')
                    this.register = ''
                    console.log(response.data)
                } else {
                    alert('등록 실패! '+response.data)
                }
                
		    }).catch(error => {
                alert('등록 실패! '+error)
                console.log(error)
            })

            this.$bvModal.hide('bv-modal-register')
        },
        onClickLogout() {
            this.$store.dispatch('doLogout').then(() => this.$router.go(this.$router.currentRoute))
        }
    }
}
</script>