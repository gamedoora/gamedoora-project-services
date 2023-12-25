package com.gamedoora.backend.projectservices.repository;

import com.gamedoora.model.dao.KanBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface KanBanRepository extends JpaRepository<KanBan, UUID> {
    KanBan findByBoardId(UUID boardId);

    KanBan findByBoardName(String boardName);

    List<KanBan> findByUsers_EmailId(String emailId);

    KanBan findByStoryIssue_IssueId(UUID issueId);
}
