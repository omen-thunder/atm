import { createStore } from "vuex";

export default createStore({
  state: {
    machineNumber: '0000'
  },
  mutations: {
    setMachineNumber(state, machineNumber){
      state.machineNumber = machineNumber;
    }
  },
  actions: {},
  modules: {},
});
