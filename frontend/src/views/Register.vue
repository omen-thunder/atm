<template>

  <form @submit.prevent="handleSubmit">
    <label> Account Starting Balance </label>
    <input type="text">

    <label> Pin Code </label>
    <input type="text">

    <button>Create Account</button>
  </form>

</template>

<script>
export default {
  name: "Register",
  methods: {
    handleSubmit() {

    }
  }
}
</script>

<style scoped>

</style>