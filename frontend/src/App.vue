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
@font-face {
  font-family: "Montserrat";
  src: local("Montserrat"),
  url(/src/fonts/Montserrat-Regular.ttf) format("truetype");
  font-weight: normal;
}

@font-face {
  font-family: "Montserrat";
  src: local("Montserrat"),
  url(/src/fonts/Montserrat-Medium.ttf) format("truetype");
  font-weight: auto;
}

@font-face {
  font-family: "Montserrat";
  src: local("Montserrat"),
  url(/src/fonts/Montserrat-Bold.ttf) format("truetype");
  font-weight: bold;
}

#app {
  font-family: Montserrat, Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

body {
  margin: 0;
}

</style>

