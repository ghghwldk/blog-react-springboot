package com.m.blog.domain.boardCollection.service;

import com.m.blog.domain.boardCollection.dto.BoardInformationInMenuDto;
import com.m.blog.domain.boardCollection.entity.BoardCollection;
import com.m.blog.domain.boardCollection.repository.BoardCollectionRepository;
import com.m.blog.domain.board.repository.BoardCustomRepository;
import com.m.blog.domain.boardCollection.repository.BoardCollectionJpaRepository;
import com.m.blog.domain.menu.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

@Service
public class BoardCollectionService {
    @Autowired
    BoardCollectionRepository boardCollectionCustomRepository;
    @Autowired
    BoardCollectionJpaRepository boardCollectionJpaRepository;

    @Autowired
    BoardCustomRepository boardCustomRepository;

    @Transactional
    public List<MenuVo> getMenuResponseDto(){
        List<MenuVo> menuVos = new LinkedList<>();
        List<BoardCollection> boardCollections = boardCollectionJpaRepository.findAll();

        List<BoardInformationInMenuDto> all = getAllBoardInformationInMenuDtos();

        for(BoardCollection bc: boardCollections){
            int postingCount = 0;
            String boardCollectionName= bc.getName();
            int boardCollectionId= bc.getId();
            List<BoardInformationInMenuDto> InSpecificBoardCollection = new LinkedList<>();
            for(BoardInformationInMenuDto b: all){
                if(b.getBoardCollectionId() == boardCollectionId){
                    InSpecificBoardCollection.add(b);
                    postingCount += b.getPostingCount();
                }
            }
            menuVos.add(new MenuVo(boardCollectionName, boardCollectionId, postingCount, InSpecificBoardCollection));
        }

        return menuVos;
    }

    public List<BoardInformationInMenuDto> getAllBoardInformationInMenuDtos(){
        List<Object[]> objects = boardCollectionJpaRepository.getMenuDtos();
        List<BoardInformationInMenuDto> boardInformationInMenuDtos = new LinkedList<>();
        for (Object[] o: objects){
            BoardInformationInMenuDto boardInformationInMenuDto =
                    new BoardInformationInMenuDto(
                            (Integer)o[0],
                            (String)o[1],
                            (Integer)o[2],
                            (String)o[3],
                            ((BigInteger)o[4]).intValue()
                    );
            boardInformationInMenuDtos.add(boardInformationInMenuDto);
        }
        return boardInformationInMenuDtos;
    }
}
