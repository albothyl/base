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
            <b-nav-item href="#">token : {{token}}</b-nav-item>

            <!-- login -->
            <div>
                <b-button id="show-btn" pill variant="primary" @click="$bvModal.show('bv-modal-login')">Login</b-button>
                <b-modal id="bv-modal-login" hide-footer>
                <template v-slot:modal-title>
                    Login
                </template>
                <div class="d-block text-center">
                    <b-form-group>
                    <b-form-input placeholder="username" />
                    </b-form-group>
                    <b-form-group>
                    <b-form-input placeholder="password" />
                    </b-form-group>
                </div>
                <b-button block variant="primary" @click="$bvModal.hide('bv-modal-login')">login</b-button>
                <b-button class="mt-3" block @click="$bvModal.hide('bv-modal-login')">close</b-button>
                </b-modal>
            </div>

            <!-- register -->
            <div>
                <b-button id="show-btn" pill variant="outline-danger" @click="$bvModal.show('bv-modal-register')">Register</b-button>
                <b-modal id="bv-modal-register" hide-footer>
                <template v-slot:modal-title>
                    Register
                </template>
                <div class="d-block text-center">
                    <b-form-group>
                    <b-form-input placeholder="username" />
                    </b-form-group>
                    <b-form-group>
                    <b-form-input placeholder="email" />
                    </b-form-group>
                    <b-form-group>
                    <b-form-input placeholder="password" />
                    </b-form-group>
                </div>
                <b-button block variant="danger" @click="$bvModal.hide('bv-modal-register')">register</b-button>
                <b-button class="mt-3" block @click="$bvModal.hide('bv-modal-register')">close</b-button>
                </b-modal>
            </div>

            </b-navbar-nav>
        </b-collapse>
        </b-navbar>
    </div>
</template>
<script>
export default {
    name: 'main-header',
    computed: {
        token() { return this.$store.getters.token}
    }
}
</script>