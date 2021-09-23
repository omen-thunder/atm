<template>
  <kings-atm-container>
    <div class="flex flex-col justify-center">
      <div class="text-center mb-8">
        <h1 class="text-3xl mb-2">Account Balance</h1>
        <h2 class="text-xl">A/C# {{ accountNumber }}</h2>
        <p class="text-4xl my-8 text-yellow-200">$ {{ accountBalance }}</p>
      </div>
      <kings-button :buttonType="'primary'" @click="handleCancel">Confirm</kings-button>
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

  methods: {
    async getBalance() {
      let ac = await this.$store.dispatch('getAccount');
      this.accountBalance = ac.balance;
      this.accountNumber = ac.id;
    },

    async handleCancel() {
      this.$router.push('/home');
    },
  }
}
</script>

<style scoped>

</style>
