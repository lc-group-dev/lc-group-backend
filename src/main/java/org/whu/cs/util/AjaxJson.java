package org.whu.cs.util;

/**
 * @author:Lucas
 * @description:处理返回成功或者失败相关信息
 * @Date:2019/3/8
 **/
public class AjaxJson<T> {

    public boolean result;
    public String msg;
    public T obj;
    public int code;

    public static <T> AjaxJson<T> success(T obj,String msg){
        return new AjaxJson(true,0,obj,msg);
    }

    public static <T> AjaxJson success(T obj){
        return success(obj,"success");
    }

    public static  AjaxJson success(){
        return  new AjaxJson(true, 0,null,null);
    }

    public static  AjaxJson success(String msg){
        return new AjaxJson(true, 0,null, msg);
    }

    public static AjaxJson error500(String msg){
        return new AjaxJson(false, 500, null, msg);
    }

    public static AjaxJson error(int code, String msg, Object obj){
        return new AjaxJson(false, code, obj, msg);
    }

    public static AjaxJson error(String msg){
        return new AjaxJson(false, 1, null, msg);
    }

    public AjaxJson() {
    }

    public AjaxJson(boolean result, int code, T obj, String msg) {
        this.result = result;
        this.msg = msg;
        this.obj = obj;
        this.code = code;
    }

    public enum CodeEnum {

        /** 200:正常返回,-1服务异常,1业务异常 , 401 未登录, 999没有权限, 503 Token失效 */
        SUCCEE(200) {
            @Override
            public String getDesc() {
                return "处理成功";
            }
        },
        ERROR(-1) {
            @Override
            public String getDesc() {
                return "内部服务异常";
            }
        },
        BizExcep(1) {
            @Override
            public String getDesc() {
                return "正常业务异常";
            }
        },
        NO_PERMISSION(999) {
            @Override
            public String getDesc() {
                return "无权限操作";
            }
        },
        NO_AUTH(401) {
            @Override
            public String getDesc() {
                return "未登录";
            }
        },
        TOKEN_EXPIRE(503) {
            @Override
            public String getDesc() {
                return "Token过期";
            }
        };

        public Integer code;

        /** {@linkplain #code} */
        public Integer getCode() {
            return code;
        }

        public abstract String getDesc();

        /** {@linkplain #code} */
        public void setCode(Integer code) {
            this.code = code;
        }

        private CodeEnum(Integer code) {
            this.code = code;
        }

    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
