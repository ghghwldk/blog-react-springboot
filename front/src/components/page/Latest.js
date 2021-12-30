import React, {useState, useEffect} from 'react';
import axios from 'axios'
import Item from "../Item"
import Pagination from 'react-js-pagination';
import "./ItemList.css";
import '../Pagination.css';


const Latest = ()=>{
  const [content, setContent] = useState([]);
  const [totalPage, setTotalPage] = useState(0);
  const [page, setPage] = useState(1); 

  const handlePageChange = (page) => { 
    setPage(page); 
    changeItems(page)
  };
  
  const changeItems = (page)=>{
    axios({
      url: `/posting/list/latest?page=${page-1}&size=10`,
      method: 'GET',
      async: true
    }).then((res) => {
      setContent(content.filter(c => false));
      res.data.content.forEach(e=>{
          setContent(content=> content.concat(e))
      })
      setTotalPage(res.data.totalPage)
    })
  }

  useEffect(
    () => {
      changeItems(page)  
    }, []
  )
  
  return (
      <div id="Latest">
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
          {content.map((posting, i)=>(
            <Item
              key={posting.boardCollectionId, posting.boardId, posting.postingId}
              
              componentName="board"
              posting={posting}
            />
          ))}
        </ul>
      </div>
  )
}

export default Latest;