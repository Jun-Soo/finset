package com.koscom.comm;

import com.koscom.util.Constant;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

public class ComController implements Constant {
    @Resource
    public Environment environment;
    public static boolean IS_LOCAL = false;

    public ComController() {
        String site = (environment != null)?environment.getProperty("service.profile"):"";
        if(LOCAL.equals(site)) {
            IS_LOCAL = true;
        }
    }
}
