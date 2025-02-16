<template>
  <div class="profile-container">
    <div class="profile-wrapper">
      <h2>내 프로필</h2>
      <div class="relative">
        <button 
          @click="isEditing ? handleSave() : handleEdit()" 
          class="p-2 rounded-full hover:bg-gray-100 absolute top-0 right-0"
        >
          <component :is="isEditing ? Check : Pencil" class="h-4 w-4" />
        </button>
      </div>
      <div class="p-6">
        <div class="flex flex-col md:flex-row gap-6">
          <div class="flex flex-col items-center space-y-2">
            <div class="w-32 h-32 relative group">
              <div class="profile-image-placeholder">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor">
                  <path fill-rule="evenodd" d="M18.685 19.097A9.723 9.723 0 0021.75 12c0-5.385-4.365-9.75-9.75-9.75S2.25 6.615 2.25 12a9.723 9.723 0 003.065 7.097A9.716 9.716 0 0012 21.75a9.716 9.716 0 006.685-2.653zm-12.54-1.285A7.486 7.486 0 0112 15a7.486 7.486 0 015.855 2.812A8.224 8.224 0 0112 20.25a8.224 8.224 0 01-5.855-2.438zM15.75 9a3.75 3.75 0 11-7.5 0 3.75 3.75 0 017.5 0z" clip-rule="evenodd" />
                </svg>
              </div>
              <div class="absolute inset-0 bg-black bg-opacity-50 flex items-center justify-center opacity-0 group-hover:opacity-100 transition-opacity rounded-full">
                <Upload class="text-white" />
              </div>
            </div>
            <button class="px-2 py-0.5 border border-gray-300 rounded-sm text-[10px] flex items-center h-5 min-h-0">
              <Upload class="mr-1 h-2.5 w-2.5" /> 프로필 사진 업로드
            </button>
          </div>
          <div class="flex-grow space-y-4">
            <div class="space-y-2">
              <div>
                <label for="name" class="block text-sm font-medium text-gray-700">Name</label>
                <input id="name" v-model="name" :readonly="!isEditing" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50" />
              </div>
              <div>
                <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
                <input id="email" v-model="email" :readonly="!isEditing" class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50" />
              </div>
              <div>
                <label for="phone" class="block text-sm font-medium text-gray-700">Phone</label>
                <div class="flex space-x-2">
                  <input v-for="(part, index) in phone" :key="index" v-model="phone[index]" :readonly="!isEditing" :maxlength="index === 0 ? 3 : 4" class="mt-1 block w-1/3 rounded-md border-gray-300 shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50" />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="p-6 border-t border-gray-200">
        <div>
          <h3 class="text-lg font-semibold mb-2">예약</h3>
          <div class="bg-gray-100 p-4 rounded-lg space-y-4">
            <div v-for="(reservation, index) in reservations" :key="index" class="bg-white p-3 rounded shadow-sm">
              <div class="flex justify-between items-center mb-2">
                <span class="font-semibold">{{ reservation.date }}</span>
                <span :class="['px-2 py-1 text-xs font-semibold rounded-full', statusColors[reservation.status]]">
                  {{ reservation.status }}
                </span>
              </div>
              <div class="text-sm text-gray-600">
                <p>시간: {{ reservation.time }}</p>
                <p>펫시터: {{ reservation.petsitterName }}</p>
                <p>지역: {{ reservation.location }}</p>
              </div>
            </div>
          </div>
        </div>
        <button class="mt-6 px-4 py-2 bg-red-600 text-white rounded-md hover:bg-red-700 inline-flex items-center">
          <LogOut class="mr-2 h-4 w-4" /> 회원 탈퇴
        </button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { Pencil, Upload, LogOut, Check } from 'lucide-vue-next'

interface Reservation {
  date: string
  time: string
  petsitterName: string
  location: string
  status: '진행 전' | '진행 중' | '진행 완료'
}

const isEditing = ref(false)
const name = ref('현종')
const email = ref('platinouss@gmail.com')
const phone = ref(['010', '1234', '5678'])

const reservations = ref<Reservation[]>([
  { date: "2023-06-15", time: "14:00-16:00", petsitterName: "홍길동", location: "서울 강남구", status: "진행 전" },
  { date: "2023-06-10", time: "13:00-15:00", petsitterName: "아무개", location: "서울 송파구", status: "진행 완료" },
])

const statusColors = {
  "진행 전": "bg-blue-500 text-white",
  "진행 중": "bg-yellow-500 text-white",
  "진행 완료": "bg-green-500 text-white",
}

const handleEdit = () => {
  isEditing.value = true
}

const handleSave = () => {
  isEditing.value = false
}
</script>

<style scoped>
.profile-container {
  min-height: 100vh;
  padding: 2rem;
  background-color: #f5f5f5;
  display: flex;
  justify-content: center;
  align-items: flex-start;
}

.profile-wrapper {
  background: white;
  border-radius: 16px;
  padding: 2.5rem;
  width: 100%;
  max-width: 600px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

h2 {
  font-size: 1.75rem;
  color: #333;
  margin-bottom: 2rem;
  text-align: center;
  font-weight: 600;
}

label {
  display: block;
  font-size: 0.9rem;
  color: #666;
  font-weight: 500;
  margin-bottom: 0.5rem;
}

input {
  width: 100%;
  padding: 0.75rem 1rem;
  border: 1.5px solid #e0e0e0;
  border-radius: 8px;
  font-size: 1rem;
  transition: all 0.3s ease;
}

input:focus {
  outline: none;
  border-color: #4f46e5;
  box-shadow: 0 0 0 3px rgba(79, 70, 229, 0.1);
}

input:disabled {
  background-color: #f3f4f6;
  color: #666;
  cursor: not-allowed;
}

button {
  flex: 1;
  padding: 0.75rem;
  border-radius: 8px;
  font-weight: 500;
  transition: all 0.3s ease;
  cursor: pointer;
}

@media (max-width: 640px) {
  .profile-container {
    padding: 1rem;
  }
  
  .profile-wrapper {
    padding: 1.5rem;
  }
}

.profile-image svg {
  width: 60%;
  height: 60%;
  color: #9ca3af;
}

input[type="file"] {
  display: none;
}
</style>

