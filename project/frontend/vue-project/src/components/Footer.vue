<template>
  <footer class="footer">
    <span>
      make with
      <span class="heart"></span>
      by <a class="footer__self-link" href="http://github.com/marcosdias">Marcos Dias</a>
    </span>
  </footer>
</template>

<script>
export default {
  name: 'Footer'
}
</script>

<style lang="scss" scoped>
@import '../assets/style/variable';

.footer {
  background-color: $black-color;
  bottom: 0;
  color: $white-color;
  left: 0;
  padding:  1em;
  position: fixed;
  text-align: center;
  width: 100%;

  &__self-link {
    color: $white-color;
    text-decoration: underline;
  }
}

.heart:before {
  color: red;
  content: '\2665';
}
</style>
