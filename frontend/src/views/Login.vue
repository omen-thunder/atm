<template>
  <kings-atm-container>
    <div class="login-page flex-grow m-4">
        <logo/>
        <login-form/>
    </div>
  </kings-atm-container>
</template>

<script>

import LoginForm from "../components/LoginForm.vue";
import KingsAtmContainer from '../components/Base/KingsAtmContainer.vue';
import Logo from "../components/Base/KingsLogo.vue";

export default {
  name: "Home",
  components: {
    KingsAtmContainer,
    LoginForm,
    Logo
  },
};
</script>
