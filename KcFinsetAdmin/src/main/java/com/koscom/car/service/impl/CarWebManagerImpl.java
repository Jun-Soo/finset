package com.koscom.car.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koscom.car.model.CarVO;
import com.koscom.car.service.CarManager;
import com.koscom.car.service.CarWebManager;
import com.koscom.domain.CarInfo;
import com.koscom.util.ReturnClass;

@Service("carWebManager")
public class CarWebManagerImpl implements CarWebManager{
	
	@Autowired
	private CarManager carManager;

	@Override
	public CarVO getCarInfo(String no_prepare) {
		return carManager.getCarInfo(no_prepare);
	}

	@Override
	public ReturnClass procCarInfo(CarInfo carInfo) {
		return carManager.procCarInfo(carInfo);
	}

	@Override
	public ReturnClass delCarInfo(CarVO carVO) {
		return carManager.delCarInfo(carVO);
	}

}
