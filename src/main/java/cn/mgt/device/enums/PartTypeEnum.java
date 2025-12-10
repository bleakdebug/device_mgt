package cn.mgt.device.enums;

import cn.mgt.device.util.ValidateUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PartTypeEnum {

    PART_TYPEA("1", "部件A"),
    PART_TYPEB("2", "部件B"),
    PART_TYPEC("3", "部件C"),
    ;

    private final String dictCode;
    private final String dictName;


    public static PartTypeEnum of(String code) throws Exception {
        PartTypeEnum result = null;
        if (ValidateUtil.isNotEmpty(code)) {
            for (PartTypeEnum codeEnum : PartTypeEnum.values()) {
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
