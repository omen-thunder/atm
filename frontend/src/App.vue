<template>
  <div>
    <router-view />
  </div>
</template>

<script>
export default {
  async created() {
    // TODO: get/set machine number per session.
    await this.$store.commit('setMachineNumber',1001);
    await this.$store.dispatch('initialise');
  }
}

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

body {
  margin: 0;
}

</style>

