# Spring Project 빛나리 쇼핑몰 V1
* 상품관리, 거래처관리, 회원가입, 로그인을 포함
* DB 오라클
* 반응형 메뉴를 사용, 반응형 메인화면 구현

## Project 시작
* Java Version 1.8 로 변경
* Spring Framework 5.2.8
* lombock, logback Dependency 설정
* views/home.jsp 삭제 후 재 생성
* Run Service 수행하여 home 화면이 나타나도록

## DB 연동 설정(pom.xml)
* spring-jdbc
* mybatis
* mybtis-spring
* commons-dbcp2
* ojdbc6

* spring/appServlet/mybatis-context.xml 파일 생성, 작성

## URL(Uniform Resource Location), URI(Uniform Resource Identifier)
* URL : 파일 식별자
* URI : 통합 자원 식별자

### View 단에서 사용하는 URL
* <a href="http://localhost:8080/shop/">서버 Home</a>  
tomcat WAS에게 shop이라는 Context를 가진 project가 작동되고 있느냐? 라고 묻는 Request
* <a href="http://localhost:8080/shop/product/list">상품리스트</a>  
shop Context의 Dipatcher에게 product/list 를 수행할 수 있는 Controller method가 있느냐 ?  
라고 묻는 Request
* 이 HTML코드를 화면에서 만나면 Hyper Text(anchor 문자열)를 클릭했을때 서버에 Request한다  
이때 수행하는 Request는 method=RequestMethod.GET 설정된 함수에서 처리한다.

* href : Hyper Text Reference, URL 주소라고 생각하면 됨.

### HTML 코드에서 GET method로 Request를 요청하는 곳들
* anchor tag : <a href="주소">텍스트</a>

* script에서 : document.location.href="주소"
* script에서 : document.location.replace("주소")

* css 가져오기 : <link rel="stylesheet" href="주소"/>
* script 가져오기 : <sciprt src="주소"></script>
* 이미지 보이기 : <img src="주소"/>
* 배경이미지 : backgroud-image : url("주소")

***
***

# Spring Project 빛나리 쇼핑몰 V3

## 상품관리 거리처관리 이후에 회원가입, 로그인 구현

### session
* Hyper Text
	* Anchor Tag로 구성된 Text를 Web browser에서 클릭했을때  
	해당하는 단어의 설명하는 새로운 문서가 열리는 구조로된 그러한 문서
	
* HTML(Hyper Text Markup Lang.)
	* Hyper Text 기능을 구현하는데 사용되는 마크업 언어
		
* HTTP(Hyper Text Transfer Protocol)
	* Hyper Text 로 구성된 문서를 Web Browser에서 보여주고  
	사용자가 마우스로 Anchor tag 단어를 클릭하면 그에 따른 다른 문서를  
	연속에서 보여주는 용도로 최적화된 용도로 만들어진 인터넷 프로토콜

* HTTP의 특징 중하는 단방향이고, 비 연결지향
	1. web client에서 서버에 request를 보냈을때만 서버에서 response를 할수있다
	2. 한번 req - res가 이루어지면 그 연결은 끊어진다.
	
* 서버의 어떤 연산을 요청할때 요청하는 정보가 누가나 봐서는 안되는 정보라고 가정  
서버에 연산을 요청할때, "나는 그 정보를 봐도 되는 권한을 가지고 있다"라고 알려줘야 한다
* 이러한 절차를 보통 로그인(Id, Passowrd)이라고 한다.  
* 서버는 client req에서 사용자의 로그인 정보가 포함되어 있으면 그 정보를 확인하여  
* 정상적인 로그인 정보인지를 확인한다 = 인증
* 확인이 되면 요청한 정보를 client에서 response 한다.
* HTTP 는 즉시 client로 부터 받은 모든 정보(ID, Password 포함)를 삭제 해버린다.
* 이후에 또다시 client 같은 정보를 요청을 할 필요가 있을때는 
* 다시한번 ID, password를 request에 같이 실어서 보내야 한다.
* 서버로부터 request해야할 정보가 여러 page에 있을경우 매 Page를 요청할때마다
* ID와 Password를 보내고, 인증 후 response하는 절차를 반복해야 한다.
* 이러한 불편을 해결하기 위해서 HTTPSession 이라고 하는 보조 프로토콜을 만들어 두었다.

## HTTP Session(연결통로가 만들어졌다)
1 client에서 로그인을 시도, ID와 Password 를 먼저 request한다.
2 인증절차 : Server에서 사용자 보낸 ID와 Password를 확인하여 정상적인 정보인지 검사

3 서버는 정상적인 사용자 임이 확인되면  
서버 메모리 어딘가에 HttpSession정보를 저장하기 위해 공간을 마련한다.

4. 이 공간에는 HtppSession규격에 따라 서버가 데이터를 보관한다.
5. 이 공간에는 Session ID라고 하는 식별자(PK)를 만들어둔다.
6. Server가 client Response를 할때   
Response 정보 생성된 SessionID를 같이 부착하여 보낸다.

7. Web Browser는 서버로부터 전달된 Response에 HttpSession  
규격에 해당하는 SessionID가 있으면 Browser의 임시 저장소에 보관한다.
  
8. 이후에 Client가 Server에 Request 하면, Browser는 이 SessionID를  
Request에 같이 부착하여 Server로 보낸다.
9. Server에서 Request를 수신했을때 HttpSession 규격에 맞는 Session ID가 있으면,   
서버가 정상적으로 발행(Response에 부착하여 보낸)한 SessionID 이고, 유효기간이   
정상적 이라면, 이 요청은 "인가된" client로 부터 전달된 것임을 확인하고 이후   
response를 수행해 준다.


	
	
	
	
	
	
	






