import AnonymousForm from './anonymous-form.js';
import UserForm from './user-form.js';

const anonymousApp = Vue.createApp({});
anonymousApp.component('anonymous-form', AnonymousForm);
anonymousApp.mount('#anonymousApp');

const userApp = Vue.createApp({});
userApp.component('user-form', UserForm);
userApp.mount('#userApp');