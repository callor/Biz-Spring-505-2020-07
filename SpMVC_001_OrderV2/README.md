## JSP의 확장기능
* 전통적인 JSP(1.x) 는 HTML 코드내에 java코드를 삽입하여 연산을 수행하거나, Controller로 부터 보내진 변수를 표현하는 방법을 사용했다.
* 이 방법은 HTML 코드에 java 코드를 삽입하는 과정에서 디자인이 무너지는 현상이 자주 발생을 하고,  그렇지 않아도 복잡한 HTML 코드를 더 복잡하게 만든다.
* 디자이너와 java 개발자가 협업을 할때 트러블을 일으키는 대표적인 상황이 연출된다.

## EL(expression Lang.) Tag 와 JSTL(JSP Standard tag Lib)
* JSP 2.x 규격이 나오면서 HTML 코드에 java 코드를 삽입하지 않아도 더 유연하게 view를 만들수 있는 확장기능이 탄생을 한다.
* JSP에 include file 등 일부 코드만 최소한으로 사용하면서 더 나은 View 를 만드는 방법이다.

### EL(expression : 계산식, Lang. )
* EL tag : ${변수}, ${1200 * 25}
* Controller로 부터 model에 담겨서 전달된 변수값을 문자열로 변환하여 표시하거나
* 계산식을 이용하여 결과를 문자열로 변환하여 표시하는 용도
* JSP 1.x : <%= pageContext.getAttribute("ORDER") %>
* EL : ${ORDER}

### JSTL(JSP Standard Tag Lib.)
* JSP 2.x 규격이 사용된 뒤로, 더이상 JSP 표준코드를 사용하지 말자는 움직임이 나오고 다양한 기법을 동원하여 최소한으로 JSP 표준코드를 사용하는 방법이 적용되었다.
* 그러나 EL tag만으로는 JSP표준코드를 100% 대체하기가 어렵다.
* 그렇다고 EL tag를 마냥 확장할수만도 없다.
* 그래서 필요할 경우 JSP 코드에 "확장된 기능을 추가하여" 사용할 수 있는 표준이 만들어진다. 
* JSP의 규격이 더이상 변동이 없어도 다양한 확장을 할수 있게 된다.    
    
  
* JSP 1.x 코드

	<%
	for(int i = 0 ; i < 10 ; i++) {
	%>
	<h3>blabla</h3>
	<%
	}
	%>

* JSTL

	<c:forEach begin="1" end="10" varStatus="index">
	<h3>blablabla</h3>
	</c:forEach>

### Spring project에서 exception이 발생했을때
* 원칙 1 : exception 블럭을 제일 아래쪽부터 확인하라
* EL tag를 사용한 JSP : 보통 객체.필드변수 형식의 EL tag에서 필드변수가 없을때 발생하는 대표적인 Exception

* project가 시작되면서 발생하는 오류중  
NullpointerException : @Autowired가 빠졌을때
No qualifying .. : @Service, Annotation이 빠진경우, Dao를 만들지 못하는 경우

### 사용자가 서버에 데이터를 보내기
1. 고정값을 보내는 방법 : a href="주소?변수=값" 형식으로 링크를 만드는 방법  
이방법은 값을 변경하려면 소스코드를 변경해야 하는 문제가 있다.
2. 사용자가 키보드로 입력한 값을 보내는 방법
* input tag를 사용하여 입력box를 보여주고
* 값을 입력하면 서버로 한꺼번에 전송하는 방법
* input tag 들은 form tag로 감싸서 전송을 하게 된다.

	<form>
		<input>
		<input>
		<button>보내기</button>
	</form>

* 이러한 방법을 사용하면 query 문자열을 ?변수1=값&변수2=값 과같이 만들지 않아도 자동으로 query 문자열을 생성하여 서버로 전송을 해준다
* form tag는 서버의 req 주소를 지정해서 보내야 야 한다. : action을 작성
* form tag에 method라는 속성을 지정할수 있는데 기본값이 GET 이다.

#### form tag의 method
* GET, POST를 주로 사용하고, 최신기술에 PUT, DELETE, HEAD 등이 있다
* GET method 방식 : input box에 입력된 데이터를 query 문자열로 변환하여 서버로 전송   
보내는 데이터가 주소창에 노출이되고, 누군가 조작하기가 쉽다.  
GET method 방식은 query문자열의 최대 길이가 255자이며 그보다 큰 문자열은 잘리게 된다

* POST method 방식 : HTTP 프로토콜의 BODY 영역에 데이터를 심어서 서버로 전송을 한다  
문자열방식으로 전송되지 않기때문에 GET방식에 비해 보안이 좋으며 데이터 길이에 제한이 없다  
일반적으로 form, input을 사용한 입력값 전송에는 POST method방식을 사용한다.









