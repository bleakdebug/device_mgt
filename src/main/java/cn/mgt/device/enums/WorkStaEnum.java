package cn.mgt.device.enums;

import cn.mgt.device.util.ValidateUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WorkStaEnum {

    WORKSTAA("1", "工作站A"),
    WORKSTAB("2", "工作站B"),
    WORKSTAC("3", "工作站C"),
    ;

    private final String dictCode;
    private final String dictName;


    public static WorkStaEnum of(String code) throws Exception {
        WorkStaEnum result = null;
        if (ValidateUtil.isNotEmpty(code)) {
            for (WorkStaEnum codeEnum : WorkStaEnum.values()) {
                if (code.equals(codeEnum.getDictCode())) {
                    result = codeEnum;
                    break;
                }
            }
        }
        if (result == null) {
            throw new Exception("未获取到部件类型枚举值[{}]，请联系管理员进行反馈");
        }
        return result;
    }

}
