<template>

  <kings-atm-container>

    <div class="flex-grow mx-4">

      <withdraw-form/>
    </div>

  </kings-atm-container>

</template>

<script>
import KingsAtmContainer from "../components/Base/KingsAtmContainer.vue";
import WithdrawForm from "../components/WithdrawForm.vue";

export default {
  name: "Withdraw",
  components: {
    WithdrawForm,
    KingsAtmContainer,
  }
}
</script>

<style scoped>

</style>