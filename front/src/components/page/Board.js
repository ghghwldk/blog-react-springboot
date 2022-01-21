import React, {useState, useEffect} from 'react';
import axios from 'axios'
import "./ItemList.css";
import { useHistory, useParams } from 'react-router-dom';
import Item from '../Item'
import '../Pagination.css';
import Pagination from 'react-js-pagination';
import { Link, Route, Switch } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faPlusCircle} from '@fortawesome/free-solid-svg-icons'
import {useSelector, connect, useDispatch} from 'react-redux'

const Board = ()=>{
  let state = useSelector((state) => state )
  const { boardCollectionId, boardId } = useParams();
  const [itemDatas, setItemDatas] = useState([]);
  const [totalPage, setTotalPage] = useState(0);
  const [page, setPage] = useState(1); 

  const handlePageChange = (page) => { 
    setPage(page); 
    changeItemDatas(page)
  };
  
  const changeItemDatas = (page)=>{
    // board일 경우
    let url= null
    if(boardCollectionId === undefined
        && boardId === undefined
      ){
      url = `/posting/list/latest?page=${page-1}&size=10`
    }
    else{
      url = `/posting/list?size=10&page=${page-1}&boardId=${boardId}&boardCollectionId=${boardCollectionId}`
    }
    
    axios({
        url: url,
        method: 'GET',
        async: true
      }).then((res) => {
        setItemDatas(itemDatas.filter(i => false));
        
        res.data.content.forEach(e=>{
            setItemDatas(itemData=> itemData.concat(e))
        })
        setTotalPage(res.data.totalPage)
      })
  }

  useEffect(
    () => {
      changeItemDatas(page)  
    }, [boardCollectionId, boardId]
  )
  return (
    <div id="Board">
      {totalPage!==0?(
        <div>
          <Pagination
            activePage={page} 
            itemsCountPerPage={10} 
            totalItemsCount={totalPage*10} 
            pageRangeDisplayed={5} 
            prevPageText={"‹"} 
            nextPageText={"›"} 
            onChange={handlePageChange} 
          />
        </div>
      ):(
        <></>
      )
      }
      
      <ul>
          {itemDatas.map((posting, i)=>(
            <Item
              key={posting.boardCollectionId, posting.boardId, posting.postingId}

              componentName = "board"
              posting={posting}
            />
          ))}
        </ul>
      {/* if this component is for the specific board, then the button must be setted to change the page. 
      destination is the component to make the new posting */}
      {!(boardCollectionId ===undefined && boardId === undefined) && state.role === 'master' ?(
        <div>
          
          <Link to={`/postingEditor/${boardCollectionId}/${boardId}`}>
            <FontAwesomeIcon 
              className='add-posting'
              icon={faPlusCircle}
            />
          </Link>
        </div>
        ):(
          <></>
        )
      }
    </div>
  )
  
}

export default Board;