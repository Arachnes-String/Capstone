<template>
<form @submit.prevent="register">
  <div id="register">
      <h1>Create an Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="firstName">First Name: </label>
      <input
        type="text"
        id="firstName"
        placeholder="ENTER FIRST NAME HERE"
        v-model="user.firstName"
        required
        autofocus
      />
      <br/>
      <label for="lastName">Last Name: </label>
      <input
        type="text"
        id="lastName"
        placeholder="ENTER LAST NAME HERE"
        v-model="user.lastName"
        required
      />
      <br/>
      <label for="address">Address : </label>
      <input
        type="text"
        id="address"
        placeholder="ENTER ADDRESS HERE"
        v-model="user.address"
        required
      />
      <br/>
      <label for="phoneNumber">Phone Number (Optional): </label>
      <input
        type="tel"
        id="phoneNumber"
        placeholder="ENTER PHONE# HERE"
        v-model="user.phoneNumber"
      />
      <br/>
      <label for="email">E-Mail: </label>
      <input
        type="text"
        id="emial"
        placeholder="ENTER EMAIL HERE"
        v-model="user.email"
        required
      />
      <br/>
      <label for="password">Password: </label>
      <input
        type="password"
        id="password"
        placeholder="ENTER PASSWORD HERE"
        v-model="user.password"
        required
      />
      <br/>
      <label for="confirmPassword">Confirm Password: </label>
      <input
        type="password"
        id="confirmPassword"
        placeholder="CONFIRM PASSWORD HERE"
        v-model="user.confirmPassword"
        required
      />
      <br/>
  </div>
  <div>
    <h3> TERMS OF SERVICE : </h3>
    <p> Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
    <label for="checkBox">You Have Read And Agree with the Terms of Service: </label> <input type="checkbox" id="checkBox"/>
    </div>
  <div>
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
      <button type="submit">Create Account</button>
  </div>
    </form>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        firstName: '',
        lastName: '',
        address: '',
        phoneNumber: '',
        password: '',
        confirmPassword: '',
        role: 'user', 
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register()  {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.register {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
}

h1 {
  font-size: 24px;
  margin-top: 0;
}

.error-message {
  margin-bottom: 10px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

input[type='text'],
input[type='password'],
input[type='tel'],
textarea {
  width: 100%;
  padding: 8px 10px;
  font-size: 16px;
}

.checkbox-group {
  display: flex;
  align-items: center;
}

.checkbox-input {
  margin-right: 10px;
}

.checkbox-label {
  font-weight: normal;
}

.submit-button {
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
}

.terms-of-service {
  margin-top: 20px;
}

p {
  margin-bottom: 15px;
}
</style>
