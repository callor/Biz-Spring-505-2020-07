package com.biz.blog.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.blog.model.BlogVO;

@Service
public class BlogFileService {

	/*
	 * SerlvetContext
	 * tomcat 위에서 실행되는 WA(Web App)의 모든 정보를
	 * 담고있는 객체
	 * 이미 Sprint project에서는 ServletContext 클래스의 
	 * 객체 인스턴스가 만들어 있기 때문에 가져다 사용하기 위해서
	 * Autowired로 묶어주기
	 */
	@Autowired
	private ServletContext context;
	
	private String serverRootPath ;
	private String blogFile ; 
	
	public BlogFileService() {
		this.blogFile = "blog.txt";
		// this.serverRootPath = context.getRealPath("/");
	}
	
	public List<BlogVO> selectAll() {
		
		this.serverRootPath = context.getRealPath("/");
		Path path = null;
		try {
		
			System.out.println("서버 rootPath : " 
					+ this.serverRootPath);
			// 서버의 root path와 blog파일 이름을 묶어서
			// 파일관련 연산을 수행할때 사용할 file 객체 생성
			File file = new File(this.serverRootPath,blogFile);
			
			path = Paths.get(file.toString());
			List<String> strList = Files.readAllLines(path);
			List<BlogVO> blogList = new ArrayList<BlogVO>();
			
			// title, content, user
			for(String str : strList) {
				String[] sSplit = str.split(":");
				BlogVO blogVO = new BlogVO();
				blogVO.setBl_title(sSplit[0]);
				blogVO.setBl_contents(sSplit[1]);
				blogVO.setBl_user(sSplit[2]);
				blogList.add(blogVO);
			}
			return blogList;
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * blog글쓰기를 수행하고 저장을 하면
	 * server root Path에 blog.txt라는 파일로 저장하겠다.
	 */
	// java 1.8 이상에서만 정상작동 되는 코드
	public void insert(BlogVO blogVO) {
	
		this.serverRootPath = context.getRealPath("/");
		File file = new File(this.serverRootPath,blogFile);
		
		Path path = null;

		FileWriter fileWriter = null;
		PrintWriter print = null;
		try {

			path = Paths.get(file.toString());
			fileWriter = new FileWriter(path.toString(),true);
			print = new PrintWriter(fileWriter);
			
			String strBlog = String.format("%s:%s:%s", 
					blogVO.getBl_title(),
					blogVO.getBl_contents(),
					blogVO.getBl_user());
			print.println(strBlog);
			print.flush();
			print.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("저장완료");
	}
	
	
}
