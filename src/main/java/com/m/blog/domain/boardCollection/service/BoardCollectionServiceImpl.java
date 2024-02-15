package com.m.blog.domain.boardCollection.service;

import com.m.blog.domain.boardCollection.dto.BoardInformationInMenuDto;
import com.m.blog.domain.boardCollection.entity.BoardCollection;
import com.m.blog.domain.boardCollection.repository.BoardCollectionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardCollectionServiceImpl implements BoardCollectionService{
    private final BoardCollectionJpaRepository boardCollectionJpaRepository;

    @Override
    public List<BoardCollection> findAll(){
        return boardCollectionJpaRepository.findAll();
    }

    @Override
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
