import React, {useState, useEffect} from 'react';
import axios from 'axios'
import GnbItem from "./GnbItem"
import "./Header.css";
import { Link, Route, Switch } from 'react-router-dom';

//import "./HeaderMobile.css";
const Header = ()=>{
  const [isMenuOpend, setIsMenuOpened] = useState(false);
  const [menuData, setMenuData] = useState([]);
  const [selectedBoardCollectionId, setSelectedBoardCollectionId] = useState(undefined)

  const setMenu = ()=> {
    axios({
      url: `/board_collection/list/group_by_board_collection_id`,
      method: 'GET',
      async: false
    }).then((res) => {
      setMenuData(res.data)
    })
  }

  useEffect(
    () => {
      setMenu()
    }, []
  )


  return (
      <div className="Header">
          <header>
              <div>
                
                  <Link to="/"><h1>M's Blog</h1></Link>
                  
                  <h2 className="hide">메뉴</h2>
                  <nav className="gnb"></nav>
                  <button className="btn_nav"
                    onClick={()=>{
                      setIsMenuOpened(isMenuOpend=> !isMenuOpend)
                    }}
                  ><span className="hide">전체 메뉴</span></button>
              </div>
          </header>
          <div className={`total_nav ${isMenuOpend ? 'opened' : ''}`}>
              <h2 className="hide">전체 메뉴</h2>
              <p className="logo"><span className="hide">리베하얀</span></p>
              <a href="" className="txt_login"><span>로그인</span>을 해주세요.</a>
              <div>
                  <nav>
                      <ul className="megamenu">
                        {menuData.map((gnbData)=>(
                          <GnbItem
                            key = {gnbData.boardCollectionName}
                            boardCollectionName={gnbData.boardCollectionName}
                            boardCollectionId={gnbData.boardCollectionId}
                            boards={gnbData.boards}
                            selectedBoardCollectionId = {selectedBoardCollectionId}
                            setSelectedBoardCollectionId = {setSelectedBoardCollectionId}
                          />
                        ))}
                      </ul>
                      <button className="close"
                        onClick={()=>{
                          setIsMenuOpened(isMenuOpend=> !isMenuOpend)
                        }}
                      ><span className="hide">전체 메뉴 닫기</span></button>
                  </nav>
              </div>
          </div>

          
          <div 
            className={`dim ${isMenuOpend ? 'opened' : ''}`}
            onClick={()=>{
              setIsMenuOpened(isMenuOpend=> !isMenuOpend)
            }}
          >

          </div>
      </div>
    )
}

export default Header;