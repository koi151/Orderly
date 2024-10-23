package com.koi151.QTDL.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {

//    private short status = 200;
    private Object data;
    private String desc;
//    private boolean isSuccess = true;
}
