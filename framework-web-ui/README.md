# 沂蒙精神官网

### 启动方式
调试启动
``` Shell
npm run dev
```

部署启动
``` Shell
npm run prd
# 或者
pm2 start bin/www
```


### 工具使用
- web框架 [koa2 中文文档](https://www.koajs.com.cn/)
- html模板 [pug 中文文档](https://pug.bootcss.com/language/inheritance.html)
- 页面路由 [koa-router github](https://github.com/ZijianHe/koa-router)
- 调试输出 [debug github](https://github.com/visionmedia/debug)
- 接口请求 [axios github](https://github.com/axios/axios)
,[中文文档](http://www.axios-js.com/)
#### 中间件
- less koa-less
### 目录结构 （cnpm install mddir -g）
``` 
|-- .gitignore
|-- README.md
|-- app.js
|-- package.json
|-- .vscode
|   |-- launch.json
|-- bin
|   |-- www
|-- public
|   |-- images
|   |-- javascripts
|   |-- stylesheets
|       |-- style.css
|-- routes
|   |-- index.js
|   |-- users.js
|-- views
    |-- activity.pug
    |-- activityDetail.pug
    |-- changeMobile.pug
    |-- changePassword.pug
    |-- error.pug
    |-- forgetPassword.pug
    |-- index.pug
    |-- login.pug
    |-- register.pug
    |-- result.pug
    |-- users.pug
    |-- layout
        |-- index.pug

```