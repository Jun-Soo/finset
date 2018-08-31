package com.koscom.util;

import org.slf4j.Logger;

/**
 * 로그 유틸
 */
public class LogUtil {
    /**
     * 로그 내용앞뒤줄에  ====== 에 추가
     * @param logger
     * @param log
     */
    public static void debugLn(Logger logger,Object log){
        logger.debug("====================================");
        logger.debug(log.toString());
        logger.debug("====================================");
    }
    /**
     * 에러 로그 내용앞뒤줄에  ====== 에 추가
     * @param logger
     * @param th
     */
    public static void error(Logger logger,Throwable th){
        StackTraceElement[] ste = th.getStackTrace();
        logger.error("====================================");
        String log = null;
        logger.error(th.toString());
        for(int i=0;i<ste.length;i++){
            log = ste[i].toString();
            logger.error(log);
        }
        logger.error("====================================");
    }
    /**
     * 에러 메세지를 자세히 출력
     * @param th 에러객체
     */
    public static String errorToStr(Throwable th){
        StringBuilder sb =new StringBuilder();
        if (th != null) {
            StackTraceElement[] ste = th.getStackTrace();
            sb.append(th.toString()).append("\n");
            String log = null;
            for(int i=0;i<ste.length;i++){
                log = ste[i].toString();
                sb.append(log).append("\n");
            }
        }
        return sb.toString();
    }
}
