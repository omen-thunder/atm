<template>

  <button
      :class="buttonClass"
      class="flex align-center justify-center"
      v-bind="$attrs">
    <slot></slot>

    <progress-spinner v-if="loading" class="w-5 h-5 ml-1 my-auto" />
  </button>


</template>

<script>
import ProgressSpinner from "./ProgressSpinner.vue";
export default {
  name: "KingsButton",
  components: {ProgressSpinner},
  props: {
    buttonType: {
      type: String,
      default: ""
    },
    loading: {
      type: Boolean,
      default: false
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