<template>
  <body class="login-page">
    <div class="login-container" role="img" aria-label="Field of Wildflowers in Multiple Colors">
      <div class="a-section">
        <div class="a-box">
          <div id="login" class="text-center">
            <form class="form-signin" @submit.prevent="login">
              <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
              <div
                class="alert alert-danger"
                role="alert"
                v-if="invalidCredentials"
              >
                Invalid username and password!
              </div>
              <div
                class="alert alert-success"
                role="alert"
                v-if="this.$route.query.registration"
              >
                Thank you for registering, please sign in.
              </div>
              <label for="username" class="sr-only">Username</label>
              <input
                type="text"
                id="username"
                class="form-control"
                placeholder="Username"
                v-model="user.username"
                required
                autofocus
              />
              <label for="password" class="sr-only">Password</label>
              <input
                type="password"
                id="password"
                class="form-control"
                placeholder="Password"
                v-model="user.password"
                required
              />
              <div class="register">
                <router-link :to="{ name: 'register' }"
                  >Need an account?</router-link
                >
              </div>
              <div class="button">
                <button type="submit">Sign in</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </body>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
body {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  -webkit-text-size-adjust: 100%;
}

.a-section {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  -webkit-text-size-adjust: 100%;
}

.login-container {
  /* height: calc(100vh - 212px); */
  height: 100vh;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  display: block;
  box-sizing: border-box;
  font-size: 17px;
  line-height: 30px;
  background-image: linear-gradient(
      rgba(300, 300, 300, 0.5),
      rgba(300, 300, 300, 0.5)
    ),
    url(../img/wildflowers.jpg);
  background-size: cover;
  background-position: center;
}

.a-box {
  grid-area: auto;
  display: inline;
  border: 10px;
  height: 200px;
  width: 350px;
  padding: 20px;
  text-align: center;
  margin: -75px 700px;
  background-color: rgba(242, 242, 242, 0.8);
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.5);
  border-radius: 10px;
  position: absolute;
  overflow: hidden;
  transform: translateY(100%);
  top: 25%;
}

form {
  display: contents;
}
.form-control {
  font-size: medium;
}

.text-center {
  position: center;
  display: grid;
  margin: 50px;
  padding: 0%;
  transform: translateY(-25%);
  font-size: large;
  text-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
}

.register {
  font-size: larger;
  transform: translateY(25%);
}
.button {
  font-size: medium;
  transform: translateY(50%);
}
button{
  background-color: #4a7c59;
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  font-weight: bold;
  text-transform: uppercase;
  margin-top: inherit;
  display: block;
  margin-left: auto;
  margin-right: auto;
}

button:hover{
  background-color: #45a049;
}


@media (max-width: 768px) {
  .login-container {
    margin: 0 10px;
  }
}
</style>
