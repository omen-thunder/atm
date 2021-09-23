<template>

  <kings-atm-container>

    <div class="flex-grow mx-4">
        <deposit-form/>
    </div>

  </kings-atm-container>

</template>

<script>
import KingsAtmContainer from "../components/Base/KingsAtmContainer.vue";
import KingsButton from '../components/Base/KingsButton.vue';
import DepositForm from "../components/DepositForm.vue";

export default {
  name: "Deposit",
  components: {
    DepositForm,
    KingsAtmContainer,
    KingsButton,
  },

  methods: {
    async handleCancel() {
      this.$router.push('/home');
    },
  },
}
</script>

<style scoped>

</style>