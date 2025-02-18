<template>
  <div v-if="isOpen" class="fixed inset-0 z-50 overflow-y-auto">
    <div class="flex items-center justify-center min-h-screen px-4 pt-4 pb-20 text-center sm:p-0">
      <div class="fixed inset-0 transition-opacity" @click="closeModal">
        <div class="absolute inset-0 bg-gray-500 opacity-75"></div>
      </div>

      <div class="inline-block w-full max-w-3xl overflow-hidden text-left align-middle transition-all transform bg-white rounded-2xl shadow-xl">
        <div class="bg-blue-500 px-8 py-6">
          <div class="flex items-center justify-between">
            <h2 class="text-2xl font-bold text-white">펫시터 등록</h2>
            <button @click="closeModal" class="text-white hover:text-gray-200">
              <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
              </svg>
            </button>
          </div>
        </div>

        <form @submit.prevent="submitForm" class="px-8 py-6">
          <div class="space-y-6">
            <div>
              <h3 class="text-lg font-semibold text-gray-900 mb-4">기본 정보</h3>
              <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div>
                  <label class="block text-sm font-medium text-gray-700 mb-2">활동 지역</label>
                  <select
                    v-model="formData.region"
                    class="w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500"
                    required
                  >
                    <option value="">지역 선택</option>
                    <option v-for="location in locations" :key="location" :value="location.value">
                      {{ location.name }}
                    </option>
                  </select>
                </div>
              </div>
            </div>

            <div>
              <h3 class="text-lg font-semibold text-gray-900 mb-4">서비스 정보</h3>
              <div class="space-y-4">
                <div>
                  <label class="block text-sm font-medium text-gray-700 mb-2">돌봄 가능한 동물</label>
                  <div class="flex flex-wrap gap-3">
                    <label v-for="pet in petTypes" :key="pet" class="inline-flex items-center">
                      <input
                          type="checkbox"
                          v-model="formData.availableAnimalTypes"
                          :value="pet.codeId"
                          class="form-checkbox h-5 w-5 text-blue-600"
                      />
                      <span class="ml-2 text-gray-700">{{ pet.codeName }}</span>
                    </label>
                  </div>
                </div>

                <div>
                  <label class="block text-sm font-medium text-gray-700 mb-2">활동 가능 시간</label>
                  <div class="space-y-4">
                    <div class="bg-gray-50 p-4 rounded-lg">
                      <div class="grid grid-cols-1 gap-4">
                        <div class="grid grid-cols-1 md:grid-cols-[200px_1fr_150px] gap-4 items-center">
                          <div>
                            <select
                              v-model="scheduleSlot.day"
                              class="w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500"
                            >
                              <option value="">요일 선택</option>
                              <option v-for="day in days" :key="day.value" :value="day.value">
                                {{ day.label }}
                              </option>
                            </select>
                          </div>
                          <div class="flex items-center space-x-2">
                            <select
                              v-model="scheduleSlot.startTime"
                              class="w-full min-w-[130px] px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500"
                            >
                              <option value="">시작 시간</option>
                              <option v-for="time in dayTimeSlots" :key="time" :value="time">
                                {{ time }}
                              </option>
                            </select>
                            <span class="text-gray-500 mx-2 flex-shrink-0">~</span>
                            <select
                              v-model="scheduleSlot.endTime"
                              class="w-full min-w-[130px] px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500"
                            >
                              <option value="">종료 시간</option>
                              <option v-for="time in dayTimeSlots" :key="time" :value="time">
                                {{ time }}
                              </option>
                            </select>
                          </div>
                          <div>
                            <button
                              type="button"
                              @click="addTimeSlot"
                              class="w-full px-4 py-2 text-sm font-medium text-white bg-blue-600 rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
                              :disabled="!isValidTimeSlot"
                            >
                              시간 추가
                            </button>
                          </div>
                        </div>
                      </div>
                    </div>

                    <div v-if="selectedTimeSlots.length > 0" class="space-y-3">
                      <div class="text-sm font-medium text-gray-700">선택된 시간</div>
                      <div class="grid grid-cols-1 md:grid-cols-2 gap-3">
                        <div
                          v-for="(slot, index) in selectedTimeSlots"
                          :key="index"
                          class="flex items-center justify-between p-3 bg-white border border-gray-200 rounded-lg shadow-sm"
                        >
                          <div class="flex items-center space-x-3">
                            <span class="font-medium text-gray-900">{{ getDayLabel(slot.day) }}</span>
                            <span class="text-gray-600">{{ slot.startTime }} ~ {{ slot.endTime }}</span>
                          </div>
                          <button
                            type="button"
                            @click="removeTimeSlot(index)"
                            class="text-red-600 hover:text-red-800 focus:outline-none"
                          >
                            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                            </svg>
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                <div>
                  <label class="block text-sm font-medium text-gray-700 mb-2">시급 (원)</label>
                  <input
                    v-model.number="formData.price"
                    type="number"
                    min="0"
                    step="1000"
                    class="w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500"
                    required
                  >
                </div>
              </div>
            </div>

            <div>
              <label class="block text-sm font-medium text-gray-700 mb-2">자기소개</label>
              <textarea
                v-model="formData.introduce"
                rows="4"
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500"
                required
              ></textarea>
            </div>
          </div>

          <div class="mt-8 flex justify-end space-x-4">
            <button
              type="button"
              @click="closeModal"
              class="px-6 py-2 text-sm font-medium text-gray-700 bg-gray-100 rounded-md hover:bg-gray-200"
            >
              취소
            </button>
            <button
              type="submit"
              class="px-6 py-2 text-sm font-medium text-white bg-blue-600 rounded-md hover:bg-blue-700"
            >
              등록하기
            </button>
          </div>
        </form>
      </div>
    </div>

    <div
      v-if="toast.show"
      class="fixed bottom-4 right-4 px-6 py-3 rounded-lg shadow-lg transition-all duration-300"
      :class="toast.type === 'error' ? 'bg-red-500' : 'bg-blue-500'"
    >
      <div class="flex items-center text-white">
        <span class="mr-2">
          <svg v-if="toast.type === 'error'" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
          </svg>
          <svg v-else class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
          </svg>
        </span>
        <span class="text-sm font-medium">{{ toast.message }}</span>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref, computed, onMounted} from 'vue'
import {useToast} from "vue-toastification";

defineProps({
  isOpen: {
    type: Boolean,
    required: true
  }
});

const emit = defineEmits(['close'])
const petTypes = ref([])
const days = ref([
  { label: '월요일', value: 'MONDAY' },
  { label: '화요일', value: 'TUESDAY' },
  { label: '수요일', value: 'WEDNESDAY' },
  { label: '목요일', value: 'THURSDAY' },
  { label: '금요일', value: 'FRIDAY' },
  { label: '토요일', value: 'SATURDAY' },
  { label: '일요일', value: 'SUNDAY' }
])
const dayTimeSlots = ref([
  '06:00', '07:00', '08:00', '09:00', '10:00', '11:00', '12:00',
  '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00',
  '20:00', '21:00', '22:00'
])
const locations = ref([
  { name: '서울특별시', value: 'SEOUL' },
  { name: '부산광역시', value: 'BUSAN' },
  { name: '대구광역시', value: 'DAEGU' },
  { name: '인천광역시', value: 'INCHEON' },
  { name: '광주광역시', value: 'GWANGJU' },
  { name: '대전광역시', value: 'DAEJEON' },
  { name: '울산광역시', value: 'ULSAN' },
  { name: '세종특별자치시', value: 'SEJONG' },
  { name: '경기도', value: 'GYEONGGI' },
  { name: '강원도', value: 'GANGWON' },
  { name: '충청북도', value: 'CHUNGCHEONGBUK' },
  { name: '충청남도', value: 'CHUNGCHEONGNAM' },
  { name: '전라북도', value: 'JEOLLABUK' },
  { name: '전라남도', value: 'JEOLLANAM' },
  { name: '경상북도', value: 'GYEONGSANGBUK' },
  { name: '경상남도', value: 'GYEONGSANGNAM' },
  { name: '제주특별자치도', value: 'JEJU' },
])

const formData = ref({
  region: '',
  availableAnimalTypes: [],
  price: 0,
  introduce: ''
});

const scheduleSlot = ref({
  dayOfWeek: '',
  timeSlots: []
})

const selectedTimeSlots = ref([])
const toast = useToast();

const isValidTimeSlot = computed(() => {
  const { day, startTime, endTime } = scheduleSlot.value
  return day && startTime && endTime && startTime < endTime
})

onMounted(async () => {
  try {
    const response = await fetch(`${import.meta.env.VITE_API_URL}/api/v1/pets/category`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
      credentials: 'include'
    });
    const types = await response.json();
    petTypes.value.push(...types)
  } catch (e) {
    toast.error("애완동물 카테고리를 조회할 수 없습니다.");
  }
});

const addTimeSlot = () => {
  const { day, startTime, endTime } = scheduleSlot.value
  
  if (!day || !startTime || !endTime) {
    const missing = []
    if (!day) missing.push('요일')
    if (!startTime) missing.push('시작 시간')
    if (!endTime) missing.push('종료 시간')
    return;
  }
  if (startTime >= endTime) {
    toast.error('종료 시간은 시작 시간보다 늦어야 합니다.');
    return;
  }

  selectedTimeSlots.value.push({ ...scheduleSlot.value })
  scheduleSlot.value = {
    day: '',
    startTime: '',
    endTime: ''
  }
}

const removeTimeSlot = (index) => {
  selectedTimeSlots.value.splice(index, 1)
}

const getDayLabel = (value) => {
  return days.value.find(day => day.value === value)?.label || value
}

const submitForm = async () => {
  const groupedTimeSlots = selectedTimeSlots.value.reduce((acc, slot) => {
    const existingDay = acc.find(item => item.dayOfWeek === slot.day);
    const timeSlot = {
      startTime: slot.startTime,
      endTime: slot.endTime
    };

    if (existingDay) {
      existingDay.timeSlots.push(timeSlot);
    } else {
      acc.push({
        dayOfWeek: slot.day,
        timeSlots: [timeSlot]
      });
    }
    return acc;
  }, []);

  const formDataToSubmit = {
    ...formData.value,
    availableDates: groupedTimeSlots,
    availableAnimalTypes: [...formData.value.availableAnimalTypes]
  }
  
  try {
    await fetch(`${import.meta.env.VITE_API_URL}/api/v1/pet-sitters`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formDataToSubmit),
      credentials: 'include'
    });
    toast.success('펫시터 등록에 성공했습니다.');
  } catch (e) {
    toast.error("펫시터를 등록할 수 없습니다.");
  }
  emit('close')
}

const closeModal = () => {
  emit('close')
}
</script>
