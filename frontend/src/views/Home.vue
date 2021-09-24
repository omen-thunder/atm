<template>
  <kings-atm-container>
    <div class="home-page flex-grow mx-4">
      <logo/>
      <home-options/>
    </div>
  </kings-atm-container>
</template>

<script>
import KingsAtmContainer from '../components/Base/KingsAtmContainer.vue';
import HomeOptions from "../components/HomeOptions.vue";
import Logo from "../components/Base/KingsLogo.vue";

export default {
  name: "Home",
  components: {
    Logo,
    HomeOptions,
    KingsAtmContainer,
  },
  methods: {

  }
};
</script>

<style scoped>

</style>