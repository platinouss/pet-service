<template>
  <footer class="border-t bg-background/95 backdrop-blur supports-[backdrop-filter]:bg-background/60 ml-5">
    <div class="container py-10 ml-20">
      <div class="grid grid-cols-1 gap-8 sm:grid-cols-2 md:grid-cols-4">
        <div>
          <h3 class="text-lg font-semibold mb-4">About</h3>
          <ul class="space-y-2">
            <li>
              <router-link to="/about" class="text-sm text-muted-foreground hover:text-foreground">
                About Us
              </router-link>
            </li>
            <li>
              <router-link to="/team" class="text-sm text-muted-foreground hover:text-foreground">
                Team
              </router-link>
            </li>
            <li>
              <router-link to="/careers" class="text-sm text-muted-foreground hover:text-foreground">
                Careers
              </router-link>
            </li>
          </ul>
        </div>
        <div>
          <h3 class="text-lg font-semibold mb-4">Resources</h3>
          <ul class="space-y-2">
            <li>
              <router-link to="/docs" class="text-sm text-muted-foreground hover:text-foreground">
                Documentation
              </router-link>
            </li>
            <li>
              <router-link to="/blog" class="text-sm text-muted-foreground hover:text-foreground">
                Blog
              </router-link>
            </li>
            <li>
              <router-link to="/support" class="text-sm text-muted-foreground hover:text-foreground">
                Support
              </router-link>
            </li>
          </ul>
        </div>
        <div>
          <h3 class="text-lg font-semibold mb-4">Legal</h3>
          <ul class="space-y-2">
            <li>
              <router-link to="/privacy" class="text-sm text-muted-foreground hover:text-foreground">
                Privacy Policy
              </router-link>
            </li>
            <li>
              <router-link to="/terms" class="text-sm text-muted-foreground hover:text-foreground">
                Terms of Service
              </router-link>
            </li>
          </ul>
        </div>
        <div>
          <h3 class="text-lg font-semibold mb-4">Connect</h3>
          <div class="flex space-x-4">
            <a href="#" class="text-gray-400 hover:text-gray-500">
              <span class="sr-only">Twitter</span>
              <twitter-icon class="h-6 w-6" />
            </a>
            <a href="#" class="text-gray-400 hover:text-gray-500">
              <span class="sr-only">Discord</span>
              <disc-icon class="h-6 w-6" />
            </a>
            <a href="#" class="text-gray-400 hover:text-gray-500">
              <span class="sr-only">GitHub</span>
              <github-icon class="h-6 w-6" />
            </a>
          </div>
        </div>
      </div>
      <div class="mt-10 border-t pt-8 text-center">
        <p class="text-sm text-muted-foreground">
          © {{ new Date().getFullYear() }} platinouss. All rights reserved.
        </p>
      </div>
    </div>
  </footer>
</template>

<script setup>
import { TwitterIcon, DiscIcon, GithubIcon } from 'lucide-vue-next'
</script>
