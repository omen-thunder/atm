<template>
  <kings-atm-container>
    <div class="login-page">
        <logo/>
        <login-form/>
    </div>
  </kings-atm-container>
</template>

<script>
// @ is an alias to /src
import KingsAtmContainer from '../components/AtmLayout/KingsAtmContainer.vue';
import LoginForm from "@/components/LoginForm.vue";
import Logo from "@/components/Logo.vue";

export default {
  name: "Home",
  components: {
    KingsAtmContainer,
    LoginForm,
    Logo
  },
};
</script>
