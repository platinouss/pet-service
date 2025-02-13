<script setup>
import Navigation from './components/common/MainNavigation.vue'
import MainFooter from './components/common/MainFooter.vue'
</script>

<template>
  <div class="flex flex-col">
    <Navigation />
    <main>
      <router-view />
    </main>
    <MainFooter />
  </div>
</template>

