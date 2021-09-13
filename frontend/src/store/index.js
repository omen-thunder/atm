import { createStore } from "vuex";
import AXIOS from "@/axios";
import router from '@/router'
import {nextTick} from "vue";

export default createStore({
  state() {
    return {
      isInitialised: false,
      isLoggedIn: false,
      isAdmin: false
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
    }

  },
  actions: {
    async loginUser({state, commit}, userDetailsObj) {
      let auth;

      if (userDetailsObj) {
        const {username, password} = userDetailsObj;
        const encodedBase64Token = Buffer.from(`${username}:${password}`).toString('base64');
        auth = `Basic ${encodedBase64Token}`
        localStorage.setItem("authtoken", "");
      }
      else {
        auth = localStorage.getItem("token");
      }

      try {

        let response = await AXIOS.get("/api/account/details", {
          headers: {
            Authorization: auth
          }
        })

        if (response.status === 200 && response.data.success) {

          localStorage.setItem("authtoken", auth)

          commit('setLoggedIn', true)

          let account = response.data.result

          commit('setAdmin', account.role === "ROLE_ADMIN");

          console.log("Attempting to push to home", router)

          if (state.isAdmin) {
            await router.replace({name: 'Admin'})
          }
          else {
            await router.replace({name: 'Home'})
          }
        }
      }
      catch (e) {
        console.log(e);
        localStorage.setItem("authtoken", "")
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
      await context.dispatch('loginUser')
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
