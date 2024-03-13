package com.gamedoora.backend.projectservices.repository;

import com.gamedoora.model.dao.ActivityType;
import com.gamedoora.model.dao.BaseIssue;
import com.gamedoora.model.dao.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BaseIssueRepository extends JpaRepository<BaseIssue, UUID> {
    BaseIssue findByHistory_Users_EmailId(String emailId);

    List<BaseIssue> findByPriority(Priority priority);

    BaseIssue findByIssueId(UUID issueId);

    BaseIssue findByTitle(String title);

    BaseIssue findByHistory_Id(UUID id);

    BaseIssue findByComments_Users_EmailId(String emailId);

    BaseIssue findByActivityType(ActivityType activityType);

    BaseIssue findByComments_Id(long id);

    BaseIssue findByComments_IssueId(UUID issueId);

}
