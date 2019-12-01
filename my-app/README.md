# my-app

> A Vue.js project

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report
```

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).

<br>

# 1. 사전 지식
## 1.1 node
Node.js®는 Chrome V8 JavaScript 엔진으로 빌드된 JavaScript 런타임입니다.
Node.js는 확장성 있는 네트워크 애플리케이션(특히 서버 사이드) 개발에 사용되는 소프트웨어 플랫폼이다. 

## 1.2 npm
npm 은 자바스크립트 프로그래밍 언어를 위한 패키지 관리자이다. 
자바스크립트 런타임 환경 Node.js의 기본 패키지 관리자이다.

## 1.3 자바스크립트 패키지 매니저 npm vs yarn
npm 이 다운로드 수에서 다른 경쟁자를 압도

[참고 사이트](https://medium.com/@ehddnjs8989/npm-vs-yarn-3a611c89d291)

## 1.4 번들러와 빌드 도구 webpack vs gulp
webpack이 다른 도구에 비해 압도적인 위치에 있다.

[참고 사이트](https://d2.naver.com/helloworld/5644368)

## 1.5 node 모듈
모듈이란 독립된 기능을 갖는 것(함수, 파일)들의 모임
### 1.5.1 외장 모듈
일반 Node.js 개발자들이 만들어 놓은 모듈(라이브러리)입니다.
### 1.5.2 내장 모듈
Node.js를 설치하고 나면 그 안에 이미 제공되어지는 모듈을 의미합니다.
### 1.5.3 모듈 정의하기(ES2015)
Example.js
```javascript
var state = {
	count: 1
}

// 디폴트의 임포트 구문으로 호출할 때 리턴할 데이터
export default state
```

### 1.5.3 모듈 사용하기(ES2015)
```javascript
// 디폴트 임포트 구문
import Example from './Example.js'
// Example 모듈의 데이터에 접근할 수 있음
console.log(Example.count) // -> 1
```

## 1.6 vuejs 기본
[vuejs 한글 가이드 사이트](https://kr.vuejs.org/v2/guide/index.html)

## 1.7 vue-router
* vue router 는 url 경로마다 띄워줄 component 들을 설정하는 것입니다. 
* vue-router 를 이용하면 SPA(Single Page Application) 을 구현할 때 마치 여러 페이지를 구현한 것 같은 효과를 낼 수 있습니다. 
* vue router 의 기본 경로는 '기본_도메인/#/' 입니다. 기본 경로에 #을 없애고 싶으시다면 router 의 mode 를 history 로 설정하시면 됩니다. 
### 1.7.1 vue-router 설치
```sh
$ npm install vue-router
```
### 1.7.2 vue-router 사용
index.js
```javascript
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    }
  ]
})
```
App.vue
```html
<template>
  <div id="app">
    <!-- <img src="./assets/logo.png"> -->
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>
```

## 1.8 vuejs 라이프사이클 시점
Vue 인스턴스는 크게 생성(create)되고, DOM에 부착(mount)되고, 
업데이트(update)되며, 없어지는(destroy) 4가지 과정을 거치게 됩니다.

### 18.1 라이프사이클 훅
* beforeCreate : 인스턴스가 생성되고, 리액티브 초기화가 일어나기 전
* created : 인스턴스가 생성되고, 리액티브 초기화가 일어난 후
* beforeMount : 인스턴스가 마운트되기 전
* mounted : 인스턴스가 마운트된 후
* beforeUpdate : 데이터가 변경되어 DOM에 적용되기 전
* updated : 데이터가 변경되어 DOM에 적용된 후
* beforeDestory : Vue 인스턴스가 제거되기 전
* destroyed : Vue 인스턴스가 제거된 후
* errorCaptured : 임의의 자식 컨포넌트에서 오류가 발생했을 때

<br>

# 2. vuejs 사용을 위한 환경 설정
## 2.1 node (npm 포함) 설치
* Downloads : Latest LTS Version (https://nodejs.org/ko/download/)
* Node.js v12.13.1 to /usr/local/bin/node
* npm v6.12.1 to /usr/local/bin/npm

## 2.2 전역 위치에 Vue CLI 설치하기
vue-cli 는 뷰 프로젝트를 생성하기 위한 명령어 도구이다.
```sh
$ npm install -g vue-cli
```

### 2.2.1 permission denied error 발생시 아래 명령으로 설치
```sh
$ sudo npm install -g @vue/cli --unsafe-perm
```

### 2.2.2 의존성 오류 발생시 아래 명령으로 cli-init 설치 후 vue-cli 설치
```sh
$ sudo npm install -g @vue/cli-init
```

## 2.3 vue-cli 를 이용하여 프로젝트 생성(webpack 템플릿 사용)
```sh
$ vue init webpack my-app
```

## 2.4 프로젝트 실행
```sh
$npm start OR $npm run dev
```

## 2.5 axios 로컬 설치
axios는 Promise 기반 HTTP 클라언트이다.
### 2.5.1 설치 명령
```sh
$ npm install axios --save
```

### 2.5.2 ‘src/main.js’에 다음과 같이 axios 사용할 수 있도록 설정
```javascript
import axios from 'axios'
Vue.prototype.$http = axios
```


참고사항)
```
axios error
has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.
Server-side 설정
해당 API Controller Method 에 @CrossOrigin(origins = "http://localhost:8081") 추가
```
<br>

# 3. vue-cli 를 이용하여 생성한 vuejs 프로젝트 구조
- build/ : webpack 빌드 관련 설정이 모여 있는 디렉토리입니다.
- config/ : 프로젝트에서 사용되는 설정이 모여 있는 디렉토리입니다.
    - dev.env.js : npm start 시 적용되는 설정입니다.
    - prod.env.js : npm run build 로 배포 버전에 적용되는 설정입니다.
- dist/ : 배포버전의 Vue 어플리케이션 파일들이 모여 있는 디렉토리입니다.npm run build 명령어 실행시 생성됩니다.
- node_modules/ : npm으로 설치되는 서드파트 라이브러리들이 모여 있는 디렉토리입니다.
- src/ : 실제 대부분의 코딩이 이루어지는 디렉토리입니다.
    - assets/ : 이미지, 포트 등 어플리케이션에서 사용되는 파일들이 모여 있는 디렉토리입니다.
    - components/ : Vue 컴포넌트들이 모여 있는 디렉토리입니다.
    - router/ : vue-router 설정을 하는 디렉토리입니다.
    - App.vue : 가장 최상위 컴포넌트입니다.
    - main.js : 가장 먼저 실행되는 javascript 파일입니다. Vue 인스턴스를 생성하는 역활을 합니다.
- index.html : 어플리케이션의 뼈대가 되는 html 파일입니다.