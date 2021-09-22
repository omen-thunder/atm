import { createStore } from "vuex";
import {nextTick} from "vue";

import AXIOS from "../axios.js";
import router from '../router/index.js'

export default createStore({
  state() {
    return {
      isInitialised: false,
      isLoggedIn: false,
      isAdmin: false,
      machineNumber: '0000'
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
    }

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
