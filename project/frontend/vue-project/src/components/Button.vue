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
  align-items: center;
  background: $black-color;
  border: none;
  border-radius: $size-radius;
  box-shadow: 1px 1px 1px #000000;
  color: $white-color;
  cursor: pointer;
  display: flex;
  font-size: 16px;
  padding: .5em 2em;

  &:hover {
    opacity: 0.7;
  }
}

.icon-triangle {
  border-top: $size-radius solid transparent;
  border-left: $size-radius solid $white-color;
  border-bottom: $size-radius solid transparent;
  margin-left: $size-radius;
}
</style>
