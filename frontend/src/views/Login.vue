<template>
  <div class="login-page">
    <kings-atm>
      <login-form/>
    </kings-atm>
  </div>
</template>

<script>
import LoginForm from "../components/LoginForm.vue";
import KingsAtm from "../components/AtmLayout/KingsAtmContainer.vue";

export default {
  name: "Home",
  components: {
    KingsAtm,
    LoginForm,
  }
};
</script>
