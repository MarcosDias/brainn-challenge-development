<template>
  <main class="main">

    <div class="main--box">
      <span>https://github.com/</span>
      <input class="main--input" type="text" placeholder="username">
    </div>

    <div class="main--btn">
      <btn label="get repositories" />
    </div>

    <!-- <ProgressBarBox /> -->

  </main>
</template>

<script>
// import ProgressBarBox from '../components/ProgressBarBox'
import Button from '../components/Button'

export default {
  name: 'FindUser',
  components: { 'btn': Button }
}
</script>

<style lang="scss" scoped>
@import '../assets/style/variable';

.main {
  height: 70vh;
  display: flex;
  flex-flow: column;
  justify-content: center;
  align-items: center;

  &--btn {
    margin-top: 10px;
  }

  &--input {
    padding: 5px 10px;
    border: 2px solid $black-color;
    color: $black-color;
    width: 200px;
    border-radius: $size-radius;
  }
}
</style>
