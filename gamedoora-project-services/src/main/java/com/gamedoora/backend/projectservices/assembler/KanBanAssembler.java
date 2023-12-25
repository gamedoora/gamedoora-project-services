package com.gamedoora.backend.projectservices.assembler;

import com.gamedoora.backend.projectservices.repository.KanBanRepository;
import com.gamedoora.model.dto.KanBanDTO;
import com.gamedoora.model.mapper.KanBanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class KanBanAssembler {

    private KanBanRepository kanBanRepository;

    private KanBanMapper kanBanMapper;

    public KanBanMapper getKanBanMapper() {
        return kanBanMapper;
    }

    @Autowired
    public void setKanBanMapper(KanBanMapper kanBanMapper) {
        this.kanBanMapper = kanBanMapper;
    }

    public KanBanRepository getKanBanRepository() {
        return kanBanRepository;
    }

    @Autowired
    public void setKanBanRepository(KanBanRepository kanBanRepository) {
        this.kanBanRepository = kanBanRepository;
    }

    public KanBanDTO createBoard(KanBanDTO kanBanDTO){
        getKanBanRepository().save(getKanBanMapper().kanBanDTOToKanBan(kanBanDTO));
        return kanBanDTO;
    }

    public KanBanDTO findBoardById(UUID boardId){
        if(boardId == null){
            return null;
        }
        return getKanBanMapper().kanBanToKanBanDTO(getKanBanRepository().findByBoardId(boardId));
    }

    public KanBanDTO findBoardByName(String boardName){
        if(boardName == null){
            return null;
        }
        return getKanBanMapper().kanBanToKanBanDTO(getKanBanRepository().findByBoardName(boardName));
    }

    public List<KanBanDTO> findBoardByUserId(String emailId){
        if(emailId == null){
            return null;
        }
        List<KanBanDTO> boards = new ArrayList<>();
        getKanBanRepository()
                .findByUsers_EmailId(emailId)
                .forEach((board) -> boards.add(getKanBanMapper()
                        .kanBanToKanBanDTO(board)));

        return boards;
    }

    public KanBanDTO findBoardByIssueId(UUID issueId){
        if(issueId == null){
            return null;
        }
        return getKanBanMapper().kanBanToKanBanDTO(getKanBanRepository().findByStoryIssue_IssueId(issueId));
    }

    public void deleteBoardById(UUID boardId){
        getKanBanRepository().deleteById(boardId);
    }

    public void deleteAllBoards(){
        getKanBanRepository().deleteAll();
    }

}
