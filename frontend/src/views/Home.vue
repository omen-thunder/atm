<template>  
  <div class="home-page">
    <kings-atm>
      <home-options/>
    </kings-atm>
  </div>
</template>

<script>
import HomeOptions from "@/components/HomeOptions";
import KingsAtm from "@/components/AtmLayout/KingsAtmContainer";

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