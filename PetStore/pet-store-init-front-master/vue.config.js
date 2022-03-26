const vueConfig = {
    devServer: {
        // development server port 8000
        port: 8020,
        // If you want to turn on the proxy, please remove the mockjs /src/main.jsL11
        proxy: {
            '/api': {
                //target: 'http://383a25k854.qicp.vip/',
                //target: 'http://cxzme.com:58081/',
                target: 'http://localhost:8080/',
                changeOrigin: true,
                pathRewrite: {
                    '^/api': '/'
                }
            }
        }
    }
}
module.exports = vueConfig