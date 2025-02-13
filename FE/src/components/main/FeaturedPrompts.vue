<template>
  <section class="py-16 px-6">
    <div class="mx-auto max-w-7xl">
      <h2 class="text-3xl font-bold tracking-tight text-center mb-12">Featured Prompts</h2>
      <div class="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
        <div v-for="prompt in featuredPrompts" :key="prompt.id" class="group relative overflow-hidden transition-all hover:shadow-lg bg-white rounded-lg shadow">
          <div class="p-6">
            <div class="flex justify-between items-start">
              <div>
                <h3 class="text-lg font-semibold">{{ prompt.title }}</h3>
                <p class="mt-2 text-sm text-gray-600">{{ prompt.description }}</p>
              </div>
              <span class="inline-flex items-center rounded-full bg-blue-100 px-2.5 py-0.5 text-xs font-medium text-blue-800">
                {{ prompt.category }}
              </span>
            </div>
            <div class="mt-4">
              <div class="flex items-center gap-1 text-yellow-500">
                <star-icon class="h-4 w-4 fill-current" />
                <span class="text-sm font-medium">{{ prompt.rating }}</span>
              </div>
            </div>
            <div class="mt-4 flex justify-between items-center">
              <span class="text-lg font-bold">{{ prompt.price }} ETH</span>
              <button class="bg-primary text-white px-4 py-2 rounded-md text-sm font-medium hover:bg-primary/90">
                Buy Now
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import { ref } from 'vue'
import { StarIcon } from 'lucide-vue-next'

const featuredPrompts = ref([
  {
    id: 1,
    title: "Creative Story Generator",
    description: "Generate engaging short stories with complex characters and plot twists.",
    price: "0.1",
    category: "Creative Writing",
    rating: 4.8,
  },
  {
    id: 2,
    title: "SEO Content Optimizer",
    description: "Create SEO-optimized content that ranks well on search engines.",
    price: "0.08",
    category: "Marketing",
    rating: 4.9,
  },
  {
    id: 3,
    title: "Code Refactoring Assistant",
    description: "Improve your code quality with smart refactoring suggestions.",
    price: "0.15",
    category: "Programming",
    rating: 4.7,
  },
])
</script>
