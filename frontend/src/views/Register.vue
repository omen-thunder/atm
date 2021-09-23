<template>
  <kings-atm-container>
    <div class="register-page">
      <logo/>
      <register-form/>
    </div>
  </kings-atm-container>

</template>

<script>
import RegisterForm from "../components/RegisterForm.vue";
import KingsAtmContainer from "../components/Base/KingsAtmContainer.vue";
import Logo from "../components/Base/KingsLogo.vue";

export default {
  name: "Register",
  components: {
    KingsAtmContainer,
    RegisterForm,
    Logo
  },
}
</script>

<style scoped>

</style>