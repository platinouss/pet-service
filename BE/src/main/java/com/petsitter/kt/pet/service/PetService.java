package com.petsitter.kt.pet.service;

import com.petsitter.kt.admin.model.CodeDetail;
import com.petsitter.kt.admin.model.CodeGroup;
import com.petsitter.kt.admin.repository.CodeDetailRepository;
import com.petsitter.kt.admin.repository.CodeGroupRepository;
import com.petsitter.kt.common.enums.CodeGroupType;
import com.petsitter.kt.pet.service.dto.PetCategoryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final CodeGroupRepository codeGroupRepository;
    private final CodeDetailRepository codeDetailRepository;

    public List<PetCategoryResult> findAllAnimalCategories() {
        CodeGroup animalCategoryCodeGroup = codeGroupRepository.findById(CodeGroupType.ANIMAL_CATEGORY.getGroupId())
                .orElseThrow(() -> new RuntimeException("코드 그룹이 존재하지 않습니다."));
        List<CodeDetail> codeDetails = codeDetailRepository.findAllByCodeGroup(animalCategoryCodeGroup);
        return codeDetails.stream().map(PetCategoryResult::fromEntity).toList();
    }

}
