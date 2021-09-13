<template>  
  <div class="home">
      <home-options/>  
  </div>
</template>

<script>
import HomeOptions from "@/components/HomeOptions";
export default {
  name: "Home",
  components: {
    HomeOptions
  },
  methods: {

  },
  beforeMount: async function(){
    // TODO: get/set machine number per session.
    this.$store.commit('setMachineNumber',1001);
  }
};
</script>

<style scoped>

</style>