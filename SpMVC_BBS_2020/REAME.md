# 자유게시판 프로젝트

### 회원가입, 로그인이 필요없는 자유게시판 만들기
* 기본 CRUD 구현
* 페이지네이션
* 파일업로드 : 이미지 겔러리 형식으로
* MySQL, Oracle DB 사용
* 검색기능
* 말머리 기능

### 화면 layout
* tiles 를 활용한 화면 layout

### DBMS
* oracle, mysql, mybatis, dbcp2 를 활용

### 추가기능
* oracle, mysql 의 username, password 암호화 


### context.xml 파일의 위치
* spring framework 프로젝트에서 권장하는 위치는 /WEB-INF/* 이다
* 전자정부 프레임워크등 일부 프로젝트에서 src/main/* 폴더에 *.xml 파일을 위치하는 경우가 있다
* src/main/* 폴더에 *.xml 파일을 위치할경우 web.xml에서 경로 설정은  
classpath:/**/root-context.xml 이라고 사용한다.
* 실제 프로젝트에서 src/main/* 폴더에 *.xml파일을 위치할 경우
* 프로젝트가 시작될때, 프로젝트의 모든 패키지를 검색하는 경우가 있어서 시작 퍼포먼스가 문제가 되는 경우가 있다.
* 하지만 시중 프로젝트에서 상당히 많이 이러한 방식으로 사용하고 있기 때문에 관심을 가질필요는 있다.

* classpath:/*/mycontext.xml >> src/main/*/mycontext.xml 파일을 찾아라
* classpath:/**/mycontext.xml >> src/main/*/mycontext.xml, src/main/*/*/*/mycontext.xml 을 뒤져서 파일을 찾아라

* classpath에 context.xml을 위치시킬 경우 web.xml 의 파일이름에 가급적 와일드카드(*)를 사용하지 말자.
* root-context.xml 을 *-context.xml 로 설정할 경우, root-context.xml 뿐만아니라 servlet-context.xml 파일도 스캔하고 읽어서 설정하는 중간에 프로젝트가 심각한 문제를 일으키는 경우가 있기 때문이다.

### /WEB-INF/ 폴더
* 이 폴더는 tomcat 서버에서 구동되는 어플리케이션에서는 가장 보안이 잘 되는 폴더이다. 이 폴더는 client에서 특별한 경우가 아니면 직접 접근이 불가능하다.
* 따라서 *.xml 파일이나, 중요한 설정 파일들은 다른곳에 위치하지 말고 이곳에 저장하는 것이 좋다.


### tiles 를 이용한 layout
* 최근 프로젝트에서는 많이 사용하지 않지만, 전통적으로 spring framework 프로젝트에서 사용하던 layout 관리 도구
* 전자 전부 프레임워크의 상당부분 프로젝트에서 지금도 많이 활용하고 있다.
* spring framework 프로젝트에서 오랫동안 사용하던 layout 도구이고, 현재는 버전업이 중단되었지만 비교적 사용하기가 간편해서 많이 활용한다.

* layout에 해당하는 jsp를 만들고, 다른 jsp파일을  *.xml 파일에 설정하여 자동으로 layout을 만들어주는 도구이다.

### mybatis configuration
* mybatis의 여러가지 설정을 하기 위해서 mybatis-config.xml 파일을 만들어 사용했었는데
* org.apache.ibatis.session.Configuration 클래스를 bean으로 등록하여 설정을 하고 mybatis-config.xml 파일을 작성하지 않는다

### jdbcTypeForNull 값을 VARCHAR로 설정하기
* 테스트과정에서 칼럼에 값을 포함하지 않은 상태로 insert를 수행하면
* 부적절한 열유형 1111 이라는 오류가 발생하는데, 그 오류를 방지하기 위한 설정
<property name="configuration">
	<bean class="org.apache.ibatis.session.Configuration">
		<property name="jdbcTypeForNull" value="VARCHAR"/>
	</bean>
</property>








