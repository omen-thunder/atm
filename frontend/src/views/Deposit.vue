<template>

  <kings-atm-container>

    <div class="flex flex-col justify-center">
      <div>
        [Deposit Form Placeholder]
      </div>
      <kings-button :buttonType="'primary'" @click="handleCancel">Cancel</kings-button>
    </div>

  </kings-atm-container>

</template>

<script>
import KingsAtmContainer from "../components/Base/KingsAtmContainer.vue";
import KingsButton from '../components/Base/KingsButton.vue';

export default {
  name: "Deposit",
  components: {
    KingsAtmContainer,
    KingsButton,
  },

  methods: {
    async handleCancel() {
      this.$router.push('/home');
    },
  },
}
</script>

<style scoped>

</style>