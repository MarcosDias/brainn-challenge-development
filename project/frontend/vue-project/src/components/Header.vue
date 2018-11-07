<template>
  <header class="header container">
    <h1 class="header__logo">githubstars</h1>
    <router-link class="header__menu" to="/" v-if="$route.path !== '/'">home</router-link>
  </header>
</template>

<script>
export default {
  name: 'Header'
}
</script>

<style lang="scss" scoped>
@import '../assets/style/variable';
@import '../assets/style/common';

.header {
  background: $black-color;
  display: flex;
  justify-content: space-between;
  padding-bottom: 3vh;
  padding-top: 3vh;

  &__logo {
    color: $white-color;
    font-size: 1.7em;
    font-weight: bold;
  }

  &__menu {
    color: #fff;
  }
}
</style>
