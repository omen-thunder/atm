<template>

  <div class="w-80">
    <div id="withdraw-form" class="grid gap-3 xl:gap-5 grid-cols-1 max-w-lg">
      <h1 class="text-3xl lg:text-4xl font-semibold text-yellow-100">How much would you like to withdraw?</h1>
      <kings-input type="number" v-model="amount" placeholder="$"/>
      <kings-button button-type="primary" @click="withdraw"> Withdraw </kings-button>
      <kings-button @click="handleCancel"> Cancel </kings-button>
    </div>

    <kings-error :form-error="formError" />
  </div>
</template>

<script>
import AXIOS from "../axios.js";
import KingsInput from "./Base/KingsInput.vue";
import KingsButton from "./Base/KingsButton.vue";
import KingsError from "./Base/KingsError.vue";
export default {
  name: "WithdrawForm",
  components: {KingsError, KingsButton, KingsInput},
  data() {
    return {
      amount: '',
      formError: null
    }
  },
  methods: {
    async withdraw() {
      if (!this.amount) {
        this.formError = "Please enter an amount"
        return;
      }

      try {
        let response = await AXIOS.post(`/api/transaction/withdraw/${this.amount * 100}`)

        if (response.status === 200 && response.data.success) {
          await this.$router.push({ name: "Receipt", params: { id: response.data.result.id}})
        }
        else {
          this.formError = response.data.error;
        }

      }
      catch (e) {
        this.formError = e;
      }


    },
    handleCancel() {
      this.$router.push("/home");
    }
  }
}
</script>

<style scoped>

</style>