package com.biz.ems.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan("com.biz.ems.mapper")
@EnableTransactionManagement
public class MybatisConfig {

	// datasource, sqlSesssionFactoryBean, transactionManager(sqlSessionTemplate)
	/*
	 * project, server의 물리적 위치의 파일을 읽거나 기록할때
	 * 위치를 찾는 용도의 클래스
	 */
	@Autowired
	ApplicationContext context;
	
	@Bean
	public DataSource ds() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		ds.setUsername("user1");
		ds.setPassword("user1");
		return ds;
	}
	
	@Bean
	public SqlSessionFactoryBean sessionFactory() {
		
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(this.ds());
		
//		try {
//			bean.setMapperLocations(
//					context.getResources("classpath:/**/mapper/*-mapper.xml"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		};
		
		
		bean.setTypeAliasesPackage("com.biz.ems.model");

		org.apache.ibatis.session.Configuration config 
				= new org.apache.ibatis.session.Configuration();
		config.setJdbcTypeForNull(JdbcType.VARCHAR);
		bean.setConfiguration(config);
		
		return bean;
		
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		
		DataSourceTransactionManager trans = new DataSourceTransactionManager();
		trans.setDataSource(this.ds());
		return trans;
	
	}
	
	
}
