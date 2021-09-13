import {createRouter, createWebHistory} from "vue-router";
import Login from "../views/Login.vue";
import NotFound from "@/views/NotFound";
import Home from "@/views/Home";
import Admin from "@/views/Admin";
import store from '@/store';

const routes = [
    {
        path: "/",
        name: "login",
        redirect: "/login",
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

    {
        path: "/home",
        name: "Home",
        component: Home,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/admin",
        name: "Admin",
        component: Admin,
        meta: {
            requiresAdmin: true
        }
    },
    {
        path: "/:pathMatch(.*)*",
        component: NotFound

    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

router.beforeEach((to, from, next) => {

    console.log("[Router Before] User logged in: "
        + store.getters.isLoggedIn +
        ", Is Admin: " + store.getters.isAdmin
    )

    console.log("[Router Before] TO: ", to)
    console.log("[Router Before] FROM: ", from)


    if (to.matched.some(record => record.meta.requiresAuth)) {

        if (!store.getters.isLoggedIn) {
            next({
                path: '/login',
                params: { nextUrl: to.fullPath }
            })
        }
        else {
            next();
        }
    }
    else if (to.matched.some(record => record.meta.requiresAdmin)) {
        if (!store.getters.isAdmin) {
            next({
                path: '/login',
                params: { nextUrl: to.fullPath }
            })
        }
        else {
            next();
        }
    }
    else {
        next()
    }
})

router.afterEach(async (to) => {
    if (to.path === "/logout") {
        await store.dispatch('logoutUser');
    }
})


export default router;
