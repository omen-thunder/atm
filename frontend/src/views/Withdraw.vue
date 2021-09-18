<template>

  <kings-atm>

    <withdraw-form/>

  </kings-atm>

</template>

<script>
import KingsAtm from "../components/AtmLayout/KingsAtmContainer.vue";
import WithdrawForm from "../components/WithdrawForm.vue";

export default {
  name: "Withdraw",
  components: {
    WithdrawForm,
    KingsAtm
  }
}
</script>

<style scoped>

</style>