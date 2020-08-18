# Oracle, MyBatis 연동 Blog
### DB Table 을 사용하여 CRUD 모두 구현하기

### Oracle, MyBatis 연동을 위한 Dependency 설정
* spring-jdbc
* mybatis
* mybatis-spring
* ojdbc
* commons-dbcp2

### SQL Injection 공격
* tbl_blogs table의 PK 값은 NUMBER형으로 숫자데이터 타입이다
* http://localhost:8080/blog/view?seq=22 형식으로 사용하도록 코드를 만들었는데  
여기에 seq=22 or 1=1 이라는 형식의 문자열로 query를 보냈다
1=1은 모든경우가 true 이므로 어떠한 형식의 조건문이 오더라도 상관없이 모든 것이  
true이다. 이러한 query를 보내서 만약 DELETE명령을 수행한다면  
모든 데이터가 삭제되는 명령이 될 것이다.  
* 현재 사용되는 MyBatis, JDBC에서는 이러한 쿼리문이 오면 오류가 나도록 설계가 되어 있다  
* 하지만 과거버전의 JDBC에서는 검증 절차없이 명령이 수행되는 경우도 있었다.
* 이러한 헛점을 노리는 공격을 SQL 삽입공격(SQL Injection 공격)이라고 한다.

### 블로그글 수정하기
1. view에서 수정을 클릭하면
2. seq에 해당하는 글을 SELECT 수행
3. write 화면에서 해당하는 글의 내용을 보여주고
4. 내용을 변경한 후 저장을 클릭하면
5. update 를 호출하여 데이터 변경

### write.jsp 파일 한개로 insert, update 모두 사용하기
* form tag에 action은 데이터를 저장하기 위한 Controller의  Mapper 인데
* action을 삭제하면 form화면을 호출할때 사용한 request Mapping이 자동으로 부여된다.
* input GET로 write.jsp를 열고 저장을 하면 input POST로 Controller에 데이터가 전송되고
* update GET로 write.jsp를 열고 저장을 하면 update POST로 Controller에 데이터가 전송된다.












