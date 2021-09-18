<template>
  <div class="kings-atm h-screen w-screen overflow-hidden flex flex-col justify-center p-8 bg-gray-800">

    <div id="atm-container" class="flex flex-col h-full overflow-auto border-4 border-gray-700 rounded-2xl">


      <div class="flex flex-col justify-center my-4">
        <img class="h-48" src="../../assets/crown.svg">
        <h1 class="text-4xl lg:text-6xl font-semibold text-yellow-100">XYZ Bank</h1>
        <h2 class="text-lg lg:text-xl text-gray-300 font-semibold">Your Business is Our Business</h2>
      </div>

      <slot></slot>

      <div class="text-gray-500 p-2 flex justify-between">
        <span> {{ machineNumber? 'ATM# ' + machineNumber : 'Powered by KingsATM'}} </span>
        <SystemTime />
      </div>

    </div>

  </div>
</template>

<script>
import SystemTime from '/src/components/AtmLayout/SystemTime.vue';

export default {
  name: "KingsAtm",
  props: {
    machineNumber: String,
  },
  components: {
    SystemTime,
  }
};
</script>


<style scoped>

@tailwind base;
@tailwind components;
@tailwind utilities;

</style>
