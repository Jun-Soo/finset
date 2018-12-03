package com.koscom.batch.person.mapper;

import com.koscom.batch.person.domain.PersonInfo;

/**
 * Person Dao Interface
 *
 * @author bwko
 *
 */
public interface PersonMapper {

	/**
	 * 고객정보 조회
	 *
	 * @param no_person
	 * @return
	 */
	PersonInfo getPersonInfo(String no_person);

}
