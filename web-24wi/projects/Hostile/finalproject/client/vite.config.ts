import { defineConfig } from 'vite'
import { resolve } from 'path'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  build: {
    cssCodeSplit: false,
    rollupOptions: {
      input: {
        main: resolve(__dirname, 'index.html'),
        nested: resolve(__dirname, 'nested/index.html'),
        login: resolve(__dirname, 'login/index.html'),
        landing: resolve(__dirname, 'landing/index.html'),
        profile: resolve(__dirname, 'profile/index.html'),
        post: resolve(__dirname, 'post/index.html'),
        users: resolve(__dirname, 'users/index.html'),
        catchall: resolve(__dirname, 'catchall/index.html'),
        menu: resolve(__dirname, 'menu/index.html'),
        feed: resolve(__dirname, 'feed/index.html'),
      },
    },
  },
  plugins: [react()],
  base: './'
})
