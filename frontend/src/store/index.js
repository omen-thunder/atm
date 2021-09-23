import { createStore } from "vuex";
import { nextTick } from "vue";

import AXIOS from "../axios.js";
import router from '../router/index.js'

export default createStore({
  state() {
    return {
      isInitialised: false,
      isLoggedIn: false,
      isAdmin: false,
      machineNumber: "0000",
      cashStore: {
        num5c: 0,
        num10c: 0,
        num20c: 0,
        num50c: 0,
        num1: 0,
        num2: 0,
        num5: 0,
        num10: 0,
        num20: 0,
        num50: 0,
        num100: 0,
      },
    }
  },
  mutations: {

    setInitialised(state, payload) {
      state.isInitialised = payload;
    },

    setLoggedIn(state, payload) {
      state.isLoggedIn = payload;
    },

    setAdmin(state, payload) {
      state.isAdmin = payload
    },

    setMachineNumber(state, machineNumber){
      state.machineNumber = machineNumber;
    },

    updateCashStore(state, payload) {
      if (!payload){ return; }
      state.cashStore.num5c = payload.num5c || state.cashStore.num5c;
      state.cashStore.num10c = payload.num10c || state.cashStore.num10c;
      state.cashStore.num20c = payload.num20c || state.cashStore.num20c;
      state.cashStore.num50c = payload.num50c || state.cashStore.num50c;

      state.cashStore.num1 = payload.num1 || state.cashStore.num1;
      state.cashStore.num2 = payload.num2 || state.cashStore.num2;

      state.cashStore.num5 = payload.num5 || state.cashStore.num5;
      state.cashStore.num10 = payload.num10 || state.cashStore.num10;
      state.cashStore.num20 = payload.num20 || state.cashStore.num20;
      state.cashStore.num50 = payload.num50 || state.cashStore.num50;
      state.cashStore.num100 = payload.num100 || state.cashStore.num100;
    },

  },
  actions: {
    async checkAuth({state, commit}) {
      try {
        const auth = localStorage.getItem("token");

        let response = await AXIOS.get("/api/account/details", {
          headers: {Authorization: auth}
        })

        if (response.status === 200 && response.data.success) {

          localStorage.setItem("authtoken", auth)

          commit('setLoggedIn', true)

          let account = response.data.result

          commit('setAdmin', account.role === "ROLE_ADMIN");

          // state.isAdmin ? await router.push({name: 'Admin'}) : await router.push({name: 'Home'})

        } else if (response.status === 200 && response.data.error) {
          localStorage.setItem("authtoken", "")
        }

        return response;
      }
      catch (e) {
        localStorage.setItem("authtoken", "")
        return e;
      }
    },

    async loginUser({state, commit}, userDetailsObj) {
      let auth;

      try {

        let response = await AXIOS.post("/api/account/login" , {
          cardNumber: userDetailsObj.username,
          cardPin: userDetailsObj.password
        })

        if (response.status === 200 && response.data.success) {
          // Get response data
          let account = response.data.result

          // Store auth token
          localStorage.setItem("authtoken", auth)

          // Update store
          commit('setLoggedIn', true)
          commit('setAdmin', account.role === "ROLE_ADMIN");

          // Redirect
          state.isAdmin ? await router.replace({name: 'Admin'}) : await router.replace({name: 'Home'})

        } else if (response.status === 200 && response.data.error) {
          localStorage.setItem("authtoken", "")
        }

        return response;
      }
      catch (e) {
          localStorage.setItem("authtoken", "")
          return e;
      }
    },

    async logoutUser(context) {
      console.log("clearing token");

      const response = await AXIOS.get("/api/logout")

      if (response.status === 200) {
        localStorage.setItem("authtoken", "")
        context.commit("setLoggedIn", false)
        context.commit("setAdmin", false)

        await nextTick(() => {
          router.replace({name: "Login"})
        })

      }
    },

    async getCashStore(context) {
      let res = await AXIOS.get('/api/atm/get-cashstore');
      if (res.status == 200 && res.data.success){
        const cashStore = res.data.result;
        context.commit('updateCashStore', cashStore);
        return context.state.cashStore;
      } else {
        return null;
      }
    },

    async getCashStoreBalance(contect) {
      let res = await AXIOS.get('/api/atm/get-cashstore-balance');
      if (res.status == 200 && res.data.success){
        const balance = res.data.result;
        return balance;
      } else {
        return null;
      }
    },

    async getAccount(context) {
      const auth = localStorage.getItem("token");

      let res = await AXIOS.get("/api/account/details", {
        headers: {Authorization: auth}
      });

      if (res.status === 200 && res.data.success) {
        let data = res.data.result;
        return data;
      } else {
        console.log('Error getting account details.');
      }
    },

    async initialise(context) {
      if (context.state.isInitialised) return
      await context.dispatch('checkAuth')
      context.commit('setInitialised', true)
    }
  },
  getters: {

    isLoggedIn(state) {
      return state.isLoggedIn
    },

    isAdmin(state) {
      return state.isAdmin
    }

  },
  modules: {},
});
