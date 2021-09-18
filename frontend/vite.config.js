import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    port: 5000,
    proxy: {
      '/api': {
        target: 'http://localhost:7000',
        // ws: true,
        changeOrigin: true
      }
    }
  },
  build: {
    outDir: 'build/dist/public'
  }
})
