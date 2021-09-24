<template>
  <div class="kings-atm h-screen w-screen overflow-hidden flex flex-col justify-center p-2 md:p-4 xl:p-8 bg-gray-800">

    <div id="atm-container" class="flex flex-col h-full overflow-auto border-4 border-gray-700 rounded-2xl">

      <div id="mainContent" class="flex items-center justify-center flex-grow text-gray-50">
        <slot></slot>
      </div>

      <div class="text-gray-500 p-2 flex justify-between">
        <span> {{ machineNumber? 'ATM# ' + machineNumber : 'Powered by KingsATM'}} </span>
        <SystemTime />
      </div>

    </div>

  </div>
</template>

<script>
import SystemTime from '/src/components/Base/SystemTime.vue';

export default {
  name: "KingsAtmContainer",
  props: {
    machineNumber: String,
  },
  components: {
    SystemTime,
  }
};
</script>


<style scoped>

</style>
