package xcu.lxj.ssmchat.utils;

public class ResponseBadyData {

    private int code;

    private String message;

    private Object data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    //  匹配成功
    public static ResponseBadyData success(Object data) {

        return new ResponseBadyData(200, "success", data);

    }

    //  匹配失败
    public static ResponseBadyData failure(Object data) {

        return new ResponseBadyData(400, "failure", data);

    }


    public ResponseBadyData(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
