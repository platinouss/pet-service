<template>
  <div class="relative">
    <div class="absolute inset-0 bg-blue-100 dark:bg-blue-950" /> <!-- 원하는 색상으로 변경 -->
    <div class="relative mx-auto max-w-5xl px-6 py-24 sm:py-32 lg:px-8">
      <div class="mx-auto max-w-2xl text-center">
        <h1 class="text-4xl text-white font-bold tracking-tight sm:text-6xl">
          소중한 반려견, 안심 케어 !
        </h1>
        <p class="mt-6 text-lg leading-8 text-gray-600">
          믿을 수 있는 펫시터 서비스
        </p>
        <div class="mt-10 flex items-center justify-center gap-x-6">
          <router-link to="/browse">
            <button class="rounded-full bg-black text-white px-4 py-2 text-sm font-semibold shadow-sm hover:bg-gray-800">
              서비스 체험하기
            </button>
          </router-link>
          <router-link to="/sell">
            <button class="rounded-full bg-black text-white px-4 py-2 text-sm font-semibold shadow-sm hover:bg-gray-800">
              펫시터로 등록하기
            </button>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
</script>
