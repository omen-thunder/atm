<template>

  <div class="flex flex-col text-center">
    <span v-if="label"
        class="my-2 text-gray-400 text-lg xl:text-2xl block"> {{ label }} </span>
    <input
        :type="type"
        :placeholder="placeholder"
        class="p-3 lg:p-4 block rounded-md text-xl xl:text-2xl text-gray-700 text-center"
        v-bind="$attrs"
        :value="modelValue"
        @input="updateInput">
  </div>

</template>

<script>
export default {
  name: "KingsInput",
  props: {
    modelValue: [String , Number],
    label: {
      type: String,
    },
    placeholder: {
      type: String,
    },
    type: {
      default: 'text'
    }
  },
  emits: ['update:modelValue'],
  methods: {
    updateInput(e) {
      this.$emit('update:modelValue', e.target.value)
    }
  }
}
</script>

<style scoped>

</style>