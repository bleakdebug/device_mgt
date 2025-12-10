package cn.mgt.device.util;

import cn.mgt.device.bean.ResultDO;

public class CommonUtil {
    public static ResultDO sResult(Boolean flag, String msg){
        ResultDO resultDO = new ResultDO();
        resultDO.setCode(flag ? "1" : "0");
        resultDO.setMessage(msg);
        return resultDO;
    }
    
    public static ResultDO sResult(Boolean flag, String msg, Object data){
        ResultDO resultDO = new ResultDO();
        resultDO.setCode(flag ? "1" : "0");
        resultDO.setMessage(msg);
        resultDO.setData(data);
        return resultDO;
    }
}
