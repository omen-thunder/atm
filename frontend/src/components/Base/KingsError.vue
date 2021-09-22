<template>
  <div class="text-gray-50 rounded-md p-4 mx-auto transition-all"
       :class="formError? 'bg-red-500 p-4 my-6': 'bg-transparent my-0'">
    <span v-text="formError" v-if="formError"></span>
  </div>
</template>

<script>
export default {
  name: "KingsError",
  props: {
    formError: String
  },
  methods: {
    async clearFormError(delay) {
      await new Promise(r => setTimeout(r, delay));
      this.$emit("update:formError", '');
    }
  },
  watch: {
    formError(value) {
      if (value) {
        this.clearFormError(5000);
      }
    }
  },
}
</script>

<style scoped>

</style>