import React, {useState, useEffect} from 'react';
import "./ItemList.css";
import { useHistory, useParams } from 'react-router-dom';
import axios from 'axios'
import Item from '../Item'
import '../Pagination.css';
import Pagination from 'react-js-pagination';

const BoardCollection = ()=>{
  const { boardCollectionId } = useParams();
  const [itemDatas, setItemDatas] = useState([]);
  const [totalPage, setTotalPage] = useState(0);
  const [page, setPage] = useState(1); 

  const handlePageChange = (page) => { 
    setPage(page); 
    changeItemDatas(page)
  };
  const changeItemDatas = (page)=>{
    axios({
      url: `/board/list/${boardCollectionId}?size=10&page=${page-1}`,
      method: 'GET',
      async: true
    }).then((res) => {
      console.log(res)
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
    <div id="BoardCollection">
      <div>
        boardCollection Id는 {boardCollectionId} 입니다.
      </div>
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
          {itemDatas.map((board, i)=>(
            <Item
              key={board.boardCollectionId, board.boardId}
              
              componentName="BoardCollection"
              board={board}
            />
          ))}
      </ul>
    </div>
    
  )
}

export default BoardCollection;