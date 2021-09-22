<template>
  <kings-atm>
    <div class="home-page">
      <logo/>
      <home-options/>
    </div>
  </kings-atm>
</template>

<script>
import HomeOptions from "../components/HomeOptions.vue";
import KingsAtm from "../components/Base/KingsAtmContainer.vue";
import Logo from "../components/Base/KingsLogo.vue";

export default {
  name: "Home",
  components: {
    Logo,
    HomeOptions,
    KingsAtm
  },
  methods: {

  }
};
</script>

<style scoped>

@tailwind base;
@tailwind components;
@tailwind utilities;


</style>