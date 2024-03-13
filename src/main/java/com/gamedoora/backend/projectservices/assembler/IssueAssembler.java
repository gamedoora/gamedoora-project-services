package com.gamedoora.backend.projectservices.assembler;

import com.gamedoora.backend.projectservices.repository.BaseIssueRepository;
import com.gamedoora.model.dao.ActivityType;
import com.gamedoora.model.dao.BaseIssue;
import com.gamedoora.model.dao.Comments;
import com.gamedoora.model.dao.IssueHistory;
import com.gamedoora.model.dao.Priority;
import com.gamedoora.model.dto.BaseIssueDTO;
import com.gamedoora.model.dto.IssueHistoryDTO;
import com.gamedoora.model.mapper.IssueHistoryMapper;
import com.gamedoora.model.mapper.IssueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class IssueAssembler {

    private BaseIssueRepository baseIssueRepository;

    private IssueHistoryMapper issueHistoryMapper;

    private IssueMapper issueMapper;

    public IssueMapper getIssueMapper() {
        return issueMapper;
    }
    @Autowired
    public void setIssueMapper(IssueMapper issueMapper) {
        this.issueMapper = issueMapper;
    }


    public IssueHistoryMapper getIssueHistoryMapper() {
        return issueHistoryMapper;
    }
    @Autowired
    public void setIssueHistoryMapper(IssueHistoryMapper issueHistoryMapper) {
        this.issueHistoryMapper = issueHistoryMapper;
    }

    public BaseIssueRepository getBaseIssueRepository() {
        return baseIssueRepository;
    }

    @Autowired
    public void setBaseIssueRepository(BaseIssueRepository baseIssueRepository) {
        this.baseIssueRepository = baseIssueRepository;
    }


    //--------------------------------------------------------

    public BaseIssueDTO createIssue(BaseIssueDTO baseIssueDTO){
        if(baseIssueDTO == null){
            return null;
        }
        BaseIssue issue = getIssueMapper().baseIssueDTOToBaseIssue(baseIssueDTO);
        getBaseIssueRepository().save(issue);
        return baseIssueDTO;
    }

    public BaseIssueDTO updateIssue(UUID issueId, BaseIssueDTO baseIssueDTO){
        if(baseIssueDTO == null){
            return null;
        }
        BaseIssue issue = getBaseIssueRepository().findByIssueId(issueId);
        List<IssueHistory> issueHistories = issue.getHistory();
        IssueHistory history = new IssueHistory();
        history.setActivityType(issue.getActivityType());
        history.setStartDate(issue.getStartDate());
//        history.setUsers(issue.getIssuer());
        history.setId(issue.getIssueId());
        issueHistories.add(history);
        issue.setHistory(issueHistories);
        getBaseIssueRepository().save(issue);
        return baseIssueDTO;
    }

    public BaseIssueDTO findIssueById(UUID id){
        BaseIssueDTO issue = getIssueMapper().baseIssueToIssueDTO(getBaseIssueRepository().findByIssueId(id));
        return issue;
    }

    public BaseIssueDTO findByTitle(String title){
        BaseIssueDTO issue = getIssueMapper().baseIssueToIssueDTO(getBaseIssueRepository().findByTitle(title));
        return issue;
    }

    public BaseIssueDTO findIssueByUserId(String emailId){
        BaseIssueDTO issue = getIssueMapper().baseIssueToIssueDTO(getBaseIssueRepository().findByHistory_Users_EmailId(emailId));
        return issue;
    }

    public List<BaseIssueDTO> findIssueByPriority(Priority priority){
        List<BaseIssueDTO> issue = getBaseIssueRepository().findByPriority(priority)
                .stream().map(getIssueMapper()::baseIssueToIssueDTO)
                .collect(Collectors.toList());
        return issue;
    }

    public void deleteIssues(UUID id){
        getBaseIssueRepository().deleteById(id);
    }

    public void deleteAllIssues(){
        getBaseIssueRepository().deleteAll();
    }

    public BaseIssueDTO findIssueByHistory(IssueHistoryDTO issueHistoryDTO){
        IssueHistory history = getIssueHistoryMapper().issueHistoryDTOToIssueHistory(issueHistoryDTO);
        BaseIssue baseIssue = getBaseIssueRepository().findByHistory_Id(history.getId());
        return getIssueMapper().baseIssueToIssueDTO(baseIssue);
    }

    public BaseIssueDTO findIssueByCommentByUserId(String emailId){
        BaseIssueDTO issueDTO = getIssueMapper().baseIssueToIssueDTO(getBaseIssueRepository().findByComments_Users_EmailId(emailId));
        return issueDTO;
    }

    public BaseIssueDTO findIssueActivityType(ActivityType activityType){
        BaseIssueDTO issueDTO = getIssueMapper().baseIssueToIssueDTO(getBaseIssueRepository().findByActivityType(activityType));
        return issueDTO;
    }

    public BaseIssueDTO updateCommentByIssueId(UUID id, String comment){
        BaseIssue issue = getBaseIssueRepository().findByComments_IssueId(id);
        List<Comments> comments = issue.getComments();
        Comments newComment = new Comments();
        newComment.setComment(comment);
        comments.add(newComment);
        issue.setComments(comments);
        baseIssueRepository.save(issue);
        BaseIssueDTO issueDTO = getIssueMapper().baseIssueToIssueDTO(issue);
        return issueDTO;
    }

    // sure use exception handlers made in user-services
}
