package com.biz.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.ems.mapper.EmsDao;
import com.biz.ems.model.EmsVO;

@Service("emsServiceV1")
public class EmsServiceImpl implements EmsService {
	
	@Autowired
	private EmsDao emsDao;

	@Override
	public List<EmsVO> selectAll() {
		List<EmsVO> emsList = emsDao.selectAll();
		return emsList;
	}

	@Override
	public EmsVO findById(Long id) {
		
		EmsVO emsVO = emsDao.findById(id);
		return emsVO;
	}

	@Override
	public int insert(EmsVO emsVO) {

		int ret = emsDao.insert(emsVO);
		return ret;
		
	}

	@Override
	public int update(EmsVO emsVO) {
		return emsDao.update(emsVO);
	}

	@Override
	public int delete(Long id) {
		int ret = emsDao.delete(id);
		return ret;
	}

}



