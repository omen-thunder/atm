<template>

  <div class="w-80">
    <div id="withdraw-form" class="grid gap-3 xl:gap-5 grid-cols-1 max-w-lg">
      <h1 class="text-3xl lg:text-4xl font-semibold text-yellow-100">How much would you like to withdraw?</h1>
      <kings-input type="number" v-model="amount" placeholder="$"/>
      <kings-button button-type="primary" @click="withdraw"> Withdraw </kings-button>
      <kings-button @click="handleCancel"> Cancel </kings-button>
    </div>
  </div>
</template>

<script>
import KingsInput from "./Base/KingsInput.vue";
import KingsButton from "./Base/KingsButton.vue";
export default {
  name: "WithdrawForm",
  components: {KingsButton, KingsInput},
  data() {
    return {
      amount: null
    }
  },
  methods: {
    withdraw() {

    },
    handleCancel() {
      this.$router.push("/home");
    }
  }
}
</script>

<style scoped>

@tailwind base;
@tailwind components;
@tailwind utilities;

</style>