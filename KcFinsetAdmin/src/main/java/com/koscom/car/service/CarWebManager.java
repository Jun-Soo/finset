package com.koscom.car.service;

import com.koscom.car.model.CarVO;
import com.koscom.domain.CarInfo;
import com.koscom.util.ReturnClass;

public interface CarWebManager {
	
	/**
	 * 차량담보정보 조회
	 * @param no_prepare
	 * @return
	 */
	CarVO getCarInfo(String no_prepare);
	
	/**
	 * 차량담보 삽입, 수정
	 * @param carVO
	 * @return
	 */
	ReturnClass procCarInfo(CarInfo carInfo);

	/**
	 * 차량담보 삭제
	 * @param carVO
	 * @return
	 */
	ReturnClass delCarInfo(CarVO carVO);


}
