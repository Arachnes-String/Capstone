import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import ClientPage from '../views/ClientPage.vue'
import PickupForm from '../views/PickupForm.vue'
import Test from '../views/Test.vue'
import Account from '../views/Account.vue'
import Driver from '../views/Driver.vue'
import MyPickups from '../views/MyPickups'
import Admin from '../views/Admin.vue'
import Prizes from '../views/Prizes.vue'
import About from '../views/About.vue'
import AdminAllClients from '../views/AdminAllClients'
import AdminAllDrivers from '../views/AdminAllDrivers'
import AdminUpdateClient from '../views/AdminUpdateClient'
import AdminEditDriver from '../views/AdminEditDriver'


Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */



const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
       {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/client",
      name: "client",
      component: ClientPage,
      meta: {
        requiresAuth: false
      }},
      {
        path: "/pickup",
        name: "pickup",
        component: PickupForm

      },
      {
        path: "/account",
        name: "account",
        component: Account

      },
      {
        path: '/admin',
        name: 'admin',
        component: Admin
      },
      {
        path: '/driver',
        name: 'driver',
        component: Driver

      },
      {
        path: '/mypickups',
        name: 'my pickups',
        component: MyPickups
      },

      {
        path: "/about",
        name: "about",
        component: About
      },

      {
        path: "/test",
        name: "test",
        component: Test
      },
      {
        path: "/prizes",
        name: "prizes",
        component: Prizes
      },
      {
        path: "/admin/clients",
        name: "admin all clients",
        component: AdminAllClients
      }, 
      {
        path: '/admin/drivers',
        name: "admin all drivers",
        component: AdminAllDrivers
      },
      {
        path: '/admin/clients/update/:id',
        name: "admin update client",
        component: AdminUpdateClient,
        
      },
      {
        path: '/admin/drivers/update/:id',
        name: 'admin update driver',
        component: AdminEditDriver
      }
  ],
});





router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});


export default router;
