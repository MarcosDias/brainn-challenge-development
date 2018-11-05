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
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  background-color: $black-color;
  color: $white-color;
  text-align: center;
  padding:  1em;

  &__self-link {
    color: $white-color;
    text-decoration: underline;
  }
}

.heart:before {
  content: '\2665';
  color: red;
}
</style>
