<template>

  <div class="flex-grow flex flex-col items-center mt-12">

    <div id="withdraw-form" class="grid gap-5 grid-cols-1 max-w-lg">

      <h1 class="text-3xl lg:text-4xl font-semibold text-yellow-100">How much would you like to withdraw?</h1>

      <input
          v-model="amount"
          type="number"
          placeholder="$"
          class="p-4 block rounded-md text-left text-4xl">


      <div class="buttons flex flex-col">
        <button class="p-4 my-6 text-2xl text-white rounded bg-blue-400 active:bg-blue-500 hover:bg-blue-300"
                @click="withdraw">Withdraw
        </button>

        <button
            class="p-4 text-2xl text-white rounded border-2 border-gray-100 hover:bg-gray-300"
            @click="handleCancel">
          Cancel
        </button>
      </div>


    </div>

  </div>

</template>

<script>
export default {
  name: "WithdrawForm",
  data() {
    return {
      amount: null
    }
  },
  methods: {
    withdraw() {

    },
    handleCancel() {
      this.$router.push("/home");
    }
  }
}
</script>

<style scoped>

@tailwind base;
@tailwind components;
@tailwind utilities;

</style>