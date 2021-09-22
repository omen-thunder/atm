<template>


  <div class="w-80">

    <div id="login-form" class="grid xl:gap-5 grid-cols-1 max-w-lg mx-auto" @keyup.enter="login()">

      <kings-input label="Account Number" placeholder="- - - -" v-model="loginForm.accountNumber"/>

      <kings-input label="Account Pin" placeholder="- - - -" v-model="loginForm.accountPin" type="password"/>

      <div class="buttons flex flex-col">
        <kings-button class="my-4" button-type="primary" @click="login">
          Login
        </kings-button>

        <kings-button @click="handleRegister">
          Register
        </kings-button>
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
  name: "LoginForm",
  components: {KingsError, KingsButton, KingsInput},
  data() {
    return {
      maintenance: false,
      formError: '',
      loginForm: {
        accountNumber: '',
        accountPin: ''
      },
    }
  },
  methods: {
    async login() {

      if (this.maintenance) {
        this.formError = 'Sorry, our system is currently in maintenance. Please try again later.';
        return
      }

      if (!this.loginForm.accountNumber || !this.loginForm.accountPin) {
        this.formError = 'Please enter a account number and pin.';
        return
      }

      let signInObj = {username: this.loginForm.accountNumber, password: this.loginForm.accountPin}

      let response = await this.$store.dispatch("loginUser", signInObj);

      if (response?.data.error) {
        this.formError = response.data.error;
      }
    },



    async handleRegister() {
      await this.$router.push("/register")
    }
  },

  async created() {
    await this.$store.dispatch('initialise');
  }
}
</script>

<style scoped>

@tailwind base;
@tailwind components;
@tailwind utilities;

</style>