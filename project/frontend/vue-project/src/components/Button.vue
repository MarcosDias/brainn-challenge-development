<template>
<div>
  <button class="btn">
    {{label}}
    <span class="icon-triangle" />
  </button>
</div>
</template>

<script>
export default {
  name: 'Button',
  props: ['label']
}
</script>

<style lang="scss" scoped>
@import '../assets/style/variable';

.btn {
  display: flex;
  align-items: center;
  box-shadow: 1px 1px 1px #000000;
  background: $black-color;
  border: none;
  color: $white-color;
  font-size: 16px;
  padding: .5em 2em;
  border-radius: $size-radius;
  cursor: pointer;

  &:hover {
    opacity: 0.7;
  }
}

.icon-triangle {
  margin-left: $size-radius;
  border-top: $size-radius solid transparent;
  border-left: $size-radius solid $white-color;
  border-bottom: $size-radius solid transparent;
}
</style>
