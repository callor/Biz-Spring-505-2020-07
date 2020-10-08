package com.biz.book.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.book.mapper.AuthorityDao;
import com.biz.book.mapper.UserDao;
import com.biz.book.model.AuthorityVO;
import com.biz.book.model.UserDetailsVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("memberServiceV1")

/*
 * MemberServiceImplV1 클래스의 필드변수가 있는 생성자를 자동으로 만들고
 * private final 로 선언된 객체에 Cotainer로 부터 
 * 		객체를 주입받아 사용할수 있도록 만들어주는
 * lombok의 Annotation
 * 
 * MemberServiceImptV1(PasswordEncodeer passwordEncoder) {
 * 		this.passwordEncoder = passwordEncoder;
 * }
 * 
 */
@RequiredArgsConstructor
public class MemberServiceImplV1 implements MemberService{

	@Qualifier("passwordEncoder")
	private final PasswordEncoder passwordEncoder ;
	
	// 회원정보 CRUD 구현
	private final UserDao userDao;
	
	// 회원의 권한(ROLE)정보 CRUD 구현
	private final AuthorityDao authDao;
	
	/*
	 * 컨트로러에서 회원정보를 전달받아서
	 * 비밀번호를 암호화 하고
	 * userDao 보내서 저장하기
	 * 
	 * 사용자의 ROLE정보를 생성하여 AuthorityVO에 담고 저장하기
	 */
	@Transactional
	public int insert(UserDetailsVO userVO) {
		
		String password = userVO.getPassword();
		String encPassword = passwordEncoder.encode(password);
		log.debug("password {} , encPassword {}",password, encPassword);
		
		// 사용자의 권한정보를 설정하기 위해서 authList 객체선언
		List<AuthorityVO> authList = new ArrayList<AuthorityVO>();
		
		/*
		 * 회원테이블에 값이 없을때(0) 회원가입이 이루어지면
		 * 그 회원은 admin 권한을 갖고
		 * enabled 칼럼을 1세팅하여 즉시 로그인이 가능하도록
		 * 자바에서 true로 값을 설정하면 오라클에서는 1로 저장
		 * 
		 * 두번째 가입되는 회원은
		 * enabled 칼럼을 0으로 세팅하여 즉시 로그인이 안되록 설정
		 * 자바에서 false로 값을 설정하면 오라클에서는 0으로 저장된다.
		 * 
		 * mysql 은 true와 false 그대로 저장된다
		 */
		int nCount = userDao.userCount();
		
		// 1. 회원가입한 모든 사용자에게 기본값으로 ROLL_USER 권한을 부여하고
		AuthorityVO authVO = AuthorityVO.builder().username(userVO.getUsername())
				.authority("ROLE_USER").build();
		authList.add(authVO);

		if(nCount > 0) {
			userVO.setEnabled(false); // 0 으로 세팅
		} else {
			userVO.setEnabled(true); // 1로 세팅
			
			// 2. 최초에 저장되는 회원은 ROLL_ADMIN 권한을 추가 부여하여
			// 	다른 사용자의 정보를 확인, 수정할수 있도록 하고
			// 	관리자 페이지를 볼수 있도록 하자.
			authVO  = AuthorityVO.builder()
								.username(userVO.getUsername())
								.authority("ROLE_ADMIN").build();
			authList.add(authVO);
		}
		
		// List 에 담겨 있는 값을 DB insert할때
		// 가장 쉽게 할수 있는 방법이 List를 for 반복문으로 감싸고
		// vo 한개씩을 insert 에 보내는 방법
		// 촌스러운방법이다.
		//for(AuthorityVO vo : authList) {
		//	authDao.insert(vo);	
		//}
		
		authDao.insertAll(authList);
		
		// 평문으로 입력된 비밀번호를 암호화된 비밀번호로 대치
		userVO.setPassword(encPassword);
		
		// userDao의 insert가 수행되기전에 authDao.insert가 수행되서 insert 될것이고
		// userDao insert에 문법오류가 나도록 했기 때문에
		// sql 실행이 중단될 것이다.
		// authDao에는 insert가 되고 userDao는 insert가 안되는 상황이 되었다.
		
		// 이 상황에서 정상적으로 Transaction이 작동된다면
		// authDao insert가 취소되어야 한다.
		
		userDao.insert(userVO);
		return 0;
		
	}

	public String userNameAndPassword(String username, String password) {
		
		UserDetailsVO userVO = userDao.findById(username);

		// passwordEncoder를 사용하여 암호화한 비밀번호는 일방향 비밀번호인까닭에
		// decoder가 존재하지 않는다.
		// 사용자가 입력한 비밀번호와 DB에 저장된 비밀번호를
		// passwordEncoder.matches() method에 매개변수로 전달하면
		// 비밀번호가 일치하는 확인하여 true, false를 리턴한다.
		
		// 결과값에 따라 3항연산자를 사용하여 OK 또는 FAIL 문자열을 Controller로  return
		return passwordEncoder.matches(password, userVO.getPassword()) ? "OK" : "FAIL";
	}
	
	@Override
	public UserDetailsVO findById(String username) {

		UserDetailsVO userVO = userDao.findById(username);
		return userVO;
	
	}

	@Override
	public int update(UserDetailsVO userVO) {
		return userDao.update(userVO);
	}
	
	
}
