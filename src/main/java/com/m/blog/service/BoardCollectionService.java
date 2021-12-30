package com.m.blog.service;

import com.m.blog.entity.Board;
import com.m.blog.entity.BoardCollection;
import com.m.blog.repository.BoardCollectionCustomRepository;
import com.m.blog.repository.BoardCollectionJpaRepository;
import com.m.blog.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
public class BoardCollectionService {
    @Autowired
    BoardCollectionCustomRepository boardCollectionCustomRepository;
    @Autowired
    BoardCollectionJpaRepository boardCollectionJpaRepository;

    @Transactional
    public List<MenuVo> getMenuResponseDto(){
        List<MenuVo> menuVos = new LinkedList<>();
        List<BoardCollection> boardCollections = boardCollectionJpaRepository.findAll();

        boardCollections.forEach(e->{
            String boardCollectionName= e.getName();
            int boardCollectionId= e.getId();
            List<Board> boards = boardCollectionCustomRepository.findBoards(e.getId());

            menuVos.add(new MenuVo(boardCollectionName, boardCollectionId, boards));
        });

        return menuVos;
    }
}
