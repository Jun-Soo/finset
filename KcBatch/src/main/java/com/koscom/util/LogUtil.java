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
     * 로그 내용앞뒤줄에  ====== 에 추가
     * @param logger
     * @param log
     */
    public static void infoLn(Logger logger,Object log){
        logger.info("====================================");
        logger.info(log.toString());
        logger.info("====================================");
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
        for(int i=0;i<ste.length;i++){
            log = ste[i].toString();
            logger.error(log);
        }
        logger.error("====================================");
    }
    /**
     * 에러 로그 내용앞뒤줄에  ====== 에 추가
     * @param logger
     * @param th
     */
    public static void error(Logger logger,Object log){
        logger.error("====================================");
        logger.error(log.toString());
        logger.error("====================================");
    }
}
