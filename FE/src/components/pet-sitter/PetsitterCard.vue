<template>
  <div class="bg-white rounded-xl shadow-lg overflow-hidden hover:shadow-xl transition-shadow duration-300">
    <div class="relative">
      <img 
        :src="petsitter.imageUrl" 
        :alt="`${petsitter.username}의 프로필`"
        class="w-full h-48 object-cover"
      />
      <div class="absolute top-4 right-4 bg-blue-500 text-white px-3 py-1 rounded-full text-sm font-semibold">
        시급 {{ formatPrice(petsitter.price) }}원
      </div>
    </div>
    
    <div class="p-6">
      <div class="flex items-center justify-between mb-4">
        <h2 class="text-2xl font-bold text-gray-800">{{ petsitter.username }}</h2>
        <div class="flex items-center text-gray-600">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-1" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
          </svg>
          {{ petsitter.region }}
        </div>
      </div>

      <div class="mb-4">
        <div class="flex items-center mb-2">
          <div class="flex items-center">
          </div>
        </div>
        <div class="flex flex-wrap gap-2">
          <span 
            v-for="type in (petsitter.availableAnimalType.includes(',') 
              ? petsitter.availableAnimalType.split(',') 
              : [petsitter.availableAnimalType])" 
            :key="type"
            class="bg-blue-100 text-blue-800 px-3 py-1 rounded-full text-sm"
          >
            {{ type }}
          </span>
        </div>
      </div>

      <p class="text-gray-600 mb-2">
        <svg class="inline-block h-5 w-5 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
        </svg>
        <span v-for="(date, index) in petsitter.availableDates" :key="date.dayOfWeek">
          {{ formatDayOfWeek(date.dayOfWeek) }} {{ formatTime(date.startTime) }}-{{ formatTime(date.endTime) }}{{ index !== petsitter.availableDates.length - 1 ? ', ' : '' }}
        </span>
      </p>

      <button 
        @click="showDetails"
        class="w-full bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-4 rounded-lg transition-colors duration-300"
      >
        상세 정보 보기
      </button>
    </div>

    <div v-if="isModalOpen" class="fixed inset-0 z-50 overflow-y-auto" aria-labelledby="modal-title" role="dialog" aria-modal="true">
      <div class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0">
        <div class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" @click="closeModal"></div>

        <div class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-2xl sm:w-full">
          <div class="bg-gradient-to-r from-blue-500 to-indigo-600 px-6 py-4">
            <div class="flex justify-between items-center">
              <h3 class="text-2xl font-bold text-white" id="modal-title">
                {{ petsitter.username }}의 프로필
              </h3>
              <button @click="closeModal" class="text-white hover:text-gray-200">
                <svg class="h-6 w-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                </svg>
              </button>
            </div>
          </div>

          <div class="px-6 py-4">
            <div class="flex items-start space-x-4 mb-6">
              <img 
                :src="petsitter.imageUrl" 
                :alt="petsitter.username"
                class="w-32 h-32 rounded-lg object-cover"
              />
              <div>
                <p class="text-gray-600 mb-2">
                  <svg class="inline-block h-5 w-5 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                  </svg>
                  {{ petsitter.region }}
                </p>
                <p class="text-gray-600 mb-2">
                  <svg class="inline-block h-5 w-5 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                  </svg>
                  <span v-for="(date, index) in petsitter.availableDates" :key="date.dayOfWeek">
                    {{ formatDayOfWeek(date.dayOfWeek) }} {{ formatTime(date.startTime) }}-{{ formatTime(date.endTime) }}{{ index !== petsitter.availableDates.length - 1 ? ', ' : '' }}
                  </span>
                </p>
                <div class="flex flex-wrap gap-2 mt-2">
                  <span 
                    v-for="type in (petsitter.availableAnimalType.includes(',') 
                      ? petsitter.availableAnimalType.split(',') 
                      : [petsitter.availableAnimalType])" 
                    :key="type"
                    class="bg-blue-100 text-blue-800 px-3 py-1 rounded-full text-sm"
                  >
                    {{ type }}
                  </span>
                </div>
              </div>
            </div>

            <div class="mb-6">
              <h4 class="text-lg font-semibold mb-2">자기소개</h4>
              <p class="text-gray-600 whitespace-pre-line">{{ petsitter.introduce }}</p>
            </div>

<!--            <div class="mb-6">-->
<!--              <h4 class="text-lg font-semibold mb-2">제공 서비스</h4>-->
<!--              <div class="grid grid-cols-2 gap-4">-->
<!--                <div v-for="service in petsitter.services" :key="service.name" -->
<!--                     class="flex items-start p-3 border rounded-lg">-->
<!--                  <div class="text-blue-500 mr-3">-->
<!--                    <component :is="service.icon" class="w-6 h-6" />-->
<!--                  </div>-->
<!--                  <div>-->
<!--                    <div class="font-medium">{{ service.name }}</div>-->
<!--                    <div class="text-sm text-gray-500">{{ service.description }}</div>-->
<!--                  </div>-->
<!--                </div>-->
<!--              </div>-->
<!--            </div>-->

            <div class="bg-gray-50 rounded-lg p-4 mb-6">
              <div class="flex justify-between items-center">
                <div>
                  <h4 class="text-lg font-semibold">시간당 요금</h4>
                </div>
                <div class="text-2xl font-bold text-blue-600">
                  {{ formatPrice(petsitter.price) }}원
                </div>
              </div>
            </div>
          </div>

          <div class="bg-gray-50 px-6 py-4">
            <button 
              @click="makeReservation"
              class="w-full bg-blue-600 text-white font-semibold py-3 px-6 rounded-lg hover:bg-blue-700 transition-colors duration-300"
            >
              예약하기
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, defineProps } from 'vue';

const props = defineProps({
  petsitter: {
    type: Object,
    required: true
  }
});

const isModalOpen = ref(false);

const formatPrice = (price) => {
  return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
};

const showDetails = () => {
  isModalOpen.value = true;
};

const closeModal = () => {
  isModalOpen.value = false;
};

const makeReservation = () => {
  console.log('예약하기 클릭:', props.petsitter.username);
};

// 요일 포맷팅 함수
const formatDayOfWeek = (day) => {
  const dayMap = {
    'MONDAY': '월요일',
    'TUESDAY': '화요일',
    'WEDNESDAY': '수요일',
    'THURSDAY': '목요일',
    'FRIDAY': '금요일',
    'SATURDAY': '토요일',
    'SUNDAY': '일요일'
  };
  return dayMap[day] || day;
};

// 시간 포맷팅 함수
const formatTime = (time) => {
  return time.substring(0, 5); // "09:00:00" -> "09:00"
};
</script>
