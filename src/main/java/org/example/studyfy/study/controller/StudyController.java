package org.example.studyfy.study.controller;

import lombok.RequiredArgsConstructor;
import org.example.studyfy.study.dto.StudyRequestDto;
import org.example.studyfy.study.dto.StudyResponseDto;
import org.example.studyfy.study.entity.StudyEntity;
import org.example.studyfy.study.service.StudyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/studies")
public class StudyController {

    private final StudyService studyService;

    // 스터디 생성
    @PostMapping("")
    public ResponseEntity<StudyResponseDto> createStudy(
            @RequestBody StudyRequestDto request
    ) {
        // 유저 아이디 getId 수정 필요
        Long memberId = request.getCreatorId();
        return ResponseEntity.ok(studyService.createStudy(memberId, request));
    }

    // 스터디 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<StudyResponseDto> getStudy(@PathVariable Long id) {
        StudyResponseDto response = studyService.getStudy(id);
        return ResponseEntity.ok(response);
    }

    // 스터디 전체 조회
    @GetMapping("")
    public ResponseEntity<List<StudyResponseDto>> getAllStudies() {
        List<StudyResponseDto> responses = studyService.getAllStudies();
        return ResponseEntity.ok(responses);
    }


    // 스터디 수정
    @PutMapping("/{id}")
    public ResponseEntity<StudyResponseDto> updateStudy(
            @PathVariable Long id,
            @RequestBody StudyRequestDto request) {
        // 유저 아이디 getId 수정 필요
        Long memberId = request.getCreatorId();
        StudyResponseDto response = studyService.updateStudy(id, request);
        return ResponseEntity.ok(response);
    }


    // 스터디 삭제
    @DeleteMagitpping("/{id}")
    public ResponseEntity<Void> deleteStudy(@PathVariable Long id) {
        // 유저 아이디 getId 수정 필요
        studyService.deleteStudy(id);
        return ResponseEntity.noContent().build();
    }

}
