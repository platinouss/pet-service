<template>
  <main class="min-h-screen bg-gray-50">
    <div class="bg-gradient-to-r from-blue-500 to-indigo-600 py-16">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="text-center">
          <h1 class="text-4xl font-bold text-white mb-4">
            신뢰할 수 있는 펫시터를 만나보세요
          </h1>
          <p class="text-xl text-blue-100 mb-8">
            전문 펫시터가 여러분의 소중한 가족을 안전하게 돌봐드립니다.
          </p>
          <div class="flex justify-center space-x-4">
            <button 
              @click="openModal" 
              class="px-6 py-3 bg-white text-blue-600 font-semibold rounded-lg hover:bg-blue-50 transition-colors duration-200"
            >
              펫시터로 등록
            </button>
            <router-link 
              to="/pet-sitter/me" 
              class="px-6 py-3 bg-blue-500 text-white font-semibold rounded-lg hover:bg-blue-400 transition-colors duration-200 border-2 border-white"
            >
              내 펫시터 프로필 확인
            </router-link>
          </div>
        </div>
      </div>
    </div>

    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 -mt-8">
      <div class="bg-white rounded-xl shadow-lg p-6 mb-8">
        <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-1">지역</label>
            <select 
              v-model="filters.location" 
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500"
            >
              <option value="">전체 지역</option>
              <option v-for="location in locations" :key="location" :value="location">
                {{ location }}
              </option>
            </select>
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-1">돌봄 가능한 반려동물</label>
            <select 
              v-model="filters.petType" 
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500"
            >
              <option value="">전체 종류</option>
              <option v-for="type in petTypes" :key="type" :value="type">
                {{ type }}
              </option>
            </select>
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-1">시급 범위</label>
            <select
              v-model="filters.priceRange"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500"
            >
              <option value="">전체 가격대</option>
              <option value="0-15000">15,000원 이하</option>
              <option value="15000-20000">15,000원 ~ 20,000원</option>
              <option value="20000+">20,000원 이상</option>
            </select>
          </div>
        </div>
      </div>
    </div>

    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 pb-16">

      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        <PetsitterCard 
          v-for="petsitter in filteredPetsitters" 
          :key="petsitter.id" 
          :petsitter="petsitter" 
        />
      </div>

      <div
        v-if="filteredPetsitters.length === 0" 
        class="text-center py-16 bg-white rounded-xl shadow-lg mt-8"
      >
        <div class="text-gray-500 text-lg">
          검색 결과가 없습니다
        </div>
      </div>
    </div>

    <PetsitterRegister
      :isOpen="isModalOpen"
      @close="closeModal"
    />
  </main>
</template>

<script setup>
import {ref, computed, onMounted} from 'vue';
import PetsitterCard from './PetsitterCard.vue';
import PetsitterRegister from './PetsitterRegister.vue';
import {useToast} from "vue-toastification";

const toast = useToast();
const isModalOpen = ref(false);
const locations = ref([
  '서울특별시',
  '부산광역시',
  '대구광역시',
  '인천광역시',
  '광주광역시',
  '대전광역시',
  '울산광역시',
  '세종특별자치시',
  '경기도',
  '강원도',
  '충청북도',
  '충청남도',
  '전라북도',
  '전라남도',
  '경상북도',
  '경상남도',
  '제주특별자치도'
]);
const petTypes = ref(['강아지', '고양이', '토끼', '햄스터', '새', '파충류']);
const filters = ref({
  location: '',
  petType: '',
  priceRange: ''
});
const sortBy = ref('rating');
const DEFAULT_IMAGE_URL = 'https://images.unsplash.com/photo-1537151625747-768eb6cf92b2?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80';
const petsitters = ref([]);

onMounted(async () => {
  try {
    const response = await fetch(`${import.meta.env.VITE_API_URL}/api/v1/pet-sitters`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
      credentials: 'include'
    });
    const data = await response.json();
    const petsittersWithImage = data.map(petsitter => ({
      ...petsitter,
      imageUrl: DEFAULT_IMAGE_URL
    }));
    petsitters.value.push(...petsittersWithImage);
  } catch (e) {
    toast.error("펫시터 목록을 조회할 수 없습니다.");
  }
});

const filteredPetsitters = computed(() => {
  let result = [...petsitters.value];

  if (filters.value.location) {
    result = result.filter(p => p.region === filters.value.location);
  }
  if (filters.value.petType) {
    result = result.filter(p => {
      const types = p.availableAnimalType.includes(',') 
        ? p.availableAnimalType.split(',')
        : [p.availableAnimalType];
      return types.includes(filters.value.petType);
    });
  }
  if (filters.value.priceRange) {
    const [min, max] = filters.value.priceRange.split('-').map(Number);
    result = result.filter(p => {
      if (max) {
        return p.price >= min && p.price <= max;
      }
      return p.price >= min;
    });
  }

  result.sort((a, b) => {
    switch (sortBy.value) {
      case 'rating':
        return b.rating - a.rating;
      case 'price-low':
        return a.price - b.price;
      case 'price-high':
        return b.price - a.price;
      default:
        return 0;
    }
  });

  return result;
});

const openModal = () => {
  isModalOpen.value = true;
};

const closeModal = () => {
  isModalOpen.value = false;
};
</script>

<style scoped>
main {
  padding: 20px;
  background-color: #172554;
}
</style>