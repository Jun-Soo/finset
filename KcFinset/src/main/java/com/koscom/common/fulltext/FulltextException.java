package com.koscom.common.fulltext;

/**
 * 전문 처리용 - 예외처리용 모듈
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
public class FulltextException extends RuntimeException {
    /** Serialization ID */
    private static final long serialVersionUID = 0;

    public FulltextException(final String message) {
        super(message);
    }

    public FulltextException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public FulltextException(final Throwable cause) {
        super(cause.getMessage(), cause);
    }

}
