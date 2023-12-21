package com.gamedoora.backend.projectservices.api;

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
            value = "/newBoard",
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

    @GetMapping("/getBoard/{boardId}")
    public ResponseEntity<KanBanDTO> findBoardById(@PathVariable("boardId")UUID boardId){
        return createResponse(getKanBanAssembler().findBoardById(boardId), HttpStatus.OK);
    }

    @GetMapping("/getBoard/{issueId}")
    public ResponseEntity<KanBanDTO> findBoardByIssueId(@PathVariable("issueId")UUID issueId){
        return createResponse(getKanBanAssembler().findBoardByIssueId(issueId), HttpStatus.OK);
    }

    @GetMapping("/getBoard/{boardName}")
    public ResponseEntity<KanBanDTO> findBoardByBoardName(@PathVariable("boardName")String boardName){
        return createResponse(getKanBanAssembler().findBoardByName(boardName), HttpStatus.OK);
    }

    @GetMapping("/getBoard/{emailId}")
    public ResponseEntity<List<KanBanDTO>> findBoardByUserId(@PathVariable("emailId")String emailId){
        return createResponse(getKanBanAssembler().findBoardByUserId(emailId), HttpStatus.OK);
    }
}
