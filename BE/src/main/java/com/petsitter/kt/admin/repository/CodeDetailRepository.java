package com.petsitter.kt.admin.repository;

import com.petsitter.kt.admin.model.CodeDetail;
import com.petsitter.kt.admin.model.CodeGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeDetailRepository extends JpaRepository<CodeDetail, String> {

    List<CodeDetail> findAllByCodeGroup(CodeGroup codeGroup);
}
