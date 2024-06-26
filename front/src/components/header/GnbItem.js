import React, {useState, useEffect} from 'react';
import "./GnbItem.css";
import { Link, Route, Switch } from 'react-router-dom';
import ico_arrow_down2 from '../../images/ico_arrow_down2.png'

const GnbItem = ({boardCollectionName,boardCollectionId, postingCount, boards, selectedBoardCollectionId, setSelectedBoardCollectionId})=>{
    return (
      <li 
        className={`${boardCollectionName} ${selectedBoardCollectionId==boardCollectionId?'on':''}`}
        onClick={()=>{
          if(selectedBoardCollectionId === boardCollectionId){
            setSelectedBoardCollectionId(undefined)
          }else{
            setSelectedBoardCollectionId(boardCollectionId)
          }
        }}
      >
        <p to={`/boardCollection/${boardCollectionId}`}>{boardCollectionName}{' ('+postingCount+'개의 항목)'}</p>
        
        <div>
          <ul>
            {boards.map(board=>(
              <li key={board.boardCollectionId, board.boardId}>
                <Link 
                  to={`/board/${boardCollectionId}/${board.boardId}`}
                ><span>{board.boardName}{' ('+board.postingCount+'개의 글)'}</span></Link>
                
              </li>
            ))}
          </ul>
        </div>
      </li>
    )
}

export default GnbItem;