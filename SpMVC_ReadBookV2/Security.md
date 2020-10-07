# Spring Security

## 개요
* Spring Security 프로젝트에서 지원하는 인증, 인가, 권한 부여를 쉽게 구현할수 있도록 만들어진 Framework
* Spring Framework와 별도의 프로젝트로 진행이 되며, 초기버전에 비해 매우 쉽고 간편하게 변화가 이루어 지고 있다.
* Spring Framework와 연동이 매우 잘 이루어져서 그동안 HttpSession등을 사용하는 Login의 복잡한 과정을 비교적 쉽게 쓸수 있다.

## Security에서 중요한 3가지 단어 개념
1. 접근주체 : 누가(User)
2. 인증 : 누가 로그인을 수행했는가? 정상인가 비정상인가를 판단
3. 인가 : 인증된 사용자가 어떤 페이지에 접근할 수 있도록 허락할 것인가
4. 권한 : 인증되고 인가를 받은 사용자가 어떤 동작을 수행하도록 허락할 것인가

## Spring Security Dependency 설정
* spring-context-support
* spring-security-core
* spring-security-web
* spring-security-config
* spring-security-taglibs

## Spring Security와 별도로 DB 정보(username, password) 암호화
* jasypt, jasypt-spring Dependency

## 양방향 암호화, 단방향 암호화

### 양방향 암호화
* 한가지 도구를 사용하여 평문을 암호화 하고, 다시 암호문을 평문으로 보호화 하는 기능을 포함하는 암호화 기법

### 단방향 암호화
* 평문을 암호화는 할수 있지만, 암호문을 평문으로 복호화 하는 도구를 제공하지 않는 암호화 기법
* 비번을 암호화 하여 저장하였을 경우, 사용자가 입력한 평문을 암호화 하여 저장된 암호문과 비교하여 일치하는지 알아 낸다.


