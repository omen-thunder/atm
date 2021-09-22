<template>

  <kings-atm-container>
    <div class="m-2 md:m-8 h-full flex flex-col">

      <h1 class="text-4xl my-4 md:m-8">Admin Console</h1>

      <div class="flex-grow">
        <div>

          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">

            <div>
              <p class="text-xl text-gray-300 pb-2">ATM Cash Balance:</p>
              <p class="text-xl">[Balance place holder]</p>
            </div>

            <div>
              <p class="text-md md:text-xl pb-2">Insert more funds into the machine</p>
              <span class="text-2xl">$</span>
              <input
                class="border rounded text-gray-700 m-2"
                type="number"
                v-model="depositAmount" />
              
              <button
                  @click="addFundsToMachine"
                  class="bg-blue-500 hover:bg-blue-700 font-bold py-2 px-4 rounded">
                Add funds
              </button>
            </div>

          </div>


          <div class="grid grid-cols-2 gap-4">

          </div>

        </div>
      </div>


      <div class="border-1-4 border-blue-500 text-blue-700 p-4"
          v-if="formSubmitResult.message"
          role="alert">
        <p> {{ formSubmitResult.message }} </p>
      </div>

    </div>
  </kings-atm-container>

</template>

<script>

import AXIOS from "../axios.js";
import KingsAtmContainer from '../components/Base/KingsAtmContainer.vue';

export default {
  components: { KingsAtmContainer },
  name: "Admin",
  data() {
    return {
      cashBalance: 0,
      depositAmount: '',
      formSubmitResult: {
        success: null,
        message: null
      }
    }
  },
  component: {
    KingsAtmContainer,
  },
  methods: {
    async addFundsToMachine() {
      let response = await AXIOS.post(`/api/atm/deposit/${this.depositAmount}`);

      if (response.status === 200 && response.data.success) {
        this.depositAmount = ''
        this.formSubmitResult.success = true
        this.formSubmitResult.message = "Successfully added funds to the machine"

        setTimeout(() => {
          this.resetSubmitMessage()
        }, 4000);
      }
    },

    async resetSubmitMessage() {
      this.formSubmitResult = {
        success: null,
        message: null
      }
    },

    async getCashStoreBalance() {
      this.cashBalance = await AXIOS.get('/api/atm/get-cashstore-balance');
    }
  },
  computed: {

  }
}
</script>

<style scoped>

@tailwind base;
@tailwind components;
@tailwind utilities;

</style>