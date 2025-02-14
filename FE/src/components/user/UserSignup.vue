<script setup>
import { ref } from "vue"
import { useRouter } from "vue-router"
import {useToast} from "vue-toastification";

const email = ref("")
const name = ref("")
const password = ref("")
const phone1 = ref('010')
const phone2 = ref('')
const phone3 = ref('')
const emailAvailable = ref(null)
const router = useRouter()
const toast = useToast();

const checkEmail = async () => {
  try {
    emailAvailable.value = null;
    const response = await fetch(`${import.meta.env.VITE_API_URL}/api/v1/users/email-available?email=${email.value}`)
    if (!response.ok) {
      toast.error("이미 가입된 이메일입니다.");
      return;
    }
    emailAvailable.value = true
  } catch (error) {
    console.error('이메일 중복 확인 오류:', error);
    toast.error('이메일 중복 확인에 실패했습니다. 다시 시도해주세요.');
  }
}

const handlePhoneInput = (e, part, maxLength) => {
  const input = e.target.value.replace(/\D/g, '')
  
  if (part === 1) {
    phone1.value = input.slice(0, maxLength)
    if (input.length >= maxLength) {
      document.getElementById('phone2').focus()
    }
  } else if (part === 2) {
    phone2.value = input.slice(0, maxLength)
    if (input.length >= maxLength) {
      document.getElementById('phone3').focus()
    }
  } else {
    phone3.value = input.slice(0, maxLength)
  }
}

const handleSubmit = async (e) => {
  e.preventDefault();
  if (emailAvailable.value === null) {
    toast.error("이메일 중복 확인을 해주세요.");
    return;
  }
  if (emailAvailable.value === false) {
    toast.error("이미 가입된 이메일입니다. 다른 이메일로 진행해주세요.");
    return;
  }
  if (phone1.value.length < 3 || phone2.value.length < 4 || phone3.value.length < 4) {
    toast.error("휴대폰 번호를 입력해주세요.");
    return;
  }
  const fullPhone = `${phone1.value}-${phone2.value}-${phone3.value}`
  try {
    await fetch(`${import.meta.env.VITE_API_URL}/api/v1/users/signup`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        email: email.value,
        password: password.value,
        username: name.value,
        phoneNumber: fullPhone,
      }),
    });
    toast.success("회원가입이 완료되었습니다. 로그인해주세요.");
    await router.push('/signin');
  } catch (error) {
    console.error('로그인 오류:', error);
    toast.error('회원가입에 실패했습니다. 다시 시도해주세요.');
  }
}
</script>

<template>
  <div class="min-h-screen bg-gray-100 flex flex-col justify-center py-12 sm:px-6 lg:px-8">
    <div class="sm:mx-auto sm:w-full sm:max-w-md">
      <h2 class="mt-6 text-center text-3xl font-extrabold text-gray-900">회원가입</h2>
    </div>

    <div class="mt-8 sm:mx-auto sm:w-full sm:max-w-md">
      <div class="bg-white py-8 px-4 shadow sm:rounded-lg sm:px-10">
        <form @submit="handleSubmit" class="space-y-6">
          <div>
            <label for="email" class="block text-sm font-medium text-gray-700">이메일</label>
            <div class="mt-1 flex rounded-md shadow-sm">
              <input
                  type="email"
                  id="email"
                  v-model="email"
                  required
                  class="flex-1 min-w-0 block w-full px-3 py-2 rounded-none rounded-l-md border border-gray-300 focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
              />
              <button
                  type="button"
                  @click="checkEmail"
                  class="inline-flex items-center px-3 py-2 border border-l-0 border-gray-300 rounded-r-md bg-gray-50 text-gray-500 text-sm hover:bg-gray-100 focus:outline-none focus:ring-1 focus:ring-indigo-500 focus:border-indigo-500"
              >
                중복확인
              </button>
            </div>
            <p v-if="emailAvailable !== null" class="mt-2 text-sm" :class="emailAvailable ? 'text-green-600' : 'text-red-600'">
              {{ emailAvailable ? "사용 가능한 이메일입니다." : "이미 사용 중인 이메일입니다." }}
            </p>
          </div>

          <div>
            <label for="name" class="block text-sm font-medium text-gray-700">이름</label>
            <input
                type="text"
                id="name"
                v-model="name"
                required
                class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
            />
          </div>

          <div>
            <label for="password" class="block text-sm font-medium text-gray-700">비밀번호</label>
            <input
                type="password"
                id="password"
                v-model="password"
                required
                class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
            />
          </div>

          <div class="input-group">
            <label class="input-label">휴대폰 번호</label>
            <div class="phone-input-group">
              <input
                type="text"
                id="phone1"
                v-model="phone1"
                @input="(e) => handlePhoneInput(e, 1, 3)"
                maxlength="3"
                class="phone-input"
                placeholder="010"
              >
              <span class="phone-separator">-</span>
              <input
                type="text"
                id="phone2"
                v-model="phone2"
                @input="(e) => handlePhoneInput(e, 2, 4)"
                maxlength="4"
                class="phone-input"
                placeholder="0000"
              >
              <span class="phone-separator">-</span>
              <input
                type="text"
                id="phone3"
                v-model="phone3"
                @input="(e) => handlePhoneInput(e, 3, 4)"
                maxlength="4"
                class="phone-input"
                placeholder="0000"
              >
            </div>
          </div>

          <div>
            <button
                type="submit"
                class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
            >
              회원가입
            </button>
          </div>
        </form>

        <div class="mt-6">
          <div class="relative">
            <div class="absolute inset-0 flex items-center">
              <div class="w-full border-t border-gray-300"></div>
            </div>
            <div class="relative flex justify-center text-sm">
              <span class="px-2 bg-white text-gray-500">이미 계정이 있으신가요?</span>
            </div>
          </div>

          <div class="mt-6">
            <router-link
                to="/signin"
                class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-indigo-600 bg-white hover:bg-gray-50"
            >
              로그인
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.phone-input-group {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 1rem;
}

.phone-input {
  width: 80px;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
  text-align: center;
}

.phone-separator {
  color: #666;
  font-weight: bold;
}

.phone-input::-webkit-inner-spin-button,
.phone-input::-webkit-outer-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

.input-group {
  margin-bottom: 1rem;
}

.input-label {
  display: block;
  margin-bottom: 0.5rem;
  font-size: 14px;
  font-weight: 400;
  color: #333;
}
</style>
