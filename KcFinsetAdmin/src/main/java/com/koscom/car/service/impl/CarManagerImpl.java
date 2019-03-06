package com.koscom.car.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.car.dao.CarMapper;
import com.koscom.car.model.CarVO;
import com.koscom.car.service.CarManager;
import com.koscom.domain.CarInfo;
import com.koscom.util.Constant;
import com.koscom.util.ReturnClass;

@Service("carManager")
public class CarManagerImpl implements CarManager{
	
	private static final Logger logger = LoggerFactory.getLogger(CarManagerImpl.class);
	
	@Autowired
	private CarMapper carMapper;

	@Override
	public CarVO getCarInfo(String no_prepare) {
		return carMapper.getCarInfo(no_prepare);
	}
	
	@Override
	public ReturnClass procCarInfo(CarInfo carInfo) {
		if (1 != carMapper.procCarInfo(carInfo)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

	@Override
	public ReturnClass delCarInfo(CarVO carVO) {
		if (1 != carMapper.delCarInfo(carVO)) {
			return new ReturnClass(Constant.FAILED, "처리에 실패하였습니다.");
		}

		return new ReturnClass(Constant.SUCCESS, "정상 처리하였습니다.");
	}

}
