<template>
  <kings-atm-container>
    <div class="grid grid-cols-1 gap-3 max-w-lg mx-4">
      <h1 class="text-3xl lg:text-4xl font-semibold text-yellow-100">Thank you for banking with <span class="text-yellow-200">XYZ</span></h1>
      <h2 class="text-lg lg:text-xl text-gray-300 font-semibold">Have a great day</h2>
      <kings-button button-type="primary" @click="backToLogin"> Return home </kings-button>
    </div>
  </kings-atm-container>
</template>

<script>
import KingsAtmContainer from "../components/Base/KingsAtmContainer.vue";
import KingsButton from "../components/Base/KingsButton.vue";
export default {
  name: "LogoutSuccess",
  components: { KingsAtmContainer, KingsButton },
  methods: {
    async backToLogin() {
        await this.$router.push("/login")
    }
  }
}
</script>

<style scoped>

</style>