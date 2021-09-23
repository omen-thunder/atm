<template>
  <kings-atm-container>
    <div class="flex flex-col justify-center mx-4">
      <div class="text-center mb-8">
        <h2 class="text-xl">Acc No. {{ accountNumber }}</h2>
        <h1 class="text-3xl lg:text-4xl font-semibold text-yellow-100">Your current account balance</h1>
        <p class="text-5xl mt-5 text-yellow-200">${{ balanceWithTwoDecimals }}</p>
      </div>
      <kings-button :buttonType="'primary'" @click="handleCancel">Back to menu</kings-button>
      <kings-button @click="ejectCard" class="mt-4">Eject Card</kings-button>
    </div>
  </kings-atm-container>
</template>

<script>
import KingsAtmContainer from "../components/Base/KingsAtmContainer.vue";
import KingsButton from '../components/Base/KingsButton.vue';

export default {
  name: "CheckBalance",
  components: {
    KingsAtmContainer,
    KingsButton,
  },
  data() {
    return {
      accountBalance: 0,
      accountNumber: '',
    }
  },

  async mounted() {
    await this.getBalance();
  },
  computed: {
    balanceWithTwoDecimals() {
      return this.accountBalance.toFixed(2);
    }
  },

  methods: {
    async getBalance() {
      let ac = await this.$store.dispatch('getAccount');
      this.accountBalance = ac.balance/100;
      this.accountNumber = ac.id;
    },

    async handleCancel() {
      await this.$router.push('/home');
    },

    async ejectCard() {
      await this.$store.dispatch('logoutUser');
    }
  }
}
</script>

<style scoped>

</style>
