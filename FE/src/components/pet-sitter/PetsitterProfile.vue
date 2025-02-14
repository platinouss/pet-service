<template>
  <div class="min-h-screen bg-gray-50 pt-24">
    <div class="max-w-5xl mx-auto px-4">
      <!-- 프로필 헤더 섹션 -->
      <div class="bg-white rounded-xl shadow-lg overflow-hidden mb-8">
        <div class="relative h-64 bg-blue-500">
          <img 
            :src="petsitter.coverImage || 'https://images.unsplash.com/photo-1544568100-847a948585b9'" 
            class="w-full h-full object-cover"
            alt="커버 이미지"
          />
          <div class="absolute -bottom-16 left-8">
            <img 
              :src="petsitter.imageUrl" 
              class="w-32 h-32 rounded-full border-4 border-white object-cover"
              :alt="`${petsitter.name}의 프로필`"
            />
          </div>
        </div>
        <div class="pt-20 pb-8 px-8">
          <div class="flex justify-between items-start">
            <div>
              <h1 class="text-3xl font-bold text-gray-900">{{ petsitter.name }}</h1>
              <div class="flex items-center mt-2 text-gray-600">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                </svg>
                {{ petsitter.location }}
              </div>
            </div>
            <div class="text-right">
              <div class="text-2xl font-bold text-blue-600">
                시급 {{ formatPrice(petsitter.hourlyRate) }}원
              </div>
              <div class="text-gray-500 mt-1">최소 2시간 부터</div>
            </div>
          </div>
        </div>
      </div>

      <!-- 상세 정보 섹션 -->
      <div class="grid grid-cols-1 md:grid-cols-3 gap-8 mb-8">
        <!-- 왼쪽 사이드바 -->
        <div class="md:col-span-1 space-y-6">
          <!-- 돌봄 가능 동물 -->
          <div class="bg-white rounded-xl shadow-sm p-6">
            <h2 class="text-xl font-semibold mb-4">돌봄 가능한 반려동물</h2>
            <div class="flex flex-wrap gap-2">
              <span 
                v-for="petType in petsitter.petTypes" 
                :key="petType"
                class="bg-blue-100 text-blue-800 px-3 py-1 rounded-full text-sm"
              >
                {{ petType }}
              </span>
            </div>
          </div>

          <!-- 가능 시간 -->
          <div class="bg-white rounded-xl shadow-sm p-6">
            <h2 class="text-xl font-semibold mb-4">활동 가능 시간</h2>
            <div class="text-gray-600">
              <div class="flex items-center mb-2">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
                {{ petsitter.availability }}
              </div>
            </div>
          </div>

          <!-- 연락처 정보 -->
          <div class="bg-white rounded-xl shadow-sm p-6">
            <h2 class="text-xl font-semibold mb-4">연락처 정보</h2>
            <div class="space-y-3 text-gray-600">
              <div class="flex items-center">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                </svg>
                {{ petsitter.email }}
              </div>
              <div class="flex items-center">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
                </svg>
                {{ petsitter.phone }}
              </div>
            </div>
          </div>
        </div>

        <!-- 메인 컨텐츠 -->
        <div class="md:col-span-2 space-y-6">
          <!-- 자기소개 -->
          <div class="bg-white rounded-xl shadow-sm p-6">
            <h2 class="text-xl font-semibold mb-4">자기소개</h2>
            <p class="text-gray-600 whitespace-pre-line">{{ petsitter.introduction }}</p>
          </div>

          <!-- 서비스 및 요금 -->
          <div class="bg-white rounded-xl shadow-sm p-6">
            <h2 class="text-xl font-semibold mb-4">제공 서비스</h2>
            <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div v-for="service in petsitter.services" :key="service.name" 
                   class="flex items-center p-4 border rounded-lg">
                <div class="text-blue-500 mr-3">
                  <component :is="service.icon" class="w-6 h-6" />
                </div>
                <div>
                  <div class="font-medium">{{ service.name }}</div>
                  <div class="text-sm text-gray-500">{{ service.description }}</div>
                </div>
              </div>
            </div>
          </div>

          <!-- 후기 섹션 -->
          <div class="bg-white rounded-xl shadow-sm p-6">
            <h2 class="text-xl font-semibold mb-4">이용자 후기</h2>
            <div class="space-y-4">
              <div v-for="review in petsitter.reviews" :key="review.id" 
                   class="border-b last:border-0 pb-4 last:pb-0">
                <div class="flex items-center justify-between mb-2">
                  <div class="flex items-center">
                    <img :src="review.userImage" alt="프로필" 
                         class="w-10 h-10 rounded-full mr-3">
                    <div>
                      <div class="font-medium">{{ review.userName }}</div>
                      <div class="text-sm text-gray-500">{{ review.date }}</div>
                    </div>
                  </div>
                  <div class="flex items-center text-yellow-400">
                    <svg v-for="i in 5" :key="i" class="w-5 h-5" 
                         :class="i <= review.rating ? 'fill-current' : 'stroke-current fill-none'"
                         viewBox="0 0 24 24">
                      <path d="M12 2l3.09 6.26L22 9.27l-5 4.87 1.18 6.88L12 17.77l-6.18 3.25L7 14.14 2 9.27l6.91-1.01L12 2z" />
                    </svg>
                  </div>
                </div>
                <p class="text-gray-600">{{ review.comment }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      petsitter: {
        name: '김펫돌',
        location: '서울 강남구',
        imageUrl: 'https://images.unsplash.com/photo-1537151625747-768eb6cf92b2?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80',
        coverImage: 'https://images.unsplash.com/photo-1544568100-847a948585b9?ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80',
        hourlyRate: 15000,
        petTypes: ['강아지', '고양이', '토끼'],
        availability: '주중 오전 9시 ~ 오후 6시',
        email: 'petdol@example.com',
        phone: '010-1234-5678',
        introduction: `안녕하세요! 반려동물을 사랑하는 펫시터 김펫돌입니다.
        
저는 3년간의 동물병원 근무 경력이 있으며, 현재는 전문 펫시터로 활동하고 있습니다. 반려동물들의 건강과 안전을 최우선으로 생각하며, 각 아이들의 특성에 맞는 맞춤 돌봄을 제공합니다.

특히 노견과 노묘를 돌보는 데 전문성이 있으며, 투약이나 기초 의료관리도 가능합니다.`,
        services: [
          {
            name: '산책 서비스',
            description: '반려동물의 성향에 맞춘 맞춤 산책',
            icon: 'WalkIcon'
          },
          {
            name: '실내 돌봄',
            description: '집안에서 안전하게 돌봄 서비스 제공',
            icon: 'HomeIcon'
          },
          {
            name: '투약 관리',
            description: '전문적인 투약 및 처치',
            icon: 'MedicineIcon'
          },
          {
            name: '놀이 및 교육',
            description: '반려동물 맞춤형 놀이와 기초 교육',
            icon: 'PlayIcon'
          }
        ],
        reviews: [
          {
            id: 1,
            userName: '박지민',
            userImage: 'https://randomuser.me/api/portraits/women/1.jpg',
            date: '2024-02-15',
            rating: 5,
            comment: '너무 꼼꼼하게 잘 봐주셨어요. 특히 우리 강아지가 좋아하는 장난감도 새로 준비해주시고 산책도 정말 잘 시켜주셨습니다.'
          },
          {
            id: 2,
            userName: '김현우',
            userImage: 'https://randomuser.me/api/portraits/men/1.jpg',
            date: '2024-02-10',
            rating: 5,
            comment: '급하게 부탁드렸는데도 친절하게 봐주셔서 감사합니다. 다음에도 꼭 부탁드리고 싶어요!'
          }
        ]
      }
    }
  },
  methods: {
    formatPrice(price) {
      return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    }
  }
}
</script>

<style scoped>
.profile-container {
  max-width: 500px;
  margin: auto;
  padding: 20px;
}
.title {
  font-size: 24px;
  font-weight: bold;
  color: indigo;
}
.card {
  background-color: #f3f3f3;
  padding: 15px;
  border-radius: 10px;
}
.name {
  font-size: 20px;
  font-weight: bold;
  color: darkblue;
}
.info {
  color: gray;
}
</style>