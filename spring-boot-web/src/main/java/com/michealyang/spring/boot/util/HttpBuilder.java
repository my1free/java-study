package com.michealyang.spring.boot.util;

import com.alibaba.fastjson.JSONObject;
import com.michealyang.spring.boot.dto.ResultDto;

/**
 * @author michealyang
 * @version 1.0
 * @created 18/5/4
 * 开始眼保健操： →_→  ↑_↑  ←_←  ↓_↓
 */
public class HttpBuilder {

    public static JSONObject genJson(ResultDto dto) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", dto.isSuccess() ? 0 : 1);
        jsonObject.put("msg", dto.getMsg());
        jsonObject.put("data", dto.getData());
        return jsonObject;
    }
}
