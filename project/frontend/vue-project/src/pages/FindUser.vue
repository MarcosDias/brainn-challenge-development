<template>
  <main class="main">

    <div>

      <div class="main__box" v-if="!request">
        <div class="main__label-input">
          <span>https://github.com/</span>
          <input class="input" type="text" placeholder="username" v-model="username">
        </div>

        <div class="main__btn">
          <btn label="get repositories" @click.native="findRepositoriesByUser" />
        </div>
      </div>

      <ProgressBarBox v-else/>
    </div>

  </main>
</template>

<script>
import Button from '@/components/Button'
import ProgressBarBox from '@/components/ProgressBarBox'

import http from '@/services/http'

export default {
  name: 'FindUser',
  components: { 'btn': Button, ProgressBarBox },
  data: () => ({
    request: false,
    messageError: null,
    username: ''
  }),
  methods: {
    async findRepositoriesByUser () {
      this.request = true
      await http.getRepositoriesByUser(() => {
        this.request = false
        this.$router.push(`/${this.username}`)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/style/variable';
@import '../assets/style/common';

.main {
  align-items: center;
  display: flex;
  height: 70vh;
  justify-content: center;

  &__label-input {
    flex-direction: column;
  }

  &__box {
    align-items: center;
    display: flex;
    flex-direction: column;
  }

  &__btn {
    margin-top: 10px;
  }
}
</style>
