<template>
  <div class="flex items-center justify-center min-h-screen">
    <div class="text-center px-4">
      <h1 class="text-4xl md:text-5xl lg:text-6xl font-bold text-white mb-6">
        소중한 반려견, 안심케어
      </h1>
      <p class="text-xl md:text-2xl text-blue-100 mb-12">
        전문 펫시터가 책임지고 돌봐드립니다
      </p>
      <router-link
        to="/pet-sitter"
        class="inline-block bg-white text-blue-600 font-bold py-4 px-8 rounded-lg hover:bg-blue-50 transition-colors duration-300"
      >
        서비스 체험하기
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MainHero'
}
</script>
