<template>
  <div class="grid container">

    <input class="grid__input input" type="search" placeholder="search by tag" v-model="search">

    <SearchableTable class="grid__table" :data="listRepositories" :search="search" />

  </div>
</template>

<script>
import SearchableTable from '@/components/SearchableTable'

import http from '@/services/http'

export default {
  name: 'GridUser',
  components: { SearchableTable },
  props: ['user'],
  data: () => ({
    search: ''
  }),
  created () {
    this.listRepositories = http.getListRepositories()
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/style/common';

.grid {
  padding-top: 8vh;

  &__input {
    background: url(https://static.tumblr.com/ftv85bp/MIXmud4tx/search-icon.png) no-repeat 9px center;
    padding-left: 32px;
  }

  &__table {
    margin-top: 5vh;
  }
}
</style>
