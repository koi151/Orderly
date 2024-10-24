package com.koi151.QTDL.controller;

import com.koi151.QTDL.model.request.DaiLyRequest;
import com.koi151.QTDL.model.response.ResponseData;
import com.koi151.QTDL.service.DaiLyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dai-ly")
public class DaiLyController {

    private final DaiLyService daiLyService;

    @PostMapping("/")
    public ResponseEntity<ResponseData> taoDaiLy(
        @RequestBody @NotNull @Valid DaiLyRequest request
    ) {
        var accountCreated = daiLyService.taoDaiLy(request);
        return new ResponseEntity<>(
            ResponseData.builder()
                .data(accountCreated)
                .desc("Tao thanh cong dai ly " + request.getTenDL())
                .build()
            , HttpStatus.CREATED);
    }
}
