<template>
  <div v-if="isOpen" class="fixed inset-0 z-50 overflow-y-auto">
    <div class="flex items-center justify-center min-h-screen px-4 pt-4 pb-20 text-center sm:p-0">
      <!-- 배경 오버레이 -->
      <div class="fixed inset-0 transition-opacity" @click="closeModal">
        <div class="absolute inset-0 bg-gray-500 opacity-75"></div>
      </div>

      <!-- 모달 컨텐츠 -->
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
            <!-- 기본 정보 섹션 -->
            <div>
              <h3 class="text-lg font-semibold text-gray-900 mb-4">기본 정보</h3>
              <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                <!-- 프로필 이미지 업로드 -->
                <div class="col-span-full">
                  <label class="block text-sm font-medium text-gray-700 mb-2">프로필 이미지</label>
                  <div class="flex items-center space-x-4">
                    <div class="w-24 h-24 rounded-full bg-gray-100 flex items-center justify-center overflow-hidden">
                      <img v-if="imagePreview" :src="imagePreview" class="w-full h-full object-cover" alt="프로필 미리보기">
                      <svg v-else class="w-12 h-12 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                      </svg>
                    </div>
                    <input
                      type="file"
                      @change="handleImageUpload"
                      accept="image/*"
                      class="hidden"
                      ref="fileInput"
                    >
                    <button
                      type="button"
                      @click="$refs.fileInput.click()"
                      class="px-4 py-2 text-sm font-medium text-blue-600 bg-blue-50 rounded-md hover:bg-blue-100"
                    >
                      이미지 업로드
                    </button>
                  </div>
                </div>

                <!-- 이름 -->
                <div>
                  <label class="block text-sm font-medium text-gray-700 mb-2">이름</label>
                  <input
                    v-model="formData.name"
                    type="text"
                    class="w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500"
                    required
                  >
                </div>

                <!-- 연락처 -->
                <div>
                  <label class="block text-sm font-medium text-gray-700 mb-2">연락처</label>
                  <input
                    v-model="formData.phone"
                    type="tel"
                    class="w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500"
                    required
                  >
                </div>

                <!-- 이메일 -->
                <div>
                  <label class="block text-sm font-medium text-gray-700 mb-2">이메일</label>
                  <input
                    v-model="formData.email"
                    type="email"
                    class="w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500"
                    required
                  >
                </div>

                <!-- 활동 지역 -->
                <div>
                  <label class="block text-sm font-medium text-gray-700 mb-2">활동 지역</label>
                  <input
                    v-model="formData.location"
                    type="text"
                    class="w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500"
                    required
                  >
                </div>
              </div>
            </div>

            <!-- 서비스 정보 섹션 -->
            <div>
              <h3 class="text-lg font-semibold text-gray-900 mb-4">서비스 정보</h3>
              <div class="space-y-4">
                <!-- 돌봄 가능한 동물 -->
                <div>
                  <label class="block text-sm font-medium text-gray-700 mb-2">돌봄 가능한 동물</label>
                  <div class="flex flex-wrap gap-3">
                    <label v-for="pet in petTypes" :key="pet" class="inline-flex items-center">
                      <input
                        type="checkbox"
                        v-model="formData.selectedPetTypes"
                        :value="pet"
                        class="form-checkbox h-5 w-5 text-blue-600"
                      >
                      <span class="ml-2 text-gray-700">{{ pet }}</span>
                    </label>
                  </div>
                </div>

                <!-- 활동 가능 시간 -->
                <div>
                  <label class="block text-sm font-medium text-gray-700 mb-2">활동 가능 시간</label>
                  <div class="space-y-4">
                    <!-- 요일 선택 -->
                    <div class="flex flex-wrap gap-3">
                      <label v-for="day in availableDays" :key="day" class="inline-flex items-center">
                        <input
                          type="checkbox"
                          v-model="formData.availability.days"
                          :value="day"
                          class="form-checkbox h-5 w-5 text-blue-600"
                        >
                        <span class="ml-2 text-gray-700">{{ day }}</span>
                      </label>
                    </div>
                    
                    <!-- 시간 선택 -->
                    <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                      <div>
                        <label class="block text-sm font-medium text-gray-700 mb-2">시작 시간</label>
                        <select
                          v-model="formData.availability.startTime"
                          class="w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500"
                          required
                        >
                          <option value="">선택해주세요</option>
                          <option v-for="time in timeSlots" :key="time" :value="time">
                            {{ time }}
                          </option>
                        </select>
                      </div>
                      <div>
                        <label class="block text-sm font-medium text-gray-700 mb-2">종료 시간</label>
                        <select
                          v-model="formData.availability.endTime"
                          class="w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500"
                          required
                        >
                          <option value="">선택해주세요</option>
                          <option v-for="time in timeSlots" :key="time" :value="time">
                            {{ time }}
                          </option>
                        </select>
                      </div>
                    </div>
                  </div>
                </div>

                <!-- 시급 -->
                <div>
                  <label class="block text-sm font-medium text-gray-700 mb-2">시급 (원)</label>
                  <input
                    v-model.number="formData.hourlyRate"
                    type="number"
                    min="0"
                    step="1000"
                    class="w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500"
                    required
                  >
                </div>
              </div>
            </div>

            <!-- 자기소개 -->
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-2">자기소개</label>
              <textarea
                v-model="formData.introduction"
                rows="4"
                class="w-full px-4 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500"
                required
              ></textarea>
            </div>
          </div>

          <!-- 제출 버튼 -->
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
  </div>
</template>

<script>
export default {
  props: {
    isOpen: {
      type: Boolean,
      required: true
    }
  },
  data() {
    return {
      imagePreview: null,
      petTypes: ['강아지', '고양이', '토끼', '햄스터', '새', '파충류'],
      availableDays: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
      timeSlots: [
        '06:00', '07:00', '08:00', '09:00', '10:00', '11:00', '12:00',
        '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00',
        '20:00', '21:00', '22:00'
      ],
      formData: {
        name: '',
        phone: '',
        email: '',
        location: '',
        selectedPetTypes: [],
        availability: {
          days: [],
          startTime: '',
          endTime: ''
        },
        hourlyRate: 15000,
        introduction: '',
        image: null
      }
    }
  },
  methods: {
    handleImageUpload(event) {
      const file = event.target.files[0]
      if (file) {
        this.formData.image = file
        this.imagePreview = URL.createObjectURL(file)
      }
    },
    submitForm() {
      // 시간 데이터 포맷팅
      const availabilityText = `${this.formData.availability.days.join(', ')} ${this.formData.availability.startTime}~${this.formData.availability.endTime}`
      console.log('Available time:', availabilityText)
      // TODO: API 연동
      console.log('Form submitted:', this.formData)
      this.$emit('close')
    },
    closeModal() {
      this.$emit('close')
    }
  }
}
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-container {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  max-width: 600px;
  width: 90%;
  max-height: 90vh;
  overflow-y: auto;
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.close-btn {
  background: none;
  border: none;
  font-size: 24px;
  cursor: pointer;
  color: #666;
}

.container {
  max-width: 600px;
  margin: auto;
  padding: 40px 20px;
}
.title {
  font-size: 24px;
  font-weight: bold;
  color: indigo;
  margin-bottom: 20px;
}
.form {
  display: flex;
  flex-direction: column;
  gap: 15px;
}
.label {
  font-size: 16px;
  font-weight: bold;
  color: #333;
}
.input {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
.checkbox-group {
  display: flex;
  gap: 10px;
}
.checkbox-label {
  font-size: 16px;
  font-weight: bold;
  color: #222;
}
.btn {
  padding: 10px;
  background-color: indigo;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 5px;
  margin-top: 20px;
}
</style>