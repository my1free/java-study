package com.michealyang.spring.boot.util;

import com.michealyang.spring.boot.dto.ResultDto;

/**
 * @author michealyang
 * @version 1.0
 * @created 18/5/4
 * 开始眼保健操： →_→  ↑_↑  ←_←  ↓_↓
 */
public class ResBuilder {

    public static ResultDto genSuccess() {
        ResultDto dto = new ResultDto();
        dto.setSuccess(true);
        dto.setMsg("success");
        return dto;
    }

    public static ResultDto genSuccess(String msg) {
        ResultDto dto = genSuccess();
        dto.setMsg(msg);
        return dto;
    }

    public static ResultDto genError(String msg) {
        ResultDto dto = new ResultDto();
        dto.setSuccess(false);
        dto.setMsg(msg);
        return dto;
    }

    public static ResultDto genData(Object data) {
        ResultDto dto = genSuccess();
        dto.setData(data);
        return dto;
    }

    public static ResultDto genData(Object data, String msg) {
        ResultDto dto = genSuccess(msg);
        dto.setData(data);
        return dto;
    }

}
