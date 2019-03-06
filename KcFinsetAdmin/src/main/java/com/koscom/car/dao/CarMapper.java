package com.koscom.car.dao;

import com.koscom.car.model.CarVO;
import com.koscom.domain.CarInfo;

public interface CarMapper {
	
	/**
	 * 차량담보정보 조회
	 * @param no_prepare
	 * @return
	 */
	CarVO getCarInfo(String no_prepare);
	
	/**
	 * 차량담보 삽입,수정
	 * @param carInfo
	 * @return
	 */
	int procCarInfo(CarInfo carInfo);
	
	/**
	 * 차량담보 삭제
	 * @param carInfo
	 * @return
	 */
	int delCarInfo(CarInfo carInfo);

}
