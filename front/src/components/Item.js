import React, {useState, useEffect} from 'react';
import "./Item.css";
import { Link, Route, Switch } from 'react-router-dom';


<Link to={`/posting/1/2/3`}>posting</Link>

const Item = ({componentName, board, posting})=>{

  const addZeroForOneDigit = (num)=>{
    if (num.toString().length == 1) {
      num = "0" + num;
    }
    return num
  }
  
  const changeDateTimeFormat=(before)=>{
    const date= before.date
    const time= before.time
    
    const after = date.year + '/' + addZeroForOneDigit(date.month) + '/' + addZeroForOneDigit(date.day) + ' ' +
    addZeroForOneDigit(time.hour) + ':' + addZeroForOneDigit(time.minute)
    
    return after
  }

  return (
    <li>
      {
        componentName==='board' ? (
          <div>
        <div className="posting-link">
          <Link to={`/postingEditor/${posting.boardCollectionId}/${posting.boardId}/${posting.postingId}`}>
            <div>
                {posting.title}
            </div>
            {/* <div>
                {posting.content}
            </div> */}
          </Link>
        </div>
        <div>
          <div className="posting-info">
            
            <Link to={`/board/${posting.boardCollectionId}/${posting.boardId}`}>
              {posting.boardCollectionName+' > '+posting.boardName}
            </Link>
            <span>|&nbsp;</span>
            <span>{changeDateTimeFormat(posting.createdTime)}</span>
          
          </div>
        </div>
      </div>
        ):
        (
          <div>
            <div className="posting-link">
              <Link to={`/board/${board.boardCollectionId}/${board.boardId}`}>
                <div>
                    {board.boardName}
                </div>
                <div>
                    {board.description}
                </div>
              </Link>
            </div>
            <div>
              <div className="posting-info">
                <div>
                  <Link to={`/boardCollection/${board.boardCollectionId}`}>
                    {board.boardCollectionName}
                  </Link>
                  {/* <span>|</span>
                  <Link to={`/board/${board.boardCollectionId}/${board.boardId}`}>
                    {board.boardName}
                  </Link> */}
                  <span>|&nbsp;</span>
                  <span>{changeDateTimeFormat(board.createdTime)}</span>
                </div>
              </div>
            </div>
          </div>
        )

      }
      
    </li>
  )
}

export default Item;