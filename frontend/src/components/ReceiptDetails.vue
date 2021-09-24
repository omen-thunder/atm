<template>

  <div v-if="receiptObj" class="flex-grow mx-4">

    <div class="text-lg grid grid-cols-1 gap-5 max-w-lg mx-auto">


      <h1 class="text-3xl lg:text-4xl font-semibold text-yellow-100">Transaction Details</h1>
      <div class="bg-green-400 p-3 rounded text-lg xl:text-2xl max-w-80">
        <p> Your transaction was successfully saved! </p>
      </div>

      <div class="flex space-x-4 justify-between">
        <p> Transaction Type </p>
        <p :class="typeBadgeClasses"> {{ receiptObj.type }} </p>
      </div>
      <div class="flex space-x-4 justify-between">
        <p> Transaction Number </p>
        <p> {{ receiptObj.id }} </p>
      </div>

      <div class="flex space-x-4 justify-between">
        <p> Transaction Date </p>
        <p> {{ formattedDate }} </p>
      </div>
      <div class="flex space-x-4 justify-between">
        <p  class="text-left"> Transaction Amount </p>
        <p class="text-right"> ${{ transactionAmount }} </p>
      </div>
      <div class="flex space-x-5 justify-between">
        <p> Account Balance </p>
        <p> ${{ accountBalance }} </p>
      </div>
      <kings-button button-type="primary" @click="handleHome"> Account Menu </kings-button>
      <kings-button @click="handleEndSession"> End Session </kings-button>
    </div>


  </div>

</template>

<script>
import AXIOS from "../axios.js";
import KingsButton from "./Base/KingsButton.vue";

export default {
  name: "ReceiptDetails",
  components: {KingsButton},
  data() {
    return {
      receiptObj: null,
    }
  },
  computed: {
    formattedDate() {
      console.log(this.receiptObj.dateTime)

      return new Date(`${this.receiptObj.dateTime}`).toDateString()
    },
    accountBalance() {
      return (this.receiptObj.balance/100).toFixed(2);
    },
    transactionAmount() {
      return (this.receiptObj.amount/100).toFixed(2);
    },
    typeBadgeClasses() {
      if (this.receiptObj.type === "WITHDRAW")
        return "py-0.2 px-1 bg-purple-500 rounded text-gray-900"
      else
        return "py-0.2 px-1 bg-green-500 rounded text-gray-900"
    }
  },
  props: {
    id: String
  },
  methods: {
    async handleHome() {
      await this.$router.push("/home")
    },
    async handleEndSession() {
        await this.$store.dispatch('logoutUser');
    }
  },

  async created() {
    try {
      console.log(this.$route.params)
      let response = await AXIOS.get(`/api/transaction/${this.id || this.$route.params.id}`)

      if (response.data?.success) {
        this.receiptObj = response.data.result;
      }
      else {
        console.log(response.data.error)
      }

    }
    catch (e) {
      console.log(e)
    }
  }
}
</script>

<style scoped>

</style>