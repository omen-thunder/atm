<template>


    <div id="home-options" class="grid gap-3 xl:gap-5 grid-cols-1 max-w-md mx-auto">

      <p class="text-xl lg:text-2xl xl:text-2xl font-semibold text-yellow-100">What would you like to do today?</p>
      <kings-button button-type="primary" @click="handleWithdraw"> Make Withdrawal </kings-button>
      <kings-button button-type="primary" @click="handleCheckBalance"> Check Balance </kings-button>
      <kings-button button-type="primary" @click="handleMakeDeposit"> Make Deposit </kings-button>
      <kings-button button-type="secondary" @click="handleCancel"> Eject Card </kings-button>

    </div>


</template>

<script>

import KingsButton from "./Base/KingsButton.vue";
export default {
  name: "HomeOptions",
  components: {KingsButton},
  methods: {
    async handleCancel() {
      await this.$store.dispatch('logoutUser');
    },
    handleWithdraw() {
      this.$router.push('/withdraw');
    },
    handleCheckBalance() {
      this.$router.push('/check-balance');
    },
    handleMakeDeposit() {

    }
  }
}
</script>

<style scoped>

</style>