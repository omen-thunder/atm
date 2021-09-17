<template>
    <div class="flex-grow flex flex-col items-center mt-12">
      <div id="login-form" class="grid gap-5 grid-cols-1 max-w-lg mx-auto">

        <span class="text-gray-400 text-2xl block">Account Number</span>
        <input type="text" placeholder="- - - -" class="p-4 block rounded-md text-center text-4xl"
               v-model="loginForm.accountNumber">


        <span class="text-gray-400 text-2xl  block">Account Pin</span>
        <input type="password" placeholder="- - - -" class="p-4 block rounded-md text-center text-3xl "
               v-model="loginForm.accountPin">

        <button class="p-4 my-6 text-3xl md:text-xl text-white rounded-md bg-blue-400 active:bg-blue-500 hover:bg-blue-300"
                @click="login()">Login
        </button>
      </div>


    </div>

  <div class="text-gray-50  text-uppercase rounded-md my-6 p-4 md:w-2/3 xl:w-1/2 mx-auto transition-all" :class="formError? 'bg-red-500': 'bg-transparent'">
    <span v-text="formError" v-if="formError"></span>
  </div>


</template>

<script>
export default {
  name: "LoginForm",
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
    login: async function () {
      if (this.maintenance) {
        this.formError = 'Sorry, our system is currently in maintenance. Please try again later.';
        this.clearFormError(5000);
      }

      let signInObj = {username: this.loginForm.accountNumber, password: this.loginForm.accountPin}

      let response = await this.$store.dispatch("loginUser", signInObj);

      if (response?.data.error) {
        this.formError = response.data.error;
      }
    },

    clearFormError: async function (delay) {
      await new Promise(r => setTimeout(r, delay));
      this.formError = '';
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