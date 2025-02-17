package com.petsitter.kt.admin.service;

import com.petsitter.kt.admin.model.CodeDetail;
import com.petsitter.kt.admin.model.CodeGroup;
import com.petsitter.kt.admin.repository.CodeDetailRepository;
import com.petsitter.kt.admin.repository.CodeGroupRepository;
import com.petsitter.kt.admin.service.dto.CodeDetailResult;
import com.petsitter.kt.admin.service.dto.CodeGroupResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final CodeGroupRepository codeGroupRepository;
    private final CodeDetailRepository codeDetailRepository;

    public List<CodeGroupResult> findCodeGroups() {
        List<CodeGroup> codeGroups = codeGroupRepository.findAll();
        return codeGroups.stream().map(CodeGroupResult::fromEntity).toList();
    }

    public List<CodeDetailResult> findCodeDetail(String groupId) {
        CodeGroup codeGroup = codeGroupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("코드 그룹이 존재하지 않습니다."));
        List<CodeDetail> codeDetails = codeDetailRepository.findAllByCodeGroup(codeGroup);
        return codeDetails.stream().map(CodeDetailResult::fromEntity).toList();
    }
}
