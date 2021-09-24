<template>


    <div id="login-form" class="grid xl:gap-5 grid-cols-1 max-w-md mx-auto" @keyup.enter="login()">

      <kings-input label="Account Number" placeholder="- - - -" v-model="loginForm.accountNumber"/>

      <kings-input label="Account Pin" placeholder="- - - -" v-model="loginForm.accountPin" type="password"/>

      <div class="buttons flex flex-col">
        <kings-button class="my-4" button-type="primary" @click="login" :loading="isLoading"> Login </kings-button>
        <kings-button @click="handleRegister"> Register</kings-button>
      </div>

      <kings-error :form-error="formError"/>
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
      isLoading: false,
      loginForm: {
        accountNumber: '',
        accountPin: ''
      },
    }
  },
  methods: {
    async login() {

      this.isLoading = true;

      if (this.maintenance) {
        this.formError = 'Sorry, our system is currently in maintenance. Please try again later.';
        this.isLoading = false
        return
      }

      if (!this.loginForm.accountNumber || !this.loginForm.accountPin) {
        this.formError = 'Please enter a account number and pin.';
        this.isLoading = false
        return
      }

      let signInObj = {username: this.loginForm.accountNumber, password: this.loginForm.accountPin}

      let response = await this.$store.dispatch("loginUser", signInObj);

      if (response.data) {
        this.formError = response.data.error;
      }
      else {
        this.formError = response.message;
      }

      this.isLoading = false
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

</style>