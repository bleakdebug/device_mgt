package cn.mgt.device.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class ValidateUtil {
    private static final String NULL_STRING = "null";
    private static final String UNDEFINED_STRING = "undefined";

    public ValidateUtil() {
    }

    public static boolean isEmpty(Object... objects) {
        if (objects == null) {
            return true;
        } else {
            Object[] var1 = objects;
            int var2 = objects.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Object object = var1[var3];
                if (isEmpty(object)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof Optional) {
            return !((Optional)obj).isPresent();
        } else if (obj instanceof CharSequence) {
            return ((CharSequence)obj).length() == 0 || isEmpty(obj.toString());
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        } else if (obj instanceof Collection) {
            return ((Collection)obj).isEmpty();
        } else {
            return obj instanceof Map && ((Map)obj).isEmpty();
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0 || "null".equalsIgnoreCase(str.trim()) || "undefined".equalsIgnoreCase(str.trim());
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
}
