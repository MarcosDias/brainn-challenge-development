<template>
  <div>

    <table>
      <thead>
        <tr>
          <th>repository</th>
          <th>description</th>
          <th>language</th>
          <th>tags</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="repo in dataFiltered" :key="repo.id">
          <td>
            <a href="repo.link">{{repo.name}}</a></td>
          <td>{{repo.description}}</td>
          <td>{{repo.language}}</td>
          <td>
            <Tag
              v-if="!!repo.tags && !!repo.tags.length"
              v-for="(tag, index) in repo.tags"
              :key="index"
              :name="`#${tag}`"
              :read-only="true" />
          </td>
          <td>
            <a href="#" @click.prevent="openModal(repo)">edit</a>
          </td>
        </tr>
      </tbody>
    </table>

    <Modal
      v-if="isModalVisible"
      @close="isModalVisible = false"
      :repository="selectedRepository" />
  </div>
</template>

<script>
import Modal from '@/components/Modal'
import Tag from '@/components/Tag'

export default {
  name: 'SearchadbleTable',
  props: [ 'data', 'search' ],
  components: { Modal, Tag },
  data: () => ({
    isModalVisible: false,
    selectedRepository: null
  }),
  computed: {
    dataFiltered () {
      const hasSearch = !!this.search
      if (hasSearch) {
        return this.data.filter(repo => {
          return !!repo.tags && repo.tags.includes(this.search)
        })
      }
      return this.data
    }
  },
  methods: {
    openModal (repository) {
      this.selectedRepository = repository
      this.isModalVisible = true
    },
    closeModal () {
      this.repository = null
      this.isModalVisible = false
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/style/variable';

table {
  display: table;
  width: 100%;

  th {
    background-color: $black-color;
    border: $border-config;
    border-inline-end-color: $white-color;
    color: $white-color;
    padding: $padding-table;
    text-align: left;
  }

  th:last-child {
    border-right-color: $black-color;
  }

  tr:nth-child(even){
    background-color: lighten($black-color, 70%);
  }

  td {
    border: $border-config;
    padding: $padding-table;
  }
}
</style>
