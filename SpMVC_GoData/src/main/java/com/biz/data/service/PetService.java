package com.biz.data.service;

import java.util.List;

import com.biz.data.config.model.pet.GoPetVO;

public interface PetService {
	public List<GoPetVO> getHosp(String cat, String hosp);
}
