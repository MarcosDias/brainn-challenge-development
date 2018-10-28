<template>
  <header id="header">
    <h1 class="container logo">githubstars</h1>
  </header>
</template>

<script>
export default {
  name: 'Header'
}
</script>

<style lang="scss" scoped>
@import '../assets/style/variable';

#header {
  background: $black-color;
  padding: 30px;
}

.logo {
  color: $white-color;
  font-size: 1.7em;
  font-weight: bold;
}
</style>
