package org.example.studyfy.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.studyfy.member.db.MemberEntity;
import org.example.studyfy.member.model.MemberRequest;
import org.example.studyfy.member.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @PostMapping("/sign")
    public MemberEntity create(
            @Valid
            @RequestBody MemberRequest request
    ){
        MemberEntity memberEntity = request.toEntity();
        return memberService.create(memberEntity);
    }

    @PostMapping("/login_test")
    public String loginTest(
            @Valid
            @RequestBody MemberRequest request
    ){
        return memberService.findMember(request);
    }



}
