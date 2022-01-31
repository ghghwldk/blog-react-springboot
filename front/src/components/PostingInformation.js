import React, {useState, useEffect} from 'react';
import { Link, Route, Switch } from 'react-router-dom';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faMapMarkerAlt} from '@fortawesome/free-solid-svg-icons'

const PostingInformation = ({componentName, board, posting, isHomePage})=>{

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
      <div className="posting-information">
        {(isHomePage)? (
          <div className="location">
            <Link to={`/board/${posting.boardCollectionId}/${posting.boardId}`}>
              <FontAwesomeIcon 
                icon={faMapMarkerAlt} 
              />&nbsp;{posting.boardCollectionName+' | '+posting.boardName}
            </Link>
          </div>  
        ):(
          <></>
        )
        }
        
        <Link className="title" to={`/postingEditor/${posting.boardCollectionId}/${posting.boardId}/${posting.postingId}`}>
          <font>
              {posting.title}
          </font>
        </Link>
        
        <p className="created-time">{changeDateTimeFormat(posting.createdTime)}</p>
      </div>
    </li>
  )
}

export default PostingInformation;