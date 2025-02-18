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

            <div class="mb-6">
              <h4 class="text-lg font-semibold mb-4">예약하기</h4>
              <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                  <label class="block text-sm font-medium text-gray-700 mb-2">날짜 선택</label>
                  <v-calendar
                    v-model="selectedDate"
                    :attributes="calendarAttributes"
                    :min-date="new Date()"
                    :max-date="maxDate"
                    @dayclick="onDayClick"
                  />
                </div>
                <div v-if="selectedDate">
                  <label class="block text-sm font-medium text-gray-700 mb-2">시간 선택</label>
                  <div class="space-y-2">
                    <button
                      v-for="timeSlot in availableTimeSlots"
                      :key="`${timeSlot.startTime}-${timeSlot.endTime}`"
                      @click="selectTimeSlot(timeSlot)"
                      :class="[
                        'w-full px-4 py-2 rounded-lg text-sm',
                        isSelectedTimeSlot(timeSlot)
                          ? 'bg-blue-600 text-white'
                          : 'bg-gray-100 text-gray-700 hover:bg-gray-200'
                      ]"
                    >
                      {{ timeSlot.startTime }} - {{ timeSlot.endTime }}
                    </button>
                  </div>
                </div>
              </div>
            </div>

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
import { ref, defineProps, computed } from 'vue';
import { useToast } from "vue-toastification";
import 'v-calendar/style.css';

const props = defineProps({
  petsitter: {
    type: Object,
    required: true
  }
});

const toast = useToast();
const isModalOpen = ref(false);
const selectedDate = ref(null);
const selectedTimeSlot = ref(null);
const maxDate = new Date(new Date().setMonth(new Date().getMonth() + 1));

// 선택 가능한 요일 계산
const availableDays = computed(() => {
  return props.petsitter.availableDates.map(date => date.dayOfWeek.toLowerCase());
});

// 캘린더 속성 설정
const calendarAttributes = computed(() => [{
  dates: new Date(),
  highlight: true,
  popover: {
    label: '오늘'
  }
}]);

// 해당 날짜의 사용 가능한 시간대 계산
const availableTimeSlots = computed(() => {
  if (!selectedDate.value) return [];
  
  const dayOfWeek = selectedDate.value.toLocaleDateString('en-US', { weekday: 'long' }).toUpperCase();
  const dateSchedule = props.petsitter.availableDates.find(d => d.dayOfWeek === dayOfWeek);
  
  if (!dateSchedule) return [];

  // 시작 시간과 종료 시간 사이의 1시간 단위 슬롯 생성
  const slots = [];
  let currentTime = new Date(`2000-01-01T${dateSchedule.startTime}`);
  const endTime = new Date(`2000-01-01T${dateSchedule.endTime}`);

  while (currentTime < endTime) {
    const startTime = currentTime.toTimeString().substring(0, 5);
    currentTime.setHours(currentTime.getHours() + 1);
    const endTime = currentTime.toTimeString().substring(0, 5);
    slots.push({ startTime, endTime });
  }

  return slots;
});

const onDayClick = (day) => {
  const dayName = day.date.toLocaleDateString('en-US', { weekday: 'long' }).toUpperCase();
  if (props.petsitter.availableDates.some(d => d.dayOfWeek === dayName)) {
    selectedDate.value = day.date;
    selectedTimeSlot.value = null;
  } else {
    toast.error('해당 요일은 예약이 불가능합니다.');
  }
};

const selectTimeSlot = (timeSlot) => {
  selectedTimeSlot.value = timeSlot;
  console.log('Selected time slot:', timeSlot); // 디버깅용
};

const isSelectedTimeSlot = (timeSlot) => {
  return selectedTimeSlot.value &&
    selectedTimeSlot.value.startTime === timeSlot.startTime &&
    selectedTimeSlot.value.endTime === timeSlot.endTime;
};

const formatPrice = (price) => {
  return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
};

const showDetails = () => {
  isModalOpen.value = true;
};

const closeModal = () => {
  isModalOpen.value = false;
};

const makeReservation = async () => {
  if (!selectedDate.value || !selectedTimeSlot.value) {
    toast.error('날짜와 시간을 선택해주세요.');
    return;
  }

  const { IMP } = window;
  IMP.init(import.meta.env.VITE_CUSTOMER_ID);

  // 예약 날짜와 시간 포맷팅
  const reservationDate = selectedDate.value.toISOString().split('T')[0];
  const reservationStartTime = `${reservationDate}T${selectedTimeSlot.value.startTime}:00`;
  const reservationEndTime = `${reservationDate}T${selectedTimeSlot.value.endTime}:00`;

  const merchantUid = `mid_${new Date().getTime()}`;

  const data = {
    pg: 'uplus',
    pay_method: 'card',
    merchant_uid: merchantUid,
    // amount: props.petsitter.price,
    amount: 1000,
    name: `${props.petsitter.username} 펫시터 서비스 비용`,
    buyer_name: '홍길동',
    buyer_tel: '01012341234',
    buyer_email: import.meta.env.VITE_BUYER_EMAIL,
    buyer_addr: '신사동 661-16',
    buyer_postcode: '06018'
  };

  IMP.request_pay(data, async (response) => {
    console.log('결제 응답:', response);
    const { success, error_msg, imp_uid } = response;
    
    if (success) {
      try {
        const reservationData = {
          petsitterId: props.petsitter.id,
          date: reservationDate,
          startTime: reservationStartTime,
          endTime: reservationEndTime,
          impUid: imp_uid,
          amount: data.amount
        };

        const response = await fetch(`${import.meta.env.VITE_API_URL}/api/v1/pet-sitters/reservation`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          credentials: 'include',
          body: JSON.stringify(reservationData)
        });

        if (!response.ok) {
          throw new Error('예약 저장에 실패했습니다.');
        }

        toast.success('예약이 완료되었습니다.');
        closeModal();
      } catch (error) {
        console.log(error);
        toast.error(`예약 저장 실패: ${error.message}`);
      }
    } else {
      toast.error(`결제 실패: ${error_msg}`);
    }
  });
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
