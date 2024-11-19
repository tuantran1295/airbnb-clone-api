package com.tuantran.airbnb.service;

import com.tuantran.airbnb.constant.common.ResponseCode;
import com.tuantran.airbnb.dto.common.response.ApiError;
import com.tuantran.airbnb.dto.common.response.Meta;
import com.tuantran.airbnb.dto.common.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ResponseFactory {
    @Value("airbnb")
    String appName;

    public ResponseDTO response(ResponseCode responseCode) {
        var meta = Meta.builder()
                .status(responseCode.getType())
                .serviceId(appName)
                .message(responseCode.getMessage())
                .build();

        return new ResponseDTO(meta, null);
    }

    public ResponseDTO response(Object payload) {
        var meta = Meta.builder()
                .status(ResponseCode.SUCCESS.getType())
                .serviceId(appName)
                .build();

        return new ResponseDTO(meta, payload);
    }

    public ResponseDTO invalidParams(Collection<ApiError> errors) {
        var meta = Meta.builder()
                .status(ResponseCode.INVALID_PARAMS.getType())
                .serviceId(appName)
                .errors(errors)
                .build();

        return new ResponseDTO(meta, null);
    }
}
