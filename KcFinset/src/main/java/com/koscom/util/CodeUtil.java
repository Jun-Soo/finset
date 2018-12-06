package com.koscom.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koscom.env.model.CodeInfo;
import com.koscom.env.service.CodeManager;
import org.springframework.beans.factory.annotation.Autowired;

public final class CodeUtil {

	private static final Logger log = LoggerFactory.getLogger(CodeUtil.class);

	@Autowired
	private CodeManager codeManager;

	private static CodeUtil codeUtil;

	public void setCodeManager(CodeManager codeManager) {
		this.codeManager = codeManager;
	}

	// 생성자가 사용될 수 없도록 봉쇄
	private CodeUtil() {
		init();
	}

	public static synchronized CodeUtil getInstance() {
		if (codeUtil == null) {
			codeUtil = new CodeUtil();
		}
		return codeUtil;
	}

	@SuppressWarnings("resource")
	private void init() {
		log.info("CodeUtil init!");
		codeManager = makeCodeManager();
	}

	private CodeManager makeCodeManager() {
		CodeManager codeManager = (CodeManager) SpringApplicationContext
				.getBean("codeManager");
		return codeManager;
	}

	/**
	 * 
	 * @param codeGroup
	 *            그룹
	 * @param codeValue
	 *            값
	 * @return
	 * @throws Exception
	 */
	public String getCodeName(String codeGroup, String codeValue) {
		return getCodeInfo(codeGroup, codeValue, "NM");
	}

	/**
	 * Method Desc : NVL + CodeName 함수 (codevalue 가 없는경우)
	 * 
	 * @param codeGroup
	 * @param codeValue
	 * @param nullValue
	 * @return
	 * @throws Exception
	 */
	public String getNvlCodeName(String codeGroup, String codeValue,
			String nullValue) {

		if (codeGroup == null || StringUtil.isEmpty(codeGroup)
				|| codeValue == null || StringUtil.isEmpty(codeValue)) {
			return nullValue;
		}

		String returnValue = getCodeInfo(codeGroup, codeValue, "NM");

		// codeValue 와 returnValue 가 같다는 의미는 code name이 null 이란 의미이다
		if (returnValue == null || codeValue.equals(returnValue)) {
			return nullValue;
		}

		return returnValue;
	}

	/**
	 * Method Desc : codeGroup, codeValue,type 에 해당하는 값을 반환합니다.
	 * 
	 * @param codeGroup
	 * @param codeValue
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String getCodeInfo(String codeGroup, String codeValue, String type) {

		String returnValue = "";
		String sType = type;

		if (StringUtil.isEmpty(codeGroup) || StringUtil.isEmpty(codeValue)) {
			returnValue = codeValue;
		} else {
			if (StringUtil.isEmpty(sType))
				sType = "NM";

			CodeInfo ci = null;
			if (codeManager != null) {
				ci = codeManager.getCodeInfo(codeGroup, codeValue);
			}

			if (ci != null) {
				if (sType.equals("ID.NM"))
					returnValue = codeValue + "." + ci.getNm_code();
				else if (sType.equals("ID.ETC"))
					returnValue = codeValue + "." + ci.getEtc();
				else if (sType.equals("ETC"))
					returnValue = ci.getEtc();
				else if (sType.equals("YN_USE"))
					returnValue = ci.getYn_use();
				returnValue = ci.getNm_code();
			} else {
				returnValue = codeValue;
			}
		}
		return returnValue;
	}

	/**
	 * Method Desc : codeGroup, codeValue 에 해당하는 nm_code 를 반환합니다.
	 * 
	 * @param codeGroup
	 * @param codeValue
	 * @return
	 * @throws Exception
	 */
	public CodeInfo getCodeInfo(String codeGroup, String codeValue)
			throws Exception {
		CodeInfo codeInfo = null;
		if (codeManager != null) {
			codeInfo = codeManager.getCodeInfo(codeGroup, codeValue);
		}
		return codeInfo;
	}

	/**
	 * Method Desc : 코드그룹의 코드리스트를 반환합니다.
	 * 
	 * @param codeGroup
	 * @return
	 * @throws Exception
	 */
	public List<CodeInfo> getCodeList(String codeGroup) {

		if (codeGroup != null && StringUtil.isEmpty(codeGroup)) {
			return null;
		}
		List<CodeInfo> result = null;
		if (codeManager != null) {
			result = codeManager.listCodeInfo(codeGroup);
		}
		return result;
	}

	/**
	 * Method Desc : 코드그룹의 ETC를 반환합니다.
	 * 
	 * @param codeGroup
	 * @return
	 * @throws Exception
	 */
	public String getCodeEtc(String codeGroup, String codeValue)
			throws Exception {

		return getCodeInfo(codeGroup, codeValue, "ETC");
	}

	/**
	 * Method Desc : 전달받은 class의 enum코드에서 value와 동일한 코드를 갖는 Code클래스를 반환한다.
	 * 
	 * @param cl
	 *            , value
	 * @return
	 */
	public static <T> CodeInfo findCode(Class<T> cl, String value) {
		CodeInfo[] codes = (CodeInfo[]) cl.getEnumConstants();
		CodeInfo result = null;
		if (codes != null) {
			for (CodeInfo code : codes) {
				if (!code.equals(value))
					continue;
				result = code;
			}
		}
		return result;
	}

	/**
	 * Method Desc : codeGroup, codeName 에 해당하는 codeValue를 반환합니다.
	 * 
	 * @param codeGroup
	 * @param codeName
	 * @return
	 */
	public String getCodeId(String codeGroup, String codeName) {

		return codeManager.getCodeId(codeGroup, codeName);
	}
}
