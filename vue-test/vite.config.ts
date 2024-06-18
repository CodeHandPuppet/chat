import { ConfigEnv, UserConfigExport, defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path';

// https://vitejs.dev/config/

export default defineConfig(({ command, mode }: ConfigEnv) => {

  const env = loadEnv(mode, process.cwd(), '')

  return {
    plugins: [vue(),
      // viteMockServe({
      //   localEnabled: command == 'serve',
      // }),
    ],
    css: {
      preprocessorOptions: {
        scss: {
          additionalData: `@import "./src/assets/sass/variables.scss";`
        }
      }
    },
    resolve: {
      alias: {
        '@': path.resolve(__dirname, './src'),
      },
    },
    server: {
      open: true,
      // proxy: {
      //   '/api': {
      //     target: 'http://localhost:8080/ssmchat', // 设置目标服务器地址
      //     changeOrigin: true,
      //     rewrite: (path) => path.replace(/^\/api/, ''),
      //   },
      // },
    },
  }
})