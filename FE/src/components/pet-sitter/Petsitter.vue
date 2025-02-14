<template>
  <main class="min-h-screen bg-gray-50">
    <!-- 헤더 섹션 - 그라데이션 배경으로 변경 -->
    <div class="bg-gradient-to-r from-blue-500 to-indigo-600 py-16">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="text-center">
          <h1 class="text-4xl font-bold text-white mb-4">
            신뢰할 수 있는 펫시터를 만나보세요
          </h1>
          <p class="text-xl text-blue-100 mb-8">
            전문 펫시터가 여러분의 소중한 가족을 안전하게 돌봐드립니다
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

    <!-- 필터 섹션 -->
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 -mt-8">
      <div class="bg-white rounded-xl shadow-lg p-6 mb-8">
        <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
          <!-- 지역 필터 -->
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
          <!-- 펫 종류 필터 -->
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
          <!-- 가격 범위 필터 -->
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

    <!-- 펫시터 목록 섹션 - 배경색 변경 -->
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 pb-16">

      <!-- 펫시터 카드 그리드 -->
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        <PetsitterCard 
          v-for="petsitter in filteredPetsitters" 
          :key="petsitter.id" 
          :petsitter="petsitter" 
        />
      </div>

      <!-- 결과 없음 표시 - 디자인 개선 -->
      <div 
        v-if="filteredPetsitters.length === 0" 
        class="text-center py-16 bg-white rounded-xl shadow-lg mt-8"
      >
        <div class="text-gray-500 text-lg">
          검색 결과가 없습니다
        </div>
      </div>
    </div>

    <!-- 펫시터 등록 모달 -->
    <PetsitterRegister 
      :isOpen="isModalOpen"
      @close="closeModal"
    />
  </main>
</template>

<script>
import PetsitterCard from './PetsitterCard.vue';
import PetsitterRegister from './PetsitterRegister.vue';

export default {
  components: {
    PetsitterCard,
    PetsitterRegister
  },
  data() {
    return {
      isModalOpen: false,
      locations: ['서울 강남구', '서울 마포구', '서울 송파구', '경기 성남시', '경기 수원시'],
      petTypes: ['강아지', '고양이', '토끼', '햄스터', '새', '파충류'],
      filters: {
        location: '',
        petType: '',
        priceRange: ''
      },
      sortBy: 'rating',
      petsitters: [
        {
          id: 1,
          name: '김펫돌',
          imageUrl: 'https://images.unsplash.com/photo-1537151625747-768eb6cf92b2?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80',
          location: '서울 강남구',
          petTypes: ['강아지', '고양이'],
          availability: '주중 오전',
          hourlyRate: 15000,
          rating: 4.8,
          reviewCount: 56
        },
        {
          id: 2,
          name: '이멍냥',
          imageUrl: 'https://images.unsplash.com/photo-1518020382113-a7e8fc38eac9?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80',
          location: '서울 마포구',
          petTypes: ['강아지'],
          availability: '주말',
          hourlyRate: 20000,
          rating: 4.9,
          reviewCount: 43
        },
        {
          id: 3,
          name: '박애견',
          imageUrl: 'https://images.unsplash.com/photo-1516734212186-65266f46771f?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80',
          location: '경기 성남시',
          petTypes: ['고양이', '소동물'],
          availability: '평일 저녁',
          hourlyRate: 18000,
          rating: 4.7,
          reviewCount: 38
        }
      ]
    };
  },
  computed: {
    filteredPetsitters() {
      let result = [...this.petsitters];

      // 필터 적용
      if (this.filters.location) {
        result = result.filter(p => p.location === this.filters.location);
      }
      if (this.filters.petType) {
        result = result.filter(p => p.petTypes.includes(this.filters.petType));
      }
      if (this.filters.priceRange) {
        const [min, max] = this.filters.priceRange.split('-').map(Number);
        result = result.filter(p => {
          if (max) {
            return p.hourlyRate >= min && p.hourlyRate <= max;
          }
          return p.hourlyRate >= min;
        });
      }

      // 정렬 적용
      result.sort((a, b) => {
        switch (this.sortBy) {
          case 'rating':
            return b.rating - a.rating;
          case 'price-low':
            return a.hourlyRate - b.hourlyRate;
          case 'price-high':
            return b.hourlyRate - a.hourlyRate;
          default:
            return 0;
        }
      });

      return result;
    }
  },
  methods: {
    openModal() {
      this.isModalOpen = true;
    },
    closeModal() {
      this.isModalOpen = false;
    }
  }
};
</script>

<style scoped>
main {
  padding: 20px;
  background-color: #172554;
}

  .card {
    @apply bg-white shadow-lg rounded-lg overflow-hidden hover:shadow-xl transition-shadow duration-300;
  }

  .input {
    @apply w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500;
  }

  .btn {
    @apply font-bold py-2 px-4 rounded transition duration-300 ease-in-out;
  }

  .btn-primary {
    @apply bg-indigo-600 text-white hover:bg-indigo-700;
  }

  .btn-secondary {
    @apply bg-purple-600 text-white hover:bg-purple-700;
  }

  .btn-danger {
    @apply bg-red-600 text-white hover:bg-red-700;
  }

</style>