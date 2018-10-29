<template>
  <div class="progress-bar stripes">
    <span style="width: 100%"></span>
  </div>
</template>

<script>
export default {
  name: 'ProgressBar'
}
</script>

<style lang="scss" scoped>
@import '../assets/style/variable';

.progress-bar {
  background-color: $black-color;
  height: 25px;
  width: 100%;
  border-radius: $size-radius;
}

.progress-bar span {
  display: inline-block;
  height: 100%;
  border-radius: $size-radius;
  background-color: $black-color;
}

.stripes span {
  background-size: 60px 60px;
  background-image: linear-gradient(135deg,
    rgba(255, 255, 255, .15) 25%, transparent 25%, transparent 50%,
    rgba(255, 255, 255, .15) 50%,
    rgba(255, 255, 255, .15) 75%, transparent 75%, transparent);
  animation: animate-stripes 3s linear infinite;
}

@keyframes animate-stripes {
    0% {background-position: 0 0;} 100% {background-position: 60px 0;}
}
</style>
