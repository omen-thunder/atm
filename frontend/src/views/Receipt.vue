<template>
  <kings-atm-container>
    <receipt-details/>
  </kings-atm-container>
</template>

<script>
import KingsAtmContainer from "../components/Base/KingsAtmContainer.vue";
import ReceiptDetails from "../components/ReceiptDetails.vue";
export default {
  name: "Receipt",
  components: {
    ReceiptDetails,
    KingsAtmContainer
  }
}
</script>

<style scoped>

</style>