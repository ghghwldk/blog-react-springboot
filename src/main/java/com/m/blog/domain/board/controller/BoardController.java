package com.m.blog.domain.board.controller;

import com.m.blog.domain.board.entity.Board;
import com.m.blog.domain.board.repository.BoardCustomRepository;
import com.m.blog.domain.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
