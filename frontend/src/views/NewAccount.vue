<template>
  <kings-atm-container>
    <div class="new-account-page grid gap-2 xl:gap-5 col-1 m-2">
      <div class="bg-green-400 p-4 rounded text-lg xl:text-2xl max-w-80">
        <p> Congratulations! You Successfully Created a New Account! </p>
      </div>

      <logo/>

      <p class="text-lg"> Your login card number:
        <strong class="text-2xl lg:text-4xl font-semibold text-yellow-100"> {{ id }} </strong>
      </p>

      <kings-button
          button-type="primary" @click="handleLogin"> Login </kings-button>

    </div>
  </kings-atm-container>
</template>

<script>
import KingsAtmContainer from "../components/Base/KingsAtmContainer.vue";
import Logo from "../components/Base/KingsLogo.vue";
import KingsButton from "../components/Base/KingsButton.vue";
export default {
  name: "NewAccount",
  components: {
    KingsButton,
    Logo,
    KingsAtmContainer,
  },
  props: ['id'],
  methods: {
    handleLogin() {
      this.$router.push("/login")
    }
  }

}
</script>

<style scoped>

</style>