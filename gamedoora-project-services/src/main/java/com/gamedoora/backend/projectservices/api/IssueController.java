package com.gamedoora.backend.projectservices.api;

import com.gamedoora.backend.projectservices.assembler.IssueAssembler;
import com.gamedoora.backend.projectservices.exceptions.NotFoundException;
import com.gamedoora.model.dao.ActivityType;
import com.gamedoora.model.dao.Priority;
import com.gamedoora.model.dto.BaseIssueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/issue")
public class IssueController extends BaseController{
    private IssueAssembler issueAssembler;

    public IssueAssembler getIssueAssembler() {
        return issueAssembler;
    }

    @Autowired
    public void setIssueAssembler(IssueAssembler issueAssembler) {
        this.issueAssembler = issueAssembler;
    }

    @PostMapping(
            value = "/addIssue",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BaseIssueDTO> createIssue(@RequestBody BaseIssueDTO issueDTO){
        return createResponse(getIssueAssembler().createIssue(issueDTO), HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/emailId",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BaseIssueDTO> updateIssue(@RequestParam("emailId")String emailId,
                                                    @RequestBody BaseIssueDTO issueDTO){
        BaseIssueDTO issue = getIssueAssembler().updateIssue(emailId, issueDTO);
        if (issue == null) {
            throw new NotFoundException(MessageFormat.format("Issue by id {0} not found", emailId));
        }
        return createResponse(issue, HttpStatus.OK);
    }

    @PostMapping(
            value = "/addComments/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BaseIssueDTO> addComment(@RequestParam("id") UUID id,
                                                   @RequestParam("comment") String comment){
        BaseIssueDTO issue = getIssueAssembler().updateCommentByIssueId(id, comment);
        if (issue == null) {
            throw new NotFoundException(MessageFormat.format("Issue by id {0} not found", id));
        }
        return createResponse(issue, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<HttpStatus> deleteAllIssues() {
        getIssueAssembler().deleteAllIssues();
        return createResponse(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<HttpStatus> deleteIssueById(@PathVariable("issueId") UUID issueId) {
        getIssueAssembler().deleteIssues(issueId);
        return createResponse(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getIssue/{issueId}")
    public ResponseEntity<BaseIssueDTO> findIssueById(@PathVariable("issueId")UUID issueId){
        return createResponse(getIssueAssembler().findIssueById(issueId), HttpStatus.OK);
    }

    @GetMapping("/getIssue/{title}")
    public ResponseEntity<BaseIssueDTO> findIssueByTitle(@PathVariable("issueId")String title){
        return createResponse(getIssueAssembler().findByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/getIssue/{emailId}")
    public ResponseEntity<BaseIssueDTO> findIssueByUserId(@PathVariable("emailId")String emailId){
        return createResponse(getIssueAssembler().findIssueByUserId(emailId), HttpStatus.OK);
    }

    @GetMapping("/getIssue/{priority}")
    public ResponseEntity<List<BaseIssueDTO>> findIssueByPriority(@PathVariable("priority") Priority priority){
        return createResponse(getIssueAssembler().findIssueByPriority(priority), HttpStatus.OK);
    }

    @GetMapping("/getIssue/{activityType}")
    public ResponseEntity<BaseIssueDTO> findIssueByActivityType(@PathVariable("activityType") ActivityType activityType){
        return createResponse(getIssueAssembler().findIssueActivityType(activityType), HttpStatus.OK);
    }
}
