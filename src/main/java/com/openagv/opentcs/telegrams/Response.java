package com.openagv.opentcs.telegrams;

import cn.hutool.core.util.ObjectUtil;
import com.openagv.core.interfaces.IRequest;
import com.openagv.core.interfaces.IResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by laotang on 2019/9/26.
 */
public class Response implements IResponse {

    private String requestId;
    private Map<String,Object> params;
    private int status;
    private String cmdKey;
    private Object returnObj;

    private Response(String requestId) {
        this.params = new HashMap<>();
        this.requestId = requestId;
        status = HttpResponseStatus.OK.code();
        this.returnObj = null;
    }

    public static Response build(String requestId) {
        return new Response(requestId);
    }

    @Override
    public String getRequestId() {
        return requestId;
    }

    @Override
    public void write(Object returnObj) {
        this.returnObj = returnObj;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void setParams(String key, String value) {
        this.params.put(key, value);
    }

    @Override
    public void setStatus(int status) {
        this.status= status;
    }

    @Override
    public String toString() {
        if(null != returnObj) {
            if(returnObj instanceof String) {
                return (String)returnObj;
            } else {
                return ObjectUtil.toString(returnObj);
            }
        } else{
            return "Hello, Laotang";
        }
    }

    @Override
    public void setTargetPointName(String pointName) {
        params.put(IResponse.TARGET_POINT_NAME, pointName);
    }

    @Override
    public String getTargetPointName() {
        return String.valueOf(params.get(IResponse.TARGET_POINT_NAME));
    }

    @Override
    public void setCmdKey(String key) {
        this.cmdKey =  String.valueOf(key);
    }

    @Override
    public String getCmdKey() {
        return cmdKey;
    }

    @Override
    public Map<String, Object> getParams() {
        return params;
    }
}