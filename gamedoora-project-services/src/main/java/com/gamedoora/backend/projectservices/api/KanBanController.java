package com.gamedoora.backend.projectservices.api;

import com.gamedoora.backend.projectservices.assembler.KanBanAssembler;
import com.gamedoora.model.dto.KanBanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/board")
public class KanBanController extends BaseController{
    private KanBanAssembler kanBanAssembler;

    public KanBanAssembler getKanBanAssembler() {
        return kanBanAssembler;
    }

    @Autowired
    public void setKanBanAssembler(KanBanAssembler kanBanAssembler) {
        this.kanBanAssembler = kanBanAssembler;
    }

    @PostMapping(
            value = "/",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<KanBanDTO> createBoard(@RequestBody KanBanDTO kanBanDTO){
        if(kanBanDTO == null){
            return null;
        }
        return createResponse(getKanBanAssembler().createBoard(kanBanDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/")
    public ResponseEntity<HttpStatus> deleteAllBoard(){
        getKanBanAssembler().deleteAllBoards();
        return createResponse(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<HttpStatus> deleteIssueById(@PathVariable("boardId") UUID boardId) {
        getKanBanAssembler().deleteBoardById(boardId);
        return createResponse(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<KanBanDTO> findBoardById(@PathVariable("boardId")UUID boardId){
        return createResponse(getKanBanAssembler().findBoardById(boardId), HttpStatus.OK);
    }

    @GetMapping("/{issueId}")
    public ResponseEntity<KanBanDTO> findBoardByIssueId(@PathVariable("issueId")UUID issueId){
        return createResponse(getKanBanAssembler().findBoardByIssueId(issueId), HttpStatus.OK);
    }

    @GetMapping("/{boardName}")
    public ResponseEntity<KanBanDTO> findBoardByBoardName(@PathVariable("boardName")String boardName){
        return createResponse(getKanBanAssembler().findBoardByName(boardName), HttpStatus.OK);
    }

    @GetMapping("/emailId")
    public ResponseEntity<List<KanBanDTO>> findBoardByUserId(@RequestParam("emailId")String emailId){
        return createResponse(getKanBanAssembler().findBoardByUserId(emailId), HttpStatus.OK);
    }
}
