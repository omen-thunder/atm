<template>
 <p> You're not authenticated or this page does not exist. Redirecting back to home...</p>
</template>

<script>
export default {
  name: "NotFound",
  mounted() {
    setTimeout(() => {
      this.$router.push("/")
    }, 4000)
  }
}
</script>

<style scoped>

</style>