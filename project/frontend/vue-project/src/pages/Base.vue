<template>
  <div class="base">
    <Header />

    <router-view></router-view>

    <Footer />
  </div>
</template>

<script>
import Header from '../components/Header'
import Footer from '../components/Footer'

export default {
  name: 'Base',
  components: { Header, Footer }
}
</script>

<style lang="scss">
@import '../assets/style/reset';
@import url(https://fonts.googleapis.com/css?family=Roboto+Mono:500,700);

.base {
  font-family: 'Roboto Mono', sans-serif;
}
</style>
