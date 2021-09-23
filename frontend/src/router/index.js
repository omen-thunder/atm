import {createRouter, createWebHistory} from "vue-router";
import store from '../store/index.js';

import Login from "../views/Login.vue";
import NotFound from "../views/NotFound.vue";
import Home from "../views/Home.vue";
import Admin from "../views/Admin.vue";
import Withdraw from "../views/Withdraw.vue";
import Register from "../views/Register.vue";
import NewAccount from "../views/NewAccount.vue";
import Receipt from "../views/Receipt.vue";
import CheckBalance from "../views/CheckBalance.vue";
import Deposit from "../views/Deposit.vue";
import LogoutSuccess from "../views/LogoutSuccess.vue";

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
        component:  Register,
    },
    {
        path: "/new-account/:id",
        name: "NewAccount",
        props: true,
        component:  NewAccount,
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
        path: "/withdraw",
        name: "Withdraw",
        component: Withdraw,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/deposit",
        name: "Deposit",
        component: Deposit,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/receipt/:id",
        name: "Receipt",
        props: true,
        component: Receipt,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: "/check-balance",
        name: "CheckBalance",
        props: true,
        component: CheckBalance,
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
        path: "/logout-success",
        name: "LogoutSuccess",
        component: LogoutSuccess,
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

router.beforeResolve((to, from, next) => {
    // If this isn't an initial page load.
    if (to.name) {
        // Start the route progress bar.
        NProgress.start()
    }
    next()
})


router.beforeEach(async (to, from, next) => {

    // Security policy
    if (to.matched.some(record => record.meta.requiresAuth)) {

        await store.dispatch("initialise");

        console.log("checking auth ", store.getters.isLoggedIn)

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
    NProgress.done();
})

export default router;
