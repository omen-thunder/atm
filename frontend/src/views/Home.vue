<template>  
  <div class="home-page">
    <kings-atm>
      <home-options/>
    </kings-atm>
  </div>
</template>

<script>
import HomeOptions from "../components/HomeOptions.vue";
import KingsAtm from "../components/AtmLayout/KingsAtmContainer.vue";

export default {
  name: "Home",
  components: {
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