<template>

    <div class="w-80">
      <div id="withdraw-form" class="grid lg:gap-3 grid-cols-1">

        <p class="text-xl lg:text-2xl xl:text-2xl font-semibold text-yellow-100">Register For a New Account</p>

        <kings-input label="Name" placeholder="Enter your name" v-model="firstName"/>

<!--        <kings-input label="Last Name" placeholder="Enter your last name" v-model="lastName"/>-->

        <kings-input type="password" label="Card Pin" placeholder="Create a card pin" v-model="pin"/>

        <kings-input type="number" label="Initial Deposit Amount" placeholder="$" v-model.number="depositAmount"/>

        <div class="buttons flex flex-col">
          <kings-button
              class="my-4"
              @click="createAccount" button-type="primary"> Create Account </kings-button>
          <kings-button @click="handleCancel"> Cancel </kings-button>
        </div>
      </div>
      <kings-error v-model:form-error="formError"/>


    </div>


</template>

<script>
import KingsInput from "./Base/KingsInput.vue";
import KingsButton from "./Base/KingsButton.vue";
import KingsError from "./Base/KingsError.vue";
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
    }
  },
  methods: {
    async handleCancel() {
      await this.$router.back();
    },
    async createAccount() {
      if (!this.firstName || !this.depositAmount || !this.pin ) {
        this.formError = 'Please fill out all the fields';
        return;
      }

    }
  }
}
</script>

<style scoped>

@tailwind base;
@tailwind components;
@tailwind utilities;

</style>