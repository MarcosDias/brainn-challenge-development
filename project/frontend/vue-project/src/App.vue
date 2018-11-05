<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<style lang="scss">
@import './assets/style/common';
@import './assets/style/variable';

.vue-input-tag-wrapper {
  @extend .input-style;
  width: 200px;
  box-sizing: border-box;
  width: 100%;
}

.vue-input-tag-wrapper .input-tag {
  @extend .input-style;
  background-color: $black-color;
  color: $white-color;
}

.vue-input-tag-wrapper .input-tag .remove {
  color: $white-color;
}
</style>
