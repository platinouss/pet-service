package com.petsitter.kt.admin.controller;

import com.petsitter.kt.admin.controller.dto.CodeDetailResponse;
import com.petsitter.kt.admin.controller.dto.CodeGroupDetailsResponse;
import com.petsitter.kt.admin.service.AdminService;
import com.petsitter.kt.admin.service.dto.CodeDetailResult;
import com.petsitter.kt.admin.service.dto.CodeGroupResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/code-management")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/code-group")
    public ResponseEntity<List<CodeGroupDetailsResponse>> codeGroupDetails() {
        List<CodeGroupResult> results = adminService.findCodeGroups();
        return ResponseEntity.ok(results.stream().map(CodeGroupDetailsResponse::fromResult).toList());
    }

    @GetMapping("/code")
    public ResponseEntity<List<CodeDetailResponse>> codeDetails(@PathVariable("groupId") String groupId) {
        List<CodeDetailResult> results = adminService.findCodeDetail(groupId);
        return ResponseEntity.ok(results.stream().map(CodeDetailResponse::fromResult).toList());
    }
}
