<template>
	<div class="signup">
		<div class="logo" style="background-image: url(/img/logoAuth.svg);"></div>
		<h1>Регистрация</h1>

			<div class="input-group">
				<label for="login">
					<h2>ЛОГИН</h2>
				</label>
				<input type="text" placeholder="login" name="login" id="login" v-model="user.username">
				<div v-if="v$.user.username.$error" class="error">Введите минимум 4 символа</div>
				<div v-if="!usernameIsUnique" class="error">Пользователь с таким логином уже существует</div>
			</div>

			<div class="input-group">
				<label for="phone">
					<h2>НОМЕР ТЕЛЕФОНА</h2>
				</label>
				<div class="phone-confirmation-group">
					<vue-tel-input v-model="user.phone"></vue-tel-input>
					<div v-if="v$.user.phone.$error" class="error">Введите телефон в формате "(999) 999-99-99"</div>
					<div class="show_confirmation" v-if="showConfirmation">
						<input placeholder="Код" type="text" id="confirmationCode" v-model="confirmationCode">
						<button @click="confirmRegistration">Подтвердить</button>
					</div>
				</div>
			</div>




			<div class="input-group">
				<label for="password">
					<h2>ПАРОЛЬ</h2>
				</label>
				<input type="password" placeholder="password" name="password" id="password" v-model="user.password">
				<div v-if="v$.user.password.$error" class="error">Введите минимум 8 символа</div>
			</div>

			<div class="input-group">
				<label for="password">
					<h2>ПОДТВЕРДИТЕ ПАРОЛЬ</h2>
				</label>
				<input type="password" placeholder="сonfirm password" name="password" id="confirm_password"
					v-model="confirmPassword">
				<div v-if="v$.confirmPassword.$error" class="error">Пароли должны совпадать</div>
			</div>
			<input type="submit" @click="submitForm" value="ЗАРЕГЕСТРИРОВАТЬСЯ" />
			<p>Есть аккаунта? <a href="/login">Войти</a></p>
			<p
				style="font-size: calc(var(--index)*0.3); width: calc(var(--index)*10); text-align: center; margin-top: calc(var(--index)*0.2);">
				Нажимая "ЗАРЕГЕСТРИРОВАТЬСЯ" вы принимаете пользовательское соглашение и политику конфиденциальности</p>


	</div>
</template>
<style>
@import '@/assets/css/signup.css';
@import '@/assets/css/vue-tel-input.css';
</style>


<script>
import axios from 'axios';
import { required, minLength, maxLength, sameAs } from '@vuelidate/validators';
import { useVuelidate } from '@vuelidate/core';
import { VueTelInput } from 'vue-tel-input';
export default {
	components: { VueTelInput },
	setup() {
		return { v$: useVuelidate() }
	},

	data() {
		return {
			user: {
				username: '',
				password: '',
				phone: '',
			},
			confirmPassword: '',
			showConfirmation: false,
			usernameIsUnique: true,
			confirmationCode: '',
		}
	},
	validations() {
		return {
			user: {
				username: { required, minLength: minLength(4) },
				password: { required, minLength: minLength(4) },
				phone: { required, minLength: minLength(16), maxLength: maxLength(17) },
			},
			confirmPassword: { required, sameAsPassword: sameAs(this.user.password) }
		}
	}
	,
	methods: {
		async submitForm() {
			this.v$.$touch()
			if (this.v$.$invalid) {
				return
			}
			try {
				const response = await axios.post('http://localhost:8080/api/register', this.user);
				if (response.data === "A confirmation code has been sent to your phone number") {
					this.showConfirmation = true;
					this.usernameIsUnique = true;
					alert("Введите код из СМС")
				}
				else {
					this.usernameIsUnique = false;
				}
			} catch (error) {
				this.usernameIsUnique = false;
			}
		},
		async confirmRegistration() {
			const response = await axios.post('http://localhost:8080/api/confirm', { code: this.confirmationCode });

			if (response.data === "Account has been confirmed") {
				alert("Account has been confirmed")
				this.$router.push('/login');
			}
			else{
				alert("Error")
			}
		},
	}
}
</script>
