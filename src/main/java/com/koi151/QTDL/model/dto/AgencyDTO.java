package com.koi151.QTDL.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgencyDTO {
    private long agencyId;
    private String agencyName;
    private String address;
    private String phone;
    private String repInfo;
}
