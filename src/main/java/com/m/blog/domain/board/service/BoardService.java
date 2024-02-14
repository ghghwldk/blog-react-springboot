package com.m.blog.domain.board.service;

import com.m.blog.domain.board.entity.Board;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {





    public List<List<Board>> processGroupBy(List<Board> boards){
        ArrayList<List<Board>> result= new ArrayList<>();

        ArrayList<Board> memorized= new ArrayList<>();
        int beforeBoardCollectionId=-1;
        for(int i=0; i<boards.size(); i++){
            int currentBoardCollectionId=boards.get(i).getBoardCollectionId();
            if(currentBoardCollectionId!=beforeBoardCollectionId){
                if(i==0){
                    beforeBoardCollectionId = boards.get(i).getBoardCollectionId();

                }else{
                    ArrayList<Board> cloned= null;
                    cloned=(ArrayList<Board>) memorized.clone();
                    result.add(cloned);

                    memorized.clear();
                    beforeBoardCollectionId= currentBoardCollectionId;
                }
            }
            memorized.add(boards.get(i));
            if(i==boards.size()-1){
                ArrayList<Board> cloned=null;
                cloned=(ArrayList<Board>) memorized.clone();
                result.add(cloned);

                memorized.clear();
            }
        };

        return result;
    }
}