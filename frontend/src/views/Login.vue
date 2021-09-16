<template>
  <div class="login-page">
    <kings-atm>
      <login-form/>
    </kings-atm>
  </div>
</template>

<script>
// @ is an alias to /src
import LoginForm from "@/components/LoginForm";
import KingsAtm from "@/components/AtmLayout/KingsAtmContainer";

export default {
  name: "Home",
  components: {
    KingsAtm,
    LoginForm,
  },
};
</script>
