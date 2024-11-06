package com.koi151.QTDL.model.response;

import com.koi151.QTDL.model.dto.AbstractDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ResponseData extends AbstractDTO<Object> {

//    private short status = 200;
    private Object data;
    private String desc;
//    private boolean isSuccess = true;
}
