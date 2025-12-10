package cn.mgt.device.enums;

import cn.mgt.device.util.ValidateUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEnum {

    STAtUSA("1", "正常"),
    STAtUSB("0", "异常"),
    ;

    private final String dictCode;
    private final String dictName;


    public static StatusEnum of(String code) throws Exception {
        StatusEnum result = null;
        if (ValidateUtil.isNotEmpty(code)) {
            for (StatusEnum codeEnum : StatusEnum.values()) {
                if (code.equals(codeEnum.getDictCode())) {
                    result = codeEnum;
                    break;
                }
            }
        }
        if (result == null) {
            throw new Exception("未获取到组装状态枚举值[" + code + "]，请联系管理员进行反馈");
        }
        return result;
    }

}
