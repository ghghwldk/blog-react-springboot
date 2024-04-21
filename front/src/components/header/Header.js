import React, {useState, useEffect} from 'react';
import axios from 'axios'
import GnbItem from "./GnbItem"
import "./Header.scss";
import { Link, Route, Switch } from 'react-router-dom';
import {useSelector, connect, useDispatch} from 'react-redux'

import { useHistory, useParams } from 'react-router-dom';
//import "./HeaderMobile.css";
const Header = ()=>{
  const history= useHistory();
  
  let state = useSelector((state) => state )
  const [isMenuOpend, setIsMenuOpened] = useState(false);
  const [menuData, setMenuData] = useState([]);
  const [selectedBoardCollectionId, setSelectedBoardCollectionId] = useState(undefined)
  const dispatch = useDispatch()

  const setMenu = ()=> {
    axios({
      url: `/menu`,
      method: 'GET',
      async: false
    }).then((res) => {
      setMenuData(res.data.nesteds)
    })
  }

  useEffect(
    () => {
      setMenu()
    }, []
  )
  const authenticate =(condition)=>{
    if(condition === 'sign-in'){
      history.push(`/login`)
    }else{
      processLogout()
    }
  }

  const processLogout = ()=> {
    axios({
      url: `/auth`,
      method: 'DELETE',
      // data:JSON.stringify(parameter),
      headers: { 'content-type': 'application/json' },
      async: true
    }).then((res) => {
      dispatch({type:'register', payload : {role : '', id: ''} })
      history.push(`/`)
    })
  }

  return (
      <div className="Header">
          <header>
              <div>
                  <Link to="/" className="logo"><font>m</font></Link>
                  
                  <h2 className="hide">메뉴</h2>
                  <nav className="gnb"></nav>

                  {
                    state.id===''?
                    <button className= "custom-button"
                      onClick={()=>{
                        authenticate('sign-in')
                      }}
                    >로그인</button>:
                    <button className= "custom-button"
                      onClick={()=>{
                        authenticate('sign-out')
                      }}
                    >로그아웃</button>
                  }
                  {/* <Link to={state.id===''?'/login':'/logout'} className="btn_login">{state.id===''?'login':'logout'}</Link> */}

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
              <div>
                  <nav>
                      <ul className="megamenu">
                        {menuData.map((gnbData)=>(
                          <GnbItem
                            key = {gnbData.boardCollectionName}
                            boardCollectionName={gnbData.boardCollectionName}
                            boardCollectionId={gnbData.boardCollectionId}
                            postingCount = {gnbData.postingCount}
                            boards={gnbData.aggregationPerBoards}
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
          <div>{JSON.stringify(state)}</div>
      </div>
    )
}

export default Header;