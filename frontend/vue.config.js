module.exports = {
    outputDir: 'build/dist/public',
    devServer: {
        port: 3000,
        proxy: {
            '/api': {
                target: 'http://localhost:8082',
                ws: true,
                changeOrigin: true
            }
        }
    }
}