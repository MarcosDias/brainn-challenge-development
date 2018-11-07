<template>
  <div class="input-tag">
    <Tag
      v-for="(nameTag, index) in tags" :key="index"
      :name="nameTag" :index="index"
      @remove-tag="removeTag" />
    <input
      type="text" class="input-tag__input" :placeholder="placeholder"
      @keyup.enter="addTag()" v-model="newTag">
  </div>
</template>

<script>
import Tag from './Tag'

export default {
  name: 'InputTag',
  components: { Tag },
  props: {
    tags: {
      default: []
    },
    placeholder: {
      type: String
    }
  },
  data: () => ({
    newTag: null
  }),
  methods: {
    addTag () {
      if (!!this.newTag && !this.tags.includes(this.newTag)) {
        this.tags.push(this.newTag)
        this.newTag = null
      }
    },
    removeTag (tagRemove) {
      this.tags = this.tags.filter(tag => (tag !== tagRemove))
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/style/common';
@import '../assets/style/variable';

.input-tag {
  @extend .input-style;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  padding: 2px;

  &__input {
    color: $black-color;
    padding: 5px;
    border: none;
  }
}
</style>
