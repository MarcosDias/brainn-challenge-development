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
  background-color: $black-color;
  color: $white-color;
  font-size: .9em;
  margin-right: 4px;
  margin-top: 2px;
  margin-bottom: 2px;
  padding: 4px;
  font-size: .8em;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width: auto;

  &__close {
    text-decoration: none;
    color: $white-color;
    cursor: pointer;
    font-weight: 700;
    margin-left: 2px;
  }
}
</style>
