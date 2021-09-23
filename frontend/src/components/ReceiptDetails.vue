<template>

  <div v-if="receiptObj">

    <div class="text-lg grid grid-cols-1 gap-5 ">
      <p class="text-xl lg:text-2xl xl:text-2xl font-semibold text-yellow-100">Transaction Details</p>

      <div class="flex space-x-4">
        <p> Transaction Type </p>
        <p> {{ receiptObj.type }} </p>
      </div>
      <div class="flex space-x-5">
        <p> Transaction Date </p>
        <p> {{ formattedDate }} </p>
      </div>
      <div class="flex space-x-5">
        <p  class="text-left"> Transaction Amount </p>
        <p class="text-right"> ${{ receiptObj.amount/100 }} </p>
      </div>
      <div class="flex space-x-5">
        <p> Account Balance </p>
        <p> ${{ receiptObj.balance/100 }} </p>
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