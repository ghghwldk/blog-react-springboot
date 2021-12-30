import React, {useState, useEffect} from 'react';
import axios from 'axios'
import "./ItemList.css";
import { useHistory, useParams } from 'react-router-dom';
import Item from '../Item'
import '../Pagination.css';
import Pagination from 'react-js-pagination';

const Board = ()=>{
  const { boardCollectionId, boardId } = useParams();
  const [itemDatas, setItemDatas] = useState([]);
  const [totalPage, setTotalPage] = useState(0);
  const [page, setPage] = useState(1); 

  const handlePageChange = (page) => { 
    setPage(page); 
    changeItemDatas(page)
  };
  
  const changeItemDatas = (page)=>{
    axios({
      url: `/posting/list?size=10&page=${page-1}&boardId=${boardId}&boardCollectionId=${boardCollectionId}`,
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
    }, []
  )
  return (
    <div id="Board">
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
      
      <ul>
          {itemDatas.map((posting, i)=>(
            <Item
              key={posting.boardCollectionId, posting.boardId, posting.postingId}

              componentName = "board"
              posting={posting}
            />
          ))}
        </ul>

    </div>
  )
}

export default Board;