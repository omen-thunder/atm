<template>

  <div class="container">
    <p class="text-xl">Insert more funds into the machine</p>

    <input
        class="border rounded"
        type="number"
        v-model="depositAmount"/>

    <button
        @click="addFundsToMachine"
        class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
      Add funds
    </button>

    <div
        v-if="formSubmitResult.message"
        role="alert"
        class="border-1-4 border-blue-500 text-blue-700 p-4">
      <p> {{ formSubmitResult.message }} </p>
    </div>
  </div>


</template>

<script>

import AXIOS from "@/axios";


export default {
  name: "Admin",
  data() {
    return {
      depositAmount: '',
      formSubmitResult: {
        success: null,
        message: null
      }
    }
  },
  methods: {
    async addFundsToMachine() {
      let response = await AXIOS.post(`/api/admin/deposit/${this.depositAmount}`);

      if (response.status === 200 && response.data.success) {
        this.depositAmount = ''
        this.formSubmitResult.success = true
        this.formSubmitResult.message = "Successfully added funds to the machine"

        setTimeout(() => {
          this.resetSubmitMessage()
        }, 4000);
      }
    },

    resetSubmitMessage() {
      this.formSubmitResult = {
        success: null,
        message: null
      }
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