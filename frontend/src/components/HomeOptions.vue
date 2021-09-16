<template>

  <div class="flex-grow flex flex-col items-center mt-12">

    <div id="home-options" class="grid gap-5 grid-cols-1 max-w-lg">

      <h1 class="text-3xl lg:text-4xl font-semibold text-yellow-100">What would you like to do today?</h1>

      <button
          @click="handleWithdraw"
          class="p-4 my-4 text-2xl text-white rounded-md bg-blue-400 active:bg-blue-500 hover:bg-blue-300">
        Make Withdrawal
      </button>

      <button class="p-4 my-4 text-2xl text-white rounded-md bg-blue-400 active:bg-blue-500 hover:bg-blue-300"> Check
        Balance
      </button>

      <button class="p-4 my-4 text-2xl text-white rounded-md bg-blue-400 active:bg-blue-500 hover:bg-blue-300"> Make
        Deposit
      </button>

      <button
          class="p-4 my-4 text-2xl text-white rounded border-2  border-gray-100 hover:bg-gray-300"
          @click="handleCancel">End Session
      </button>

    </div>

  </div>

</template>

<script>

export default {
  name: "HomeOptions",
  methods: {
    async handleCancel() {
      await this.$store.dispatch('logoutUser');
    },
    handleWithdraw() {
      this.$router.push('/withdraw')
    },
    handleCheckBalance() {

    },
    handleMakeDeposit() {

    }
  }
}
</script>

<style scoped>
@tailwind base;
@tailwind components;
@tailwind utilities;
</style>