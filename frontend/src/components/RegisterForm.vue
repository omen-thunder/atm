<template>

    <div id="withdraw-form" class="grid lg:gap-3 grid-cols-1">

      <p class="text-xl lg:text-2xl xl:text-2xl font-semibold text-yellow-100">Register For a New Account</p>

      <kings-input label="Name" placeholder="Enter your name" v-model="firstName"/>

      <kings-input type="password" label="Card Pin" placeholder="Create a card pin" v-model="pin"/>

      <kings-input type="number" label="Initial Amount Balance" placeholder="$" v-model.number="depositAmount"/>

      <div class="buttons flex flex-col">
        <kings-button class="my-4" @click="createAccount" button-type="primary"> Create Account </kings-button>
        <kings-button @click="handleCancel"> Cancel</kings-button>
      </div>
      <kings-error v-model:form-error="formError"/>
    </div>
</template>

<script>
import KingsInput from "./Base/KingsInput.vue";
import KingsButton from "./Base/KingsButton.vue";
import KingsError from "./Base/KingsError.vue";

import AXIOS from "../axios.js";

export default {
  name: "RegisterForm",
  components: {
    KingsError,
    KingsButton,
    KingsInput
  },
  data() {
    return {
      firstName: '',
      lastName: '',
      pin: '',
      depositAmount: '',
      formError: '',
      createdUser: null,
    }
  },
  methods: {
    async handleCancel() {
      await this.$router.back();
    },
    async createAccount() {
      if (!this.firstName || !this.depositAmount || !this.pin) {
        this.formError = 'Please fill out all the fields';
        return;
      }

      try {
        const postObj = {
          balance: this.depositAmount * 100,
          cards: [{
              pin: this.pin
            }
          ]
        }

        let response = await AXIOS.post('/api/account/create', postObj);

        if (response.status === 200) {

          if (response.data.success) {
            await this.$router.push({name: "NewAccount", params: { id: response.data.result.cards[0].id }})
          } else {
            this.formError = response.data.error;
          }

        }
      } catch (e) {
        this.formError = e;
      }

    }
  }
}
</script>

<style scoped>

</style>