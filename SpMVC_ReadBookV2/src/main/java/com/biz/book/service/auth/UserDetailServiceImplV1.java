package com.biz.book.service.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biz.book.mapper.AuthorityDao;
import com.biz.book.mapper.UserDao;
import com.biz.book.model.AuthorityVO;
import com.biz.book.model.UserDetailsVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * spring security 프로젝트에서 
 * 사용자 인가와 권한을 관리하는 클래스
 * UserDetailService 를 Customizing 
 * 
 * Customizing
 * 패키지형 솔루션을 가지고 있는 it 회사에서
 * 어떤 회사에 솔루션을 판매하면서
 * 회사의 실정, 업무환경, 여러가지 여건들을 요구분석하여
 * 솔루션을 사용하는 회사에 최적화 하는 것
 */
@Slf4j
@RequiredArgsConstructor
@Service("userDetailServiceV1")
public class UserDetailServiceImplV1 implements UserDetailsService {
	
	/*
	 * 그동안 @Autowired를 사용하여 객체를 주입받아서 사용해 왔는데
	 * @Autowired로 주입받은 객체에 메모리 누수현상이 발생을 하더라
	 * 
	 * 주입받을 객체를 final로 선언을 해주는데
	 * final로 선언한 객체는 반드시 생성자에서 객체 초기화(주입)을 해야 한다
	 * 
	 * 1. 주입받을 객체를 final로 선언하고
	 * 2. 생성자의 매개변수를 통하여 객체를 초기화 한다.
	 * 3. 주입받을 객체의 개수가 늘거나 줄면 생성자를 또다시 변경해야하는
	 * 	번거로움이 있다.
	 * 
	 * lombok의 @RequiredArgsConstructor 를 사용하면
	 * final 로 선언된 모든 필드변수들을 모아서 생성자로 만들어준다.
	 * 
	 */
	private final UserDao userDao;
	private final AuthorityDao authDao;

	/*
	public UserDetailServiceImplV1(UserDao userDao, AuthorityDao authDao) {
		this.userDao = userDao;
		this.authDao = authDao;
	}
	*/
	
	/*
	 * 이프로젝트에서 사용할 member(user) 관련 table에서 username으로
	 * 사용자 정보를 SELECT 하고
	 * 사용자의 ROLL 정보를 기준으로 사용자의 권한을 설정하여 
	 * 기능을 수행을 제한하는 설정을 하고
	 * 사용자의 여러 세부 정보를 VO 객체에 담아주는 역할 수행
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 UserDetailsVO userDetail = userDao.findById(username);

		// 테스트를 위한 임시 사용자 정보 생성
		 /*
		userDetail = UserDetailsVO.builder()
						.username(username)
						.password("12341234")
						.enabled(true)
						.build();
		*/
		
		if(userDetail == null) {
			// 강제로 일부러 UsernameNotFoundException을 발생시킨다.
			throw new UsernameNotFoundException(username + " 정보를 찾을 수 없음");
		}
		log.debug("USER:" + userDetail.toString());
		
		/*
		 * 사용자 정보테이블에서 enable 칼럼의 값이 boolean형으로
		 * 설정을 하고
		 * 최초에 회원가입을 했을때 이 값을 false로 세팅을 하고
		 * email 인증과 같은 절차를 통과했을대 이 값을 true로 만들어서
		 * 로그인이 될수 있도록 설정
		 */

		
		if(this.getAuthorities(username).size() == 0) {
			throw new UsernameNotFoundException(
					String.format("[ %s ] 는 아무런 권한이 없습니다",username));
		}
		
		// userDetail.setEnabled(true);
		// DB로부터 가져와 GrantedAuthority로 변환한 ROLE 정보 List UserDetails정보에 저장
		userDetail.setAuthorities(this.getAuthorities(username));
		return userDetail;
		
	}
	
	// tbl_authority로부터 ROLE 정보를 가져와서 List로 생성
	private Collection<GrantedAuthority> getAuthorities(String username) {
		
		// tbl_authority로 부터 ROLE 정보를 SELECT
		List<AuthorityVO> authList = authDao.findByUserName(username);
		
		// |
		// |변환
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(AuthorityVO vo : authList) {
			// 문자열된 ROLE 정보를 GrantedAuthority 타입으로 변환하여
			// 리스트로 생성하기
			// SimpleGrantedAuthority 클래스를 사용하여 변환
			authorities.add(new SimpleGrantedAuthority(vo.getAuthority()));
		}
		return authorities;
	}

}





