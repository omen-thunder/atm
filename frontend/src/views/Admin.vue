<template>

  <kings-atm-container>
    <div class="m-2 md:m-8 h-full flex flex-col w-full">

      <p class="text-2xl lg:text-3xl xl:text-4xl font-semibold text-yellow-100">Admin Console</p>
      <h2 class="mb-6">Authorised Personnel Only</h2>

      <div class="flex-grow">
        <div>

          <div class="grid grid-cols-1 md:grid-cols-2 gap-4 md:gap-6">

            <div class="col-span-2">
              <p class="text-xl text-gray-300 pb-2">ATM Total Balance: <strong class="ml-2 text-2xl text-green-400">${{ cashBalance }}</strong></p>
            </div>

            <div class="col-span-2">
              <p class="col-span-2 text-md md:text-xl">Insert more funds into the machine</p>
            </div>

            <div class="col-span-2 p-2 rounded bg-white bg-opacity-5 py-4">
              <div class="grid grid-cols-1 md:grid-cols-7 gap-4">

                <div class="flex md:flex-col items-end item-center justify-around md:justify-between">
                  <strong class="bg-yellow-200 rounded text-gray-700 px-2">Coins</strong>
                  <span class="md:py-6">Available</span>
                  <span>Add</span>
                </div>

                <div class="flex md:flex-col items-center justify-between md:justify-between">
                  <span class="w-full text-lg text-yellow-200">5c</span>
                  <span class="w-full">{{ hasCash.num5c }}</span>
                  <div class="w-full">
                    <input type="number" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num5c">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-between">
                  <span class="w-full text-lg text-yellow-200">10c</span>
                  <span class="w-full">{{ hasCash.num10c }}</span>
                  <div class="w-full">
                    <input type="number" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num10c">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-between">
                  <span class="w-full text-lg text-yellow-200">20c</span>
                  <span class="w-full">{{ hasCash.num20c }}</span>
                  <div class="w-full">
                    <input type="number" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num20c">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-between">
                  <span class="w-full text-lg text-yellow-200">50c</span>
                  <span class="w-full">{{ hasCash.num50c }}</span>
                  <div class="w-full">
                    <input type="number" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num50c">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-between">
                  <span class="w-full text-lg text-yellow-200">$1</span>
                  <span class="w-full">{{ hasCash.num1 }}</span>
                  <div class="w-full">
                    <input type="number" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num1">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-between">
                  <span class="w-full text-lg text-yellow-200">$2</span>
                  <span class="w-full">{{ hasCash.num2 }}</span>
                  <div class="w-full">
                    <input type="number" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num2">
                  </div>
                </div>

              </div>  <!-- end of grid -->
            </div>  <!-- end of coin grid col -->

            <div class="col-span-2 p-2 rounded bg-white bg-opacity-5 py-4">
              <div class="grid grid-cols-1 md:grid-cols-6 gap-4">

                <div class="flex md:flex-col items-end justify-around md:justify-between">
                  <strong class="bg-green-400 rounded text-gray-700 px-2">Notes</strong>
                  <span class="md:py-6">Available</span>
                  <span class="text-left">Add</span>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-between">
                  <span class="w-full text-lg text-green-300">$5</span>
                  <span class="w-full">{{ hasCash.num5 }}</span>
                  <div class="w-full">
                    <input type="number" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num5">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-between">
                  <span class="w-full text-lg text-green-300">$10</span>
                  <span class="w-full">{{ hasCash.num10 }}</span>
                  <div class="w-full">
                    <input type="number" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num10">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-between">
                  <span class="w-full text-lg text-green-300">$20</span>
                  <span class="w-full">{{ hasCash.num20 }}</span>
                  <div class="w-full">
                    <input type="number" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num20">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-between">
                  <span class="w-full text-lg text-green-300">$50</span>
                  <span class="w-full">{{ hasCash.num50 }}</span>
                  <div class="w-full">
                    <input type="number" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num50">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-between">
                  <span class="w-full text-lg text-green-300">$100</span>
                  <span class="w-full">{{ hasCash.num100 }}</span>
                  <div class="w-full">
                    <input type="number" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addCash.num100">
                  </div>
                </div>

              </div>  <!-- end of grid -->
            </div>  <!-- end of notes grid col -->

            <div class="col-span-2 flex justify-between">
              <kings-button @click="handleLogout">Logout</kings-button>
              <kings-button button-type="primary" @click="handleAddNotes" class="ml-2">Deposit ${{getAmountToBeDeposited}}</kings-button>
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

    </div>
  </kings-atm-container>

</template>

<script>

import AXIOS from "../axios.js";
import KingsAtmContainer from '../components/Base/KingsAtmContainer.vue';
import KingsButton from "../components/Base/KingsButton.vue";

export default {
  components: { KingsAtmContainer, KingsButton },
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
    getAmountToBeDeposited() {
      return (
          this.addCash.num5c * 0.05
          + this.addCash.num10c * 0.1
          + this.addCash.num20c * 0.2
          + this.addCash.num50c * 0.5
          + this.addCash.num1 * 1
          + this.addCash.num2 * 2
          + this.addCash.num5 * 5
          + this.addCash.num10 * 10
          + this.addCash.num20 * 20
          + this.addCash.num50 * 50
          + this.addCash.num100 * 100).toFixed(2)
    }

  }
}
</script>

<style scoped>

</style>