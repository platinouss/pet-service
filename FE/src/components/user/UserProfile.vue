<template>
  <div class="profile-container">
    <div class="profile-wrapper">
      <div class="relative">
        <h2>내 프로필</h2>
        <div v-if="isEditing" class="bg-indigo-50 border-l-4 border-indigo-500 p-4 mb-4">
          <div class="flex items-center">
            <div class="flex-shrink-0">
              <Pencil class="h-5 w-5 text-indigo-500" />
            </div>
            <div class="ml-3">
              <p class="text-sm text-indigo-700">
                프로필 편집 후 저장 버튼을 눌러주세요.
              </p>
            </div>
          </div>
        </div>
        <button 
          @click="isEditing ? handleSave() : handleEdit()" 
          :class="[
            'absolute top-0 right-0 flex items-center px-4 py-2 rounded-md transition-all',
            isEditing 
              ? 'bg-indigo-600 text-white hover:bg-indigo-700' 
              : 'bg-gray-100 text-gray-700 hover:bg-gray-200'
          ]"
        >
          <component :is="isEditing ? Check : Pencil" class="h-4 w-4 mr-2" />
          {{ isEditing ? '저장하기' : '편집하기' }}
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
                <label for="name" class="block text-sm font-medium text-gray-700">이름</label>
                <input 
                  id="name" 
                  v-model="username"
                  :readonly="!isEditing" 
                  :class="[
                    'mt-1 block w-full rounded-md shadow-sm focus:ring focus:ring-indigo-200 focus:ring-opacity-50',
                    isEditing 
                      ? 'border-indigo-300 bg-white focus:border-indigo-500' 
                      : 'border-transparent bg-gray-100 cursor-default'
                  ]"
                />
              </div>
              <div>
                <label for="email" class="block text-sm font-medium text-gray-700">이메일</label>
                <input 
                  id="email" 
                  v-model="email" 
                  :readonly="!isEditing" 
                  :class="[
                    'mt-1 block w-full rounded-md shadow-sm focus:ring focus:ring-indigo-200 focus:ring-opacity-50',
                    isEditing 
                      ? 'border-indigo-300 bg-white focus:border-indigo-500' 
                      : 'border-transparent bg-gray-100 cursor-default'
                  ]"
                />
              </div>
              <div>
                <label for="phoneNumber" class="block text-sm font-medium text-gray-700">휴대전화</label>
                <div class="flex space-x-2">
                  <input 
                    v-for="(part, index) in phoneNumber"
                    :key="index" 
                    v-model="phoneNumber[index]"
                    :readonly="!isEditing" 
                    :maxlength="index === 0 ? 3 : 4" 
                    :class="[
                      'mt-1 block w-1/3 rounded-md shadow-sm focus:ring focus:ring-indigo-200 focus:ring-opacity-50',
                      isEditing 
                        ? 'border-indigo-300 bg-white focus:border-indigo-500' 
                        : 'border-transparent bg-gray-100 cursor-default'
                    ]"
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="p-6 border-t border-gray-200">
        <div>
          <h3 class="text-lg font-semibold mb-2">예약 내역</h3>
          <div class="bg-gray-100 p-4 rounded-lg space-y-4">
            <div v-if="reservations.length === 0" class="text-center text-gray-500 py-4">
              예약 내역이 없습니다.
            </div>
            <div 
              v-for="(reservation, index) in reservations" 
              :key="index" 
              class="bg-white p-4 rounded-lg shadow-sm"
            >
              <div class="flex justify-between items-center mb-2">
                <span class="font-medium">{{ formatDate(reservation.date) }}</span>
                <span class="text-sm text-gray-500">예약번호: {{ reservation.impUid || '-' }}</span>
              </div>
              <div class="text-sm text-gray-600">
                <p>시간: {{ formatTime(reservation.startTime) }} - {{ formatTime(reservation.endTime) }}</p>
              </div>
            </div>
          </div>
        </div>
        <button @click="showDeleteModal = true" class="mt-6 px-4 py-2 bg-red-600 text-white rounded-md hover:bg-red-700 inline-flex items-center">
          <LogOut class="mr-2 h-4 w-4" /> 회원 탈퇴
        </button>
      </div>
    </div>
    
    <Transition name="modal">
      <div v-if="showDeleteModal" class="fixed inset-0 z-50 overflow-y-auto" aria-labelledby="modal-title" role="dialog" aria-modal="true">
        <div class="flex items-end sm:items-center justify-center min-h-full p-4 text-center sm:p-0">
          <div class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" @click="showDeleteModal = false"></div>
          
          <div class="relative bg-white rounded-lg px-4 pt-5 pb-4 text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:max-w-lg sm:w-full sm:p-6">
            <div class="sm:flex sm:items-start">
              <div class="mx-auto flex-shrink-0 flex items-center justify-center h-12 w-12 rounded-full bg-red-100 sm:mx-0 sm:h-10 sm:w-10">
                <AlertTriangle class="h-6 w-6 text-red-600" />
              </div>
              <div class="mt-3 text-center sm:mt-0 sm:ml-4 sm:text-left">
                <h3 class="text-lg leading-6 font-medium text-gray-900" id="modal-title">회원 탈퇴</h3>
                <div class="mt-2">
                  <p class="text-sm text-gray-500">
                    정말 탈퇴하시겠습니까? 이 작업은 되돌릴 수 없으며, 모든 데이터가 영구적으로 삭제됩니다.
                  </p>
                </div>
              </div>
            </div>
            <div class="mt-5 sm:mt-4 sm:flex sm:flex-row-reverse">
              <button
                type="button"
                class="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-red-600 text-base font-medium text-white hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500 sm:ml-3 sm:w-auto sm:text-sm"
                @click="handleDeleteAccount"
              >
                탈퇴하기
              </button>
              <button
                type="button"
                class="mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:mt-0 sm:w-auto sm:text-sm"
                @click="showDeleteModal = false"
              >
                취소
              </button>
            </div>
          </div>
        </div>
      </div>
    </Transition>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import { Pencil, Upload, LogOut, Check, AlertTriangle } from 'lucide-vue-next'
import { useToast } from "vue-toastification";

const isEditing = ref(false);
const username = ref('');
const email = ref('');
const phoneNumber = ref(['', '', '']);
const toast = useToast();
const showDeleteModal = ref(false);
const reservations = ref([]);
const isLoading = ref(true);

onMounted(async () => {
  try {
    const userResponse = await fetch(`${import.meta.env.VITE_API_URL}/api/v1/users/me`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
      credentials: 'include'
    });
    const userData = await userResponse.json();
    username.value = userData.username;
    email.value = userData.email;
    phoneNumber.value = userData.phoneNumber.split('-');

    const reservationResponse = await fetch(`${import.meta.env.VITE_API_URL}/api/v1/pet-sitters/reservation`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
      credentials: 'include'
    });
    reservations.value = await reservationResponse.json();
    console.log('예약 데이터:', reservations.value);
  } catch (e) {
    console.error('에러:', e);
    toast.error('정보 조회에 실패했습니다.');
  } finally {
    isLoading.value = false;
  }
});

const handleEdit = () => {
  isEditing.value = true;
}

const handleSave = async () => {
  isEditing.value = false;
  try {
    await fetch(`${import.meta.env.VITE_API_URL}/api/v1/users/me`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        username: username.value,
        phoneNumber: phoneNumber.value.join('-'),
        email: email.value,
      }),
      credentials: 'include'
    });
    toast.success('프로필이 성공적으로 저장되었습니다.');
  } catch (e) {
    toast.error('내 정보 조회에 실패했습니다.');
  }
}

const handleDeleteAccount = async () => {
  try {
    const response = await fetch(`${import.meta.env.VITE_API_URL}/api/v1/users/me`, {
      method: 'DELETE',
      credentials: 'include'
    });
    if (response.ok) {
      toast.success('회원 탈퇴가 완료되었습니다.');
      window.location.href = '/';
    } else {
      throw new Error('회원 탈퇴 실패');
    }
  } catch (e) {
    toast.error('회원 탈퇴에 실패했습니다.');
  } finally {
    showDeleteModal.value = false;
  }
};

const formatDate = (date) => {
  if (!date) return '-';
  const [year, month, day] = date.split('-');
  return `${year}년 ${month}월 ${day}일`;
};

const formatTime = (time) => {
  if (!time) return '-';
  const [hours, minutes] = time.split(':');
  return `${hours}:${minutes}`;
};
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
  position: relative;
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
  font-size: 1rem;
  transition: all 0.3s ease;
}

input:focus {
  outline: none;
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

input:not([readonly]) {
  border: 2px solid #818cf8;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(99, 102, 241, 0.1);
}

input:not([readonly]):focus {
  border-color: #4f46e5;
  box-shadow: 0 0 0 3px rgba(99, 102, 241, 0.2);
}

</style>

