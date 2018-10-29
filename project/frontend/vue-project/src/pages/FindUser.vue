<template>
  <main class="main">

    <div>

      <div class="main--box" v-if="!request">
        <div class="main--label-input">
          <span>https://github.com/</span>
          <input class="main--input" type="text" placeholder="username">
        </div>

        <div class="main--btn">
          <btn label="get repositories" @click.native="findRepositoriesByUser" />
        </div>
      </div>

      <ProgressBarBox v-else/>
    </div>

  </main>
</template>

<script>
import Button from '../components/Button'
import ProgressBarBox from '../components/ProgressBarBox'

import http from '../services/http'

export default {
  name: 'FindUser',
  components: { 'btn': Button, ProgressBarBox },
  data: () => ({
    request: false,
    messageError: null
  }),
  methods: {
    async findRepositoriesByUser () {
      this.request = true
      await http.getRepositoriesByUser(() => {
        this.request = false
      })
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/style/variable';

.main {
  height: 70vh;
  display: flex;
  justify-content: center;
  align-items: center;

  &--label-input {
    flex-direction: column;
  }

  &--box {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

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
