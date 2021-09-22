<template>
  <button
      :class="buttonClass"
      v-bind="$attrs">
    <slot></slot>
  </button>


</template>

<script>
export default {
  name: "KingsButton",
  props: {
    buttonType: {
      type: String,
      default: ""
    }
  },
  computed: {
    buttonClass() {
      if (this.buttonType === "primary") {
        return "p-3 xl:p-4 text-lg xl:text-2xl text-white rounded bg-blue-400 active:bg-blue-500 hover:bg-blue-300"
      }
      else {
        return "p-3 xl:p-4 text-lg xl:text-2xl text-white rounded border-2 border-gray-100 hover:bg-gray-300"
      }
    }
  }

}
</script>

<style scoped>

</style>