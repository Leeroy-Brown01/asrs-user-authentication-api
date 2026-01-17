package com.brown.asrs_user_authentication_api.controller;

import com.brown.asrs_user_authentication_api.dto.auth.MeResponseDto;
import com.brown.asrs_user_authentication_api.dto.auth.MeUpdateDto;
import com.brown.asrs_user_authentication_api.service.MeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/me")
public class MeController {
    private final MeService meService;

    @Autowired
    public MeController(MeService meService) {
        this.meService = meService;
    }

    @GetMapping("api/me{id}")
    public MeResponseDto getMe(@RequestHeader("id") Long Id) {
        return meService.getMe(Id);
    }

    //Update me endpoint
    @GetMapping("api/me/update{id}")
    public MeResponseDto updateMe(@RequestHeader("id") Long id, MeUpdateDto meUpdateDto) {
        return meService.updateMe(id, meUpdateDto);
    }
}


