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



