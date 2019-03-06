package com.koscom.deny.dao;

import java.util.List;

import com.koscom.deny.model.DenyForm;
import com.koscom.deny.model.DenyVO;

public interface DenyMapper {
	List<DenyVO> listDenyDetail(DenyVO denyVO);
}