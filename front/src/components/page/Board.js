import React, {useState, useEffect} from 'react';
import axios from 'axios'
import "./Board.scss";
import { useHistory, useParams } from 'react-router-dom';
import PostingInformation from '../PostingInformation'
import '../Pagination.css';
import Pagination from 'react-js-pagination';
import { Link, Route, Switch } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faPlusCircle} from '@fortawesome/free-solid-svg-icons'
import {useSelector, connect, useDispatch} from 'react-redux'
import { faMapMarkerAlt} from '@fortawesome/free-solid-svg-icons'

const Board = ()=>{
  let state = useSelector((state) => state )
  const { boardCollectionId, boardId } = useParams();
  const [itemDatas, setItemDatas] = useState([]);
  const [totalPages, setTotalPages] = useState(0);
  const [page, setPage] = useState(1); 
  const [isHomePage, setIsHomePage] = useState(undefined);
  const [postingCount, setPostingCount] = useState(0);
  const [location, setLocation] = useState('');
  const handlePageChange = (page) => { 
    setPage(page); 
    changeItemDatas(page)
  };
  
  const changeItemDatas = (page)=>{
    let url= null
    if(isHomePage !==undefined){
      if(isHomePage){
        url = `/posting/list?page=${page-1}&size=10`
      }
      else{
        url = `/posting/list-per-board?size=10&page=${page-1}&boardId=${boardId}&boardCollectionId=${boardCollectionId}`
      }
      axios({
        url: url,
        method: 'GET',
        async: true
      }).then((res) => {
        setPostingCount(res.data.totalElements);
        setItemDatas(itemDatas.filter(i => false));
        
        res.data.content.forEach(e=>{
            setItemDatas(itemData=> itemData.concat(e))
        })
        setTotalPages(res.data.totalPages)
        setLocation(res.data.location)
        
      })
    }
  }

  useEffect(
    () => {
      if(boardCollectionId ===undefined && boardId === undefined) {
        setIsHomePage(true)
      }else{
        setIsHomePage(false)
      }
    }, [boardCollectionId, boardId]
  )
  useEffect(
    () => {
      changeItemDatas(page)  
    }, [boardCollectionId, boardId, isHomePage]
  )
  return (
    <div className="Board">
      <div className="location">
          {/* {(isHomePage)?(
            <Link to={isHomePage ===true ? `/` : 
                ``}>
                <FontAwesomeIcon 
                  icon={faMapMarkerAlt} 
                />&nbsp;Home
                <font>{' ('+postingCount+')'}</font>
              </Link>
            ):(
            <Link to={isHomePage ===true ? `/` :``}>
              <FontAwesomeIcon 
                icon={faMapMarkerAlt} 
              />&nbsp;Home x
              <font>{' ('+postingCount+')'}</font>
            </Link>
            )
          } */}
          {
            <Link to={isHomePage ===true ? `/` :`/board/${boardCollectionId}/${boardId}`}>
              <FontAwesomeIcon 
                icon={faMapMarkerAlt} 
              />&nbsp;{`${location}`}
              <font>{' ('+postingCount+')'}</font>
            </Link>
          }
        </div>
        
        
        <ul id="posting-information-list">
            {itemDatas.map((posting, i)=>(
              <PostingInformation
                key={posting}

                componentName = "board"
                posting={posting}
                isHomePage={isHomePage}
              />
            ))}
          </ul>
        {/* if this component is for the specific board, then the button must be setted to change the page. 
        destination is the component to make the new posting */}
        {!(isHomePage) && state.role === 'master' ?(
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
      <div className="pagination-container">
        {totalPages!==0?(
          <Pagination
            activePage={page} 
            itemsCountPerPage={10} 
            totalItemsCount={totalPages*10} 
            pageRangeDisplayed={5} 
            prevPageText={"‹"} 
            nextPageText={"›"} 
            onChange={handlePageChange} 
          />
        ):(
          <></>
        )
        }

      </div>
    </div>
  )
  
}

export default Board;