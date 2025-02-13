<template>
  <div class="container mx-auto py-10">
    <div class="max-w-3xl mx-auto bg-white shadow rounded-lg">
      <div class="p-6 border-b border-gray-200">
        <h2 class="text-2xl font-bold">내 정보</h2>
      </div>
      <div class="p-6">
        <div class="flex flex-col md:flex-row gap-6">
          <div class="flex flex-col items-center space-y-4">
            <div class="w-32 h-32 relative group">
              <img :src="avatarUrl" alt="Profile picture" class="w-full h-full rounded-full object-cover" />
              <div class="absolute inset-0 bg-black bg-opacity-50 flex items-center justify-center opacity-0 group-hover:opacity-100 transition-opacity rounded-full">
                <Upload class="text-white" />
              </div>
            </div>
            <button class="px-4 py-2 border border-gray-300 rounded-md inline-flex items-center">
              <Upload class="mr-2 h-4 w-4" /> 프로필 사진 업로드
            </button>
          </div>
          <div class="flex-grow space-y-4">
            <div class="flex justify-between items-center">
              <h2 class="text-xl font-semibold">일반 사용자 | 펫시터</h2>
              <button @click="isEditing ? handleSave() : handleEdit()" class="p-2 rounded-full hover:bg-gray-100">
                <component :is="isEditing ? Check : Pencil" class="h-4 w-4" />
              </button>
            </div>
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

const avatarUrl = computed(() => '/placeholder.svg?height=128&width=128')

const handleEdit = () => {
  isEditing.value = true
}

const handleSave = () => {
  isEditing.value = false
}
</script>

<style scoped>
</style>

