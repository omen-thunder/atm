import { createStore } from "vuex";
import axios from "axios";
import router from "@/router";

export default createStore({
  state() {
    return {
      isLoggedIn: false,
      isAdmin: false
    }
  },
  mutations: {

    setLoggedIn(state) {
      state.isLoggedIn = true;
    }

  },
  actions: {
    async loginUser(context, userDetailsObj) {

      const {username, password} = userDetailsObj;

      const encodedBase64Token = Buffer.from(`${username}:${password}`).toString('base64');
      const auth = `Basic ${encodedBase64Token}`

      localStorage.setItem("token", "");

      let response = await axios.get("/api/account/details", {
        headers: {
          Authorization: auth
        }
      })

      if (response.status === 200 && response.data.success) {
        localStorage.setItem("token", auth)
        context.commit('setLoggedIn', true)
      }
    },

    async logoutUser(context) {
      console.log("clearing token");

      const response = await axios.get("/api/logout")

      if (response.status === 200) {
        localStorage.setItem("token", "")
        context.commit("setLoggedIn", false)
      }
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
