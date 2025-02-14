<template>
  <header class="sticky top-0 z-50 w-full border-b bg-white supports-[backdrop-filter]:bg-background/60 justify-center">
    <div class="container flex h-16 items-center justify-between md:justify-center">
      <div class="ml-20 hidden md:flex items-center space-x-6">
        <router-link to="/" class="mr-6 flex items-center space-x-2">
          <span class="hidden font-bold sm:inline-block">Pet Service</span>
        </router-link>
        <nav class="flex items-center space-x-6 text-sm font-medium">
          <router-link to="/pet-sitter" class="transition-colors hover:text-foreground/80 text-foreground">
            펫시터 서비스
          </router-link>
          <router-link to="/sell" class="transition-colors hover:text-foreground/80 text-foreground">
            펫시터 등록
          </router-link>
        </nav>
      </div>
      <button @click="isMenuOpen = true" class="mr-2 px-0 text-base hover:bg-transparent focus-visible:bg-transparent focus-visible:ring-0 focus-visible:ring-offset-0 md:hidden">
        <Menu class="h-6 w-6" />
        <span class="sr-only">Toggle Menu</span>
      </button>
      <Teleport to="body">
        <div v-if="isMenuOpen" class="fixed inset-0 z-50 bg-background/80 backdrop-blur-sm" @click="isMenuOpen = false">
          <div class="fixed inset-y-0 left-0 w-full max-w-xs bg-background p-6 shadow-lg" @click.stop>
            <button @click="isMenuOpen = false" class="absolute right-4 top-4 text-foreground/50 hover:text-foreground">
              <X class="h-6 w-6" />
            </button>
            <nav class="grid gap-6 py-6">
              <router-link to="/browse" class="hover:text-foreground/80" @click="isMenuOpen = false">Browse</router-link>
              <router-link to="/sell" class="hover:text-foreground/80" @click="isMenuOpen = false">Sell</router-link>
              <router-link to="/governance" class="hover:text-foreground/80" @click="isMenuOpen = false">Governance</router-link>
              <router-link to="/profile" class="hover:text-foreground/80" @click="isMenuOpen = false">Profile</router-link>
            </nav>
          </div>
        </div>
      </Teleport>
      <div class="flex flex-1 items-center justify-between space-x-2 md:justify-end">
        <div v-if="isConnected" class="flex items-center space-x-2">
          <div class="h-8 w-8 rounded-full bg-gray-300"></div>
          <div class="relative">
            <button @click="isDropdownOpen = !isDropdownOpen" class="ml-auto hidden md:flex items-center space-x-1 bg-white px-3 py-2 rounded-md border">
              <span>My Account</span>
              <ChevronDown class="h-4 w-4" />
            </button>
            <div v-if="isDropdownOpen" class="absolute right-0 mt-2 w-48 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5">
              <div class="py-1" role="menu" aria-orientation="vertical" aria-labelledby="options-menu">
                <router-link to="/profile" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100" role="menuitem">My Page</router-link>
                <a @click="logout" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100" role="menuitem">Logout</a>
              </div>
            </div>
          </div>
        </div>
        <div class="ml-auto hidden md:flex items-center space-x-1 bg-white px-3 py-2 rounded-md border">
          <router-link to="/signin">Login</router-link>
        </div>
      </div>
    </div>
  </header>
</template>

<script setup>
import { ref } from 'vue'
import { Menu, X, ChevronDown, UserPlus } from 'lucide-vue-next'

const isConnected = ref(false)
const isMenuOpen = ref(false)
const isDropdownOpen = ref(false)

const login = () => {
  isConnected.value = true
}

const logout = () => {
  isConnected.value = false
  isDropdownOpen.value = false
}
</script>
