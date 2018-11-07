<template>
  <span class="tag">
    {{name}}
    <a v-if="!readOnly" class="tag__close" href="#" @click.prevent="remove">x</a>
  </span>
</template>

<script>
export default {
  name: 'Tag',
  props: {
    name: {
      type: String,
      required: true
    },
    readOnly: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    remove () {
      this.$emit('remove-tag', this.name)
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/style/common';
@import '../assets/style/variable';

.tag {
  @extend .input-style;
  align-items: center;
  background-color: $black-color;
  color: $white-color;
  display: flex;
  flex-direction: row;
  font-size: .8em;
  justify-content: space-between;
  margin-right: 4px;
  margin-top: 2px;
  margin-bottom: 2px;
  padding: 4px;

  &__close {
    color: $white-color;
    cursor: pointer;
    font-weight: 700;
    text-decoration: none;
    margin-left: 2px;
  }
}
</style>
