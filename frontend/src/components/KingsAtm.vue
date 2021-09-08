<template>
  <div class="kings-atm h-screen w-screen overflow-hidden flex flex-col justify-center p-8">

    <div id="atm-container" class="flex flex-col h-full overflow-auto border-4 border-gray-700 rounded-2xl">
      <div class="flex flex-col justify-center my-4">
        <img class="h-48" src="../assets/crown.svg">
        <h1 class="text-4xl lg:text-6xl font-semibold text-yellow-100">XYZ Bank</h1>
        <h2 class="text-lg lg:text-xl text-gray-300 font-semibold">Your Business is Our Business</h2>
      </div>

      <div class="text-gray-50 rounded-2xl p-2 md:w-2/3 xl:w-1/2 mx-auto h-12 transition-all" :class="formError? 'bg-red-600': 'bg-transparent'">
        <span v-text="formError" v-if="formError"></span>
      </div>

      <div class="flex-grow flex flex-col items-center mt-12">
        <div id="login-form" class="grid grid-cols-1 max-w-md">
          <div class="block">
            <span class="text-gray-400 text-2xl inline">Account Number</span>
            <input type="text" placeholder="_ _ _ _ _" class="block w-64 text-center text-2xl" v-model="loginForm.accountNumber">
          </div>

          <button class="p-2 m-4 text-2xl rounded-3xl bg-blue-400 active:bg-gray-400 hover:bg-blue-300" @click="login()">Login</button>
        </div>
      </div>

    </div>
    
  </div>
</template>

<script>
export default {
  name: "KingsAtm",
  props: {
    machineNumber: String,
  },
  data() {
    return {
      maintenance: true,

      formError: '',
      loginForm: {
        accountNumber: '',
      },
    }
  },

  methods: {

    login: async function(){
      if (this.maintenance){
        this.formError = 'Sorry, our system is currently in maintenance. Please try again later.';
        this.clearFormError(3000);
      }
    },

    clearFormError: async function(delay){
      await new Promise( r => setTimeout(r, delay));
      this.formError = '';
    }

  }
};
</script>
