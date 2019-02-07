package com.koscom.common.fulltext;

import java.lang.reflect.Method;
import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.koscom.common.fulltext.FulltextField.FieldType;
import com.koscom.common.util.ByteArray;

/**
 * 전문 처리용 - 전문 생성 및 파싱용 클래스
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
@Configuration
@PropertySource("classpath:prop/apiconfig.properties")
public class FulltextService implements EnvironmentAware{
	private static final Logger logger = LoggerFactory.getLogger(FulltextService.class);
	private static final int gridSzByte = 5;
	private static final String token = "Fulltext[";
	protected static final String defaultCodeSetName = "euc-kr";
	
	@Resource
	Environment environment;
	
	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
		
	}
	
   /* @Value("${rest.charset.forName}")
    protected transient String codeSetName;*/

	private String doubleToString(final double value) {
		String str = String.format("%f", value);
		byte[] num = str.getBytes();
		int lastIndex = num.length;
		for(int n=num.length-1; n>=0; n--) {
			if(num[n] == '.') {
				lastIndex = n-1;
				break;
			} else if(num[n] != '0') {
				lastIndex = n;
				break;
			}
		}
		return str.substring(0, lastIndex+1);
	}

	private static String numberToString(final String data) {
		String value = data.trim();
		String num = "";
		
		if ("".equals(value)) {
			return "";
		}
		
		for (int n = 0; n < value.length(); n++) {
			if (!"0".equals(value.substring(n, n + 1))) {
				if (".".equals(value.substring(n, n + 1))) {
					num = value.substring(n - 1, value.length());
					break;
				} else {
					num = value.substring(n, value.length());
					break;
				}
			}
		}
		
		if ("".equals(num)) {
			if (value.substring(0, 1).equals("0")) {
				num = "0";
			} else {
				num = value;
			}
		}
		
		return num;
	}

	private String RightPad(final String value, int size) {
		StringBuilder sb = new StringBuilder(value == null ? "" : value);
		for (int n = sb.length(); n < size; n++) {
			sb.append(' ');
		}
		return sb.toString();
	}

	private String jsonMemberName(final String name) {
		return name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
	}

	public String CreateFulltext(final String title, final FulltextVO<?> vo, FulltextField [] fields, FulltextField [] gridFields) throws FulltextException
	{
		String codeSetName = defaultCodeSetName;
		Charset codeSet = Charset.forName(codeSetName == null ? defaultCodeSetName : codeSetName);
		StringBuffer fulltext = new StringBuffer();

		for (int n = 0; n < fields.length; n++) {
			if (fields[n].getName().equals("GridCnt")) {
				if(fields[n+1].getName().equals("GridSz")) {
					int gridCnt = vo.getGridCnt();
					int gridCntByteSize = String.valueOf(gridCnt).length();
					int byteSize = fields[n].getFieldSize();
					
					if (byteSize >= gridCntByteSize) {
						logger.debug("RightPad(String.valueOf(GridCnt), byteSize) ["+byteSize+"]: [" + RightPad(String.valueOf(gridCnt), byteSize).length() + "]");
						fulltext.append(RightPad(String.valueOf(gridCnt), byteSize));
					} else if (byteSize < gridCntByteSize) {
						String errorMessage = FulltextService.token+title+":"+fields[n].getNameKr()+"]"
								+"\n전문의 그리드사이즈 보다 큰 값이 입력되었습니다."
								+"\nMAX("+fields[n].getFieldSize()+") : ["+gridCnt+"]";
						logger.error(errorMessage);
						throw new FulltextException(errorMessage);
					}
					logger.debug("RightPad(String.valueOf(fields[n+1].getFieldSize()), FulltextService.gridSzByte) ["+FulltextService.gridSzByte+"]: [" + RightPad(String.valueOf(fields[n+1].getFieldSize()), FulltextService.gridSzByte).length() + "]");
					fulltext.append(RightPad(String.valueOf(fields[n+1].getFieldSize()), FulltextService.gridSzByte));
					
					for (int gridIndex = 0; gridIndex < gridCnt; gridIndex++) {
						fulltext.append(CreateGridFulltext(title, vo.getGridList().get(gridIndex), gridFields));
					}
					n++;
				} else {
					String errorMessage = FulltextService.token+title+":"+fields[n].getNameKr()+"]"
							+ "\n'그리드 카운트' 다음에 '그리드 사이즈' 컬럼이 위치하여야 합니다.";
					logger.error(errorMessage);
					throw new FulltextException(errorMessage);				
				}
			} else {
				String data = "";
				
				try {
					Method method = vo.getClass().getMethod("get"+fields[n].getName());
					if(fields[n].getFieldType() == FieldType.CHAR) {
						data = method.invoke(vo).toString();
					} else if (fields[n].getFieldType() == FieldType.FLOAT) {
						double val = Double.parseDouble(method.invoke(vo).toString());

						data = doubleToString(val);
					} else {
						//int val = (int) method.invoke(vo);
						long val = Long.parseLong(method.invoke(vo).toString());
					
						data = String.valueOf(val);
					}
				} catch (NoSuchMethodException e) {
					String errorMessage = FulltextService.token+title+":"+fields[n].getNameKr()+"]"
							+ "\n전문이름을 확인할 수 없습니다.";
					logger.error(errorMessage);
					throw new FulltextException(errorMessage);
				} catch (Exception e) {
					logger.error("n : {}", n);
					logger.error("field name : {}", fields[n].getName());
					logger.error("getNameKr name : {}", fields[n].getNameKr());
					
					e.printStackTrace();
					// TODO: handle exception 추가적인 예외가 있는지 파악이 필요함
				}
				
				fulltext.append(data);
				int byteSize = data.getBytes(codeSet).length;
				
				if (byteSize < fields[n].getFieldSize()) {
					for (int m = 0; m < fields[n].getFieldSize() - byteSize; m++) {
						fulltext.append(' ');
					}
				} else if (byteSize > fields[n].getFieldSize()) {
					String errorMessage = FulltextService.token+title+":"+fields[n].getNameKr()+"]"
							+ "\n전문의 크기보다 큰 값이 입력되었습니다."
							+ "\nMAX("+fields[n].getFieldSize()+") : ["+data+"]";
					logger.error(errorMessage);
					throw new FulltextException(errorMessage);				
				}
			}
		}		
		return fulltext.toString();
	}
	
	
	private String CreateGridFulltext(final String title, final Object vo, FulltextField [] fields) throws FulltextException {
		String codeSetName = defaultCodeSetName;
		Charset codeSet = Charset.forName(codeSetName == null ? defaultCodeSetName : codeSetName);
		StringBuffer fulltext = new StringBuffer();
		
		for (int n = 0; n < fields.length; n++) {
			String data = "";
			try {
				Method method = vo.getClass().getMethod("get" + fields[n].getName());
				
				if (fields[n].getFieldType() == FieldType.CHAR) {
					data = method.invoke(vo).toString();
				} else if (fields[n].getFieldType() == FieldType.FLOAT) {
					// double val = (double) method.invoke(vo);
					double val = Double.parseDouble(method.invoke(vo).toString());

					data = doubleToString(val);
				} else {
					// int val = (int) method.invoke(vo);
					long val = Long.parseLong(method.invoke(vo).toString());

					data = String.valueOf(val);
				}
			} catch (NoSuchMethodException e) {
				String errorMessage = FulltextService.token+title+":"+fields[n].getNameKr()+"]"
						+ "\n전문이름을 확인할 수 없습니다.";
				logger.error(errorMessage);
				throw new FulltextException(errorMessage);				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception 추가적인 예외가 있는지 파악이 필요함
			}

			fulltext.append(data);
			
			int byteSize = data.getBytes(codeSet).length;

			if (byteSize < fields[n].getFieldSize()) {
				for (int m = 0; m < fields[n].getFieldSize() - byteSize; m++) {
					fulltext.append(' ');
				}
			} else if (byteSize > fields[n].getFieldSize()) {
				String errorMessage = FulltextService.token+title+":"+fields[n].getNameKr()+"]"
						+ "\n전문의 크기보다 큰 값이 입력되었습니다."
						+ "\nMAX("+fields[n].getFieldSize()+") : ["+data+"]";
				logger.error(errorMessage);
				throw new FulltextException(errorMessage);				
			}
			
		}
		return fulltext.toString();
	}
	
	private String CreateGridOutJson(String fulltext, FulltextField [] gridFields) throws FulltextException {
		String codeSetName = defaultCodeSetName;
		Charset codeSet = Charset.forName(codeSetName == null ? defaultCodeSetName : codeSetName);
		StringBuffer jsonBuffer = new StringBuffer();
		//byte[] byteText = fulltext.getBytes(codeSet);
		ByteArray byteText = new ByteArray(fulltext.getBytes(codeSet));
		int pos = 0;
		String data = "";
		jsonBuffer.append('{');
		for(int n=0; n<gridFields.length; n++) 
		{
			//data = new String(byteText, pos, gridFields[n].getFieldSize(), codeSet);
			data = byteText.toString(pos, gridFields[n].getFieldSize(), codeSet);
			
			pos += gridFields[n].getFieldSize();
			if(n>0) 
				jsonBuffer.append(',');
			jsonBuffer.append("\"" + jsonMemberName(gridFields[n].getName()) + "\":");
			if(gridFields[n].getFieldType() == FieldType.CHAR) {
				jsonBuffer.append("\"" + data.trim() + "\"");
			} else {
				jsonBuffer.append(numberToString(data.trim()));
			}
		}
		jsonBuffer.append('}');
		return jsonBuffer.toString();
	}
	
	public String CreateOutJson(final String title, String fulltext, final FulltextField [] fields, final FulltextField [] gridFields) {
		String codeSetName = defaultCodeSetName;
		Charset codeSet = Charset.forName(codeSetName == null ? defaultCodeSetName : codeSetName);
		StringBuffer jsonBuffer = new StringBuffer();
		//byte[] byteText = fulltext.getBytes(codeSet);
		ByteArray byteText = new ByteArray(fulltext.getBytes(codeSet));
		logger.info(new String(fulltext.getBytes(codeSet)));
		String data = "";
		
		int pos = 0;
		jsonBuffer.append('{');
		for(int n=0; n<fields.length; n++) {
			//data = new String(byteText, pos, fields[n].getFieldSize(), codeSet);
			logger.info(Integer.toString(pos) +" / "+ fields[n].getFieldSize()+" / "+ codeSet);
			data = byteText.toString(pos, fields[n].getFieldSize(), codeSet);
			pos += fields[n].getFieldSize();
			
			if(!fields[n].getName().equals("GridSz") && !fields[n].getName().equals("GridCnt")) {
				if (n > 0)
					jsonBuffer.append(',');
				jsonBuffer.append("\"" + jsonMemberName(fields[n].getName()) + "\":");
				if (fields[n].getFieldType() == FieldType.CHAR) {
					jsonBuffer.append("\"" + data.trim() + "\"");					
				} else {
					jsonBuffer.append(numberToString(data.trim()));
				}
			} else if (fields[n].getName().equals("GridCnt")) {
				if (fields[n + 1].getName().equals("GridSz")) {
					int gridSz = fields[n + 1].getFieldSize();
					int GridCnt = "".equals(data.trim()) ? 0 : Integer.parseInt(data.trim());
					
					data = byteText.toString(pos, FulltextService.gridSzByte, codeSet);
					pos += FulltextService.gridSzByte;
					logger.info("GridCnt: " + GridCnt);
					logger.info("전문사이즈: " + gridSz);
					logger.info("받은사이즈: " + Integer.parseInt(data.trim()));
					if (GridCnt != 0 && gridSz != ("".equals(data.trim()) ? 0 : Integer.parseInt(data.trim()))) {
						String errorMessage = FulltextService.token+title+":**전체**]"
								+ "\n'그리드 사이즈가 일치하지 않습니다.";
						logger.error(errorMessage);
						throw new FulltextException(errorMessage);
					}
					
					if (n > 0)
						jsonBuffer.append(',');
					jsonBuffer.append("\"gridList\":[");
					for (int gridIndex = 0; gridIndex < GridCnt; gridIndex++) {			
						try {
							data = byteText.toString(pos, gridSz, codeSet);
							if (gridIndex > 0)
								jsonBuffer.append(',');

							pos += gridSz;

							jsonBuffer.append(CreateGridOutJson(data, gridFields));
							logger.info("======data:" + data);
						} catch (FulltextException e) {
							e.printStackTrace();
						} catch (Exception e) {
							logger.error("gridIndex:" + gridIndex);
							logger.error("GridCnt:" + GridCnt);
							logger.error("byteText:" + byteText.getLength());
							logger.error("pos:" + pos);
							logger.error("gridSz:" + gridSz);
							
							e.printStackTrace();
						}
					}
					jsonBuffer.append(']');
					
					n++;
				} else {
					String errorMessage = FulltextService.token+title+":"+fields[n].getNameKr()+"]"
							+ "\n'그리드 카운트' 다음에 '그리드 사이즈' 컬럼이 위치하여야 합니다.";
					logger.error(errorMessage);
					throw new FulltextException(errorMessage);				
				}
			}
		}
		jsonBuffer.append('}');
		
		return jsonBuffer.toString();
	}

	
}
