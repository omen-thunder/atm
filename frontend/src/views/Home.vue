<template>
  <div class="home">
    <!-- <img class="mx-auto" alt="Vue logo" src="../assets/logo.png" /> -->
    <!-- <HelloWorld msg="Welcome to Your Vue.js App" /> -->
    <KingsATM />
  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from "@/components/HelloWorld.vue";
import KingsATM from "@/components/KingsAtm.vue";

export default {
  name: "Home",
  components: {
    KingsATM,
  },
};
</script>
