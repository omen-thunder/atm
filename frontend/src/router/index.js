import {createRouter, createWebHistory} from "vue-router";
import Login from "../views/Login.vue";
import NotFound from "@/views/NotFound";
import axios from "axios";

const routes = [
    {
        path: "/",
        name: "login",
        redirect: "/login"
    },
    {
        path: "/login",
        name: "Login",
        component: Login,
    },
    {
        path: "/register",
        name: "Register",
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
            import(/* webpackChunkName: "about" */ "../views/Register.vue"),
    },
    // {
    //   path: "/logout"
    // },
    {
        path: "/:pathMatch(.*)*",
        component: NotFound

    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

router.afterEach((to) => {

    if (to.path === "/logout") {
        console.log("clearing token");

        axios.get("/api/logout")
            .then((res) => {
                if (res.status === 200) {
                    localStorage.setItem("token", "")
                    router.push('/login');
                }
            })
    }

})


export default router;
