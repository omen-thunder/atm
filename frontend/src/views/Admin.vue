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
              <p class="text-xl">$ <span>[Balance place holder]</span></p>
            </div>

            <div class="col-span-2">
              <p class="text-md md:text-xl">Insert more funds into the machine</p>
            </div>

            <div class="col-span-2 p-2 border-2 border-gray-700">
              <div class="grid grid-cols-1 md:grid-cols-7 gap-4">

                <div class="flex md:flex-col items-end justify-around md:justify-center">
                  <span>~</span>
                  <span>Available</span>
                  <span class="text-left">Add</span>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-center">
                  <span class="w-full text-lg text-yellow-200">$5</span>
                  <span class="w-full">{{ hasNote.n5 }}</span>
                  <div class="w-full">
                    <input type="text" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addNote.n5">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-center">
                  <span class="w-full text-lg text-yellow-200">$10</span>
                  <span class="w-full">{{ hasNote.n10 }}</span>
                  <div class="w-full">
                    <input type="text" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addNote.n10">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-center">
                  <span class="w-full text-lg text-yellow-200">$20</span>
                  <span class="w-full">{{ hasNote.n20 }}</span>
                  <div class="w-full">
                    <input type="text" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addNote.n20">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-center">
                  <span class="w-full text-lg text-yellow-200">$50</span>
                  <span class="w-full">{{ hasNote.n50 }}</span>
                  <div class="w-full">
                    <input type="text" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addNote.n50">
                  </div>
                </div>

                <div class="flex md:flex-col items-center justify-around md:justify-center">
                  <span class="w-full text-lg text-yellow-200">$100</span>
                  <span class="w-full">{{ hasNote.n100 }}</span>
                  <div class="w-full">
                    <input type="text" class="w-16 bg-transparent border-0 text-center bg-gray-700 rounded-lg" v-model="addNote.n100">
                  </div>
                </div>

                <div class="mt-4 self-end">
                  <button class="px-4 py-1 bg-blue-500 rounded-lg" @click="handleAddNotes">ADD</button>
                </div>

              </div>  <!-- end of grid -->

            </div>  <!-- end of col -->

          </div>


          <div class="grid grid-cols-2 gap-4">

          </div>

        </div>
      </div>

      <div
        class="border-1-4 border-blue-500 text-blue-700 p-4"
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
      addNote: {
        n5: 0,
        n10: 0,
        n20: 0,
        n50: 0,
        n100: 0,
      },
      hasNote: {
        n5: 0,
        n10: 0,
        n20: 0,
        n50: 0,
        n100: 0,
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
  methods: {
    async handleLogout() {
      await this.$store.dispatch('logoutUser');
    },

    // async addFundsToMachine() {
    //   let response = await AXIOS.post(`/api/atm/deposit/${this.depositAmount}`);

    //   if (response.status === 200 && response.data.success) {
    //     this.depositAmount = ''
    //     this.formSubmitResult.success = true
    //     this.formSubmitResult.message = "Successfully added funds to the machine"

    //     setTimeout(() => {
    //       this.resetSubmitMessage()
    //     }, 4000);
    //   }
    // },

    async handleAddNotes() {
      let params = this.addNote;
      let response = await AXIOS.post(`/api/atm/deposit/${params}`);

      if (response.status === 200 && response.data.success) {
        this.addNote = {
          n5: 0,
          n10: 0,
          n20: 0,
          n50: 0,
          n100: 0,
        }
        this.formSubmitResult.success = true
        this.formSubmitResult.message = "Successfully added funds to the machine"

        console.log(response.data);

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
    },
  },
  computed: {

  }
}
</script>

<style scoped>

</style>