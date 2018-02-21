<template>
  <div id="app">
    <img src="./assets/logo.png">
    <router-view/>
    <div>{{ $t("message.hello") }}</div>
    <div>{{$t("message.welcome")}}</div>
    <div>{{welcomeMessage}}</div>
    <div @click="tabzhCHS" style="cursor:pointer">切换语言为中文</div>
    <div @click="tabzhCHT" style="cursor:pointer">切换语言为繁体</div>
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      username: 'name:'
    }
  },
  computed: {
    welcomeMessage () {
      return this.username + this.$t('message.welcome')
    }
  },
  methods: {
    tabzhCHS () {
      this.$i18n.locale = 'zhCHS'
    },
    tabzhCHT () {
      this.$i18n.locale = 'zhCHT'
    }
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
    margin-top: 60px;
  }

</style>
