<template>

  <button @click="handleCancel">  Cancel </button>
  <button>  Make Withdrawal </button>
  <button>  Check Balance </button>
  <button>  Make Deposit </button>


</template>

<script>

export default {
  name: "HomeOptions",
  methods: {
    async handleCancel() {
      await this.$store.dispatch('logoutUser');
    },
    handleWithdraw() {

    },
    handleCheckBalance() {

    },
    handleMakeDeposit() {

    }
  }
}
</script>

<style scoped>

</style>