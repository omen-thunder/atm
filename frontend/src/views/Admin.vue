<template>

  <kings-atm-container>
    <div class="m-2 md:m-8 h-full flex flex-col w-full">

      <h1 class="text-4xl my-4 md:mt-8">Admin Console</h1>
      <h2 class="mb-8">Authorised Personnel Only</h2>

      <div class="flex-grow">
        <div>

          <div class="grid grid-cols-1 md:grid-cols-2 gap-4 md:gap-8">

            <div class="col-span-2">
              <p class="text-xl text-gray-300 pb-2">ATM Cash Balance:</p>
              <p class="text-xl">$ <span>{{ cashBalance }}</span></p>
            </div>

            <div class="col-span-2">
              <p class="text-md md:text-xl">Insert more funds into the machine</p>
            </div>

            <div class="col-span-2 p-2 border-2 border-gray-700">
              <div class="grid grid-cols-1 md:grid-cols-7 gap-4">

                <div class="flex md:flex-col items-end justify-around md:justify-center">
                  <span>[ Coins ]</span>
                  <span>Available</span>
                  <span class="text-left">Add</span>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-center">
                  <span class="w-full text-lg text-yellow-100">$.5</span>
                  <span class="w-full">{{ hasCash.num5c }}</span>
                  <div class="w-full">
                    <input type="text" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num5c">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-center">
                  <span class="w-full text-lg text-yellow-100">$.10</span>
                  <span class="w-full">{{ hasCash.num10c }}</span>
                  <div class="w-full">
                    <input type="text" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num10c">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-center">
                  <span class="w-full text-lg text-yellow-100">$.20</span>
                  <span class="w-full">{{ hasCash.num20c }}</span>
                  <div class="w-full">
                    <input type="text" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num20c">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-center">
                  <span class="w-full text-lg text-yellow-100">$.50</span>
                  <span class="w-full">{{ hasCash.num50c }}</span>
                  <div class="w-full">
                    <input type="text" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num50c">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-center">
                  <span class="w-full text-lg text-yellow-100">$1</span>
                  <span class="w-full">{{ hasCash.num1 }}</span>
                  <div class="w-full">
                    <input type="text" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num1">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-center">
                  <span class="w-full text-lg text-yellow-100">$2</span>
                  <span class="w-full">{{ hasCash.num2 }}</span>
                  <div class="w-full">
                    <input type="text" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num2">
                  </div>
                </div>

              </div>  <!-- end of grid -->
            </div>  <!-- end of coin grid col -->

            <div class="col-span-2 p-2 border-2 border-gray-700">
              <div class="grid grid-cols-1 md:grid-cols-6 gap-4">

                <div class="flex md:flex-col items-end justify-around md:justify-center">
                  <span>[ Notes ]</span>
                  <span>Available</span>
                  <span class="text-left">Add</span>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-center">
                  <span class="w-full text-lg text-yellow-200">$5</span>
                  <span class="w-full">{{ hasCash.num5 }}</span>
                  <div class="w-full">
                    <input type="text" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num5">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-center">
                  <span class="w-full text-lg text-yellow-200">$10</span>
                  <span class="w-full">{{ hasCash.num10 }}</span>
                  <div class="w-full">
                    <input type="text" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num10">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-center">
                  <span class="w-full text-lg text-yellow-200">$20</span>
                  <span class="w-full">{{ hasCash.num20 }}</span>
                  <div class="w-full">
                    <input type="text" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num20">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-center">
                  <span class="w-full text-lg text-yellow-200">$50</span>
                  <span class="w-full">{{ hasCash.num50 }}</span>
                  <div class="w-full">
                    <input type="text" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num50">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-center">
                  <span class="w-full text-lg text-yellow-200">$100</span>
                  <span class="w-full">{{ hasCash.num100 }}</span>
                  <div class="w-full">
                    <input type="text" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num100">
                  </div>
                </div>

              </div>  <!-- end of grid -->
            </div>  <!-- end of notes grid col -->

            <div class="col-span-2">
              <button class="px-4 py-1 bg-blue-500 rounded-lg" @click="handleAddNotes">Confirm Deposit Cash</button>
            </div>

          </div>


          <div class="grid grid-cols-2 gap-4">

          </div>

        </div>
      </div>

      <div
        class="border-1-4 border-blue-500 text-yellow-400 p-4"
        v-if="formSubmitResult.message"
        role="alert">
        <p> {{ formSubmitResult.message }} </p>
      </div>

      <div>
        <button class="px-4 py-2 bg-yellow-600 rounded-lg" @click="handleLogout">Logout</button>
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
      addCash: {
        num5c: 0,
        num10c: 0,
        num20c: 0,
        num50c: 0,
        num1: 0,
        num2: 0,
        num5: 0,
        num10: 0,
        num20: 0,
        num50: 0,
        num100: 0,
      },
      hasCash: {
        num5c: 0,
        num10c: 0,
        num20c: 0,
        num50c: 0,
        num1: 0,
        num2: 0,
        num5: 0,
        num10: 0,
        num20: 0,
        num50: 0,
        num100: 0,
      },
      formSubmitResult: {
        success: null,
        message: null
      }
    }
  },
  component: {
    KingsAtmContainer,
  },

  /**
   * Actions to be performed when the component is mounted.
   */
  async mounted() {
    await this.fetchCashStore();
  },

  methods: {
    async handleLogout() {
      await this.$store.dispatch('logoutUser');
    },

    async handleAddNotes() {
      const params = this.addCash;
      let res = await AXIOS.post(`/api/atm/deposit`, params);

      if (res.status === 200 && res.data.success) {
        this.resetAddNote();
        
        // Update global state and refresh view
        const cash = res.data.result;
        await this.$store.commit('updateCashStore', cash);
        this.hasCash = this.$store.state.cashStore;
        await this.fetchCashStoreBalance();

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
        message: null,
      };
    },

    async resetAddNote() {
      this.addCash = {
        num5c: 0,
        num10c: 0,
        num20c: 0,
        num50c: 0,
        num1: 0,
        num2: 0,
        num5: 0,
        num10: 0,
        num20: 0,
        num50: 0,
        num100: 0,
      };
    },

    /**
     * Fetch cash store from backend
     */
    async fetchCashStore() {
      const cashStore = await this.$store.dispatch('getCashStore');
      if (!cashStore){
        this.formSubmitResult.message = "Failed to fetch backend cash store"
      } else {
        this.hasCash = cashStore;
        console.log('Fetched backend cash store');
        await this.fetchCashStoreBalance();
      }
    },

    async fetchCashStoreBalance() {
      const balance = await this.$store.dispatch('getCashStoreBalance');
      if (balance === null){
        this.formSubmitResult.message = "Failed to fetch balance"
      } else {
        this.cashBalance = balance / 100;
        console.log('Fetched balance');
      }
    },


  },
  computed: {

  }
}
</script>

<style scoped>

</style>