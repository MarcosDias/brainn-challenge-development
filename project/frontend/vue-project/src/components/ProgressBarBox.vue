<template>
  <div>
    <ProgressBar class="progressbar" />
    <p>getting the repositories list from Github...</p>
  </div>
</template>

<script>
import ProgressBar from './ProgressBar'

export default {
  name: 'ProgressBarBox',
  components: { ProgressBar }
}
</script>

<style lang="scss" scoped>

.progressbar {
  margin-bottom: 5vh;
}
</style>
