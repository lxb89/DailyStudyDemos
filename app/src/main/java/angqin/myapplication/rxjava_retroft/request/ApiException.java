package angqin.myapplication.rxjava_retroft.request;

/**
 * 作者：${lixuebin} on 2018/1/25 14:45
 * 邮箱：2072301410@qq.com
 */
public class ApiException extends RuntimeException {

    public static final int USER_NOT_EXIST = 100;
    public static final int WRONG_PASSWORD = 101;

    public ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    private static String getApiExceptionMessage(int code) {
        String message = "";
        switch (code) {
            case USER_NOT_EXIST:
                message = "该用户不存在";
                break;
            case WRONG_PASSWORD:
                message = "密码错误";
                break;
            default:
                message = "未知错误";

        }
        return message;
    }
}
