package com.wei.utils.json;

/**
 * @Author:wei.peng
 * @Desicription:
 * @Date:Created in 2018/6/26.
 * @Modified By:
 */
public class JsonResult {
    private String status = null;

    private Object result = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
