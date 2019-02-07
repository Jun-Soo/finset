package com.koscom.common.fulltext;

import java.util.List;

/**
 * 전문 처리용 - 전문의 VO 가 상속받을 클래스
 * @author EndFoint 개발팀 김학진
 * @since 2018.08.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2018.08.01 김학진 최초 생성
 *  </pre>
*/
public class FulltextVO <T> {
	protected final String DEFAULT_NEXTKEY = "0000000000000000000000000000000000000000000000000000000000000000";

	protected List<T> gridList;

	public List<T> getGridList() {
		return gridList;
	}

	public void setGridList(List<T> gridList) {
		this.gridList = gridList;
	}

	public int getGridCnt() {
		return (gridList == null ? 0 : gridList.size());
	}
}
