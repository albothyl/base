<template>
  <div id="app">
    <!-- <img src="./assets/logo.png"> -->
    
    <!-- header -->
    <div id="header">
      <main-header/>
    </div>

    <!-- body -->
    <router-view/>
  </div>
</template>

<script>
import MainHeader from './components/MainHeader.vue'

export default {
  name: 'App',
  components: {
    'main-header': MainHeader,
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
