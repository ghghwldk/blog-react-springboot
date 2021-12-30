package com.m.blog.controller;

import com.m.blog.dto.BoardDto;
import com.m.blog.entity.Board;
import com.m.blog.paging.PagingResponse;
import com.m.blog.repository.BoardCustomRepository;
import com.m.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardCustomRepository boardCustomRepository;
    @Autowired
    BoardService boardService;

    @ResponseBody
    @GetMapping("/list/group_by_board_collection_id")
    public List<List<Board>> listGroupByBoardCollection(){
        List<Board> boards = boardCustomRepository.findBoards();
        List<List<Board>> response= boardService.processGroupBy(boards);
        return response;
    }


    @ResponseBody
    @GetMapping("/list/{board_collection_id}")
    public PagingResponse list(@PathVariable("board_collection_id")int boardCollectionId, Pageable pageable){
        Page<BoardDto> page= boardCustomRepository.findBoardPage(boardCollectionId, pageable);
        List<BoardDto> boards = page.getContent();
        Integer totalPage= page.getTotalPages();
        return new PagingResponse(totalPage, boards);
    }
}
