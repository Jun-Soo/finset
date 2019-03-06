package com.koscom.deny.service;

import java.util.List;

import com.koscom.deny.model.DenyForm;
import com.koscom.deny.model.DenyVO;

public interface DenyManager {
	List<DenyVO> listDenyDetail(DenyVO denyVO);
}
