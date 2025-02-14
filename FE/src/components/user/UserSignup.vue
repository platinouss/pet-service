<script setup>
import { ref } from "vue"
import { useRouter } from "vue-router"

const email = ref("")
const name = ref("")
const password = ref("")
const phone = ref("")
const emailAvailable = ref(null)
const router = useRouter()

const checkEmail = async () => {
  const isAvailable = Math.random() < 0.5
  emailAvailable.value = isAvailable
}

const handleSubmit = async (e) => {
  e.preventDefault()

  try {
    const response = await fetch('http://localhost:8080/api/login', { // 백엔드 API 주소로 변경
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        email: email.value,
        password: password.value,
      }),
      credentials: 'include', // 세션 기반 로그인 시 필요
    });

    if (!response.ok) {
      throw new Error('로그인 실패');
    }

    const data = await response.json();
    console.log('로그인 성공:', data);

    router.push('/');
  } catch (error) {
    console.error('로그인 오류:', error);
    alert('로그인에 실패했습니다. 다시 시도해주세요.');
  }

  console.log("회원가입:", { email: email.value, name: name.value, password: password.value, phone: phone.value })
  router.push("/")
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

          <div>
            <label for="phone" class="block text-sm font-medium text-gray-700">연락처</label>
            <input
                type="tel"
                id="phone"
                v-model="phone"
                required
                class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
            />
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
