<template>
  <div class="modal">
    <div class="modal__mask">

      <div class="modal__body">
        <label
          for="tags"
          class="modal__label">
          {{ `edit tags for ${repository.name}` }}
        </label>

        <InputTag
          class="modal__input"
          :tags="tags"
          :placeholder="'add new tags'" />

        <div class="modal__buttons">
          <button
            class="modal__btn-save btn"
            @click="saveTags">save</button>
          <button class="btn" @click="closeModal">cancel</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import InputTag from './InputTag'

export default {
  name: 'Modal',
  components: { InputTag },
  props: {
    repository: {
      type: Object,
      required: true
    }
  },
  data: () => ({
    tags: []
  }),
  created () {
    const cloneTags = this.repository.tags || []
    this.tags = cloneTags.slice()
  },
  methods: {
    closeModal () {
      this.$emit('close')
    },
    saveTags () {
      this.repository.tags = this.tags
      this.$emit('close')
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/style/common';
@import '../assets/style/variable';

.modal {
  &__body {
    background: #fff;
    width: 30%;
    padding: 12px;
    display: flex;
    flex-direction: column;
  }

  &__label {
    margin-bottom: 5px;
  }

  &__input {
    margin-bottom: 10px;
  }

  &__buttons {
    flex-wrap: wrap;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  &__btn-save {
    margin-right: 10px;
  }

  &__mask {
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: rgba($black-color, .4);
  }
}
</style>
