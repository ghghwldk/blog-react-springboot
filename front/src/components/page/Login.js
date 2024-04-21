import React, {useState, useEffect, useRef, useMemo} from 'react';
import axios from 'axios'
import "./Login.css";
import { Link, Route, Switch, useLocation} from 'react-router-dom';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';

import {useSelector, connect, useDispatch} from 'react-redux'

function useQuery() {
  const history = useHistory()
  const { search } = useLocation();

  return React.useMemo(() => new URLSearchParams(search), [search]);
}

const Login = ()=>{
  let state = useSelector((state) => state )
  const idRef = useRef();
  const passwordRef = useRef();
  let query= useQuery();
  const history = useHistory()
  const dispatch = useDispatch()
  const activeEnter = (e) => {
    if(e.key === "Enter") {
      debugger
      processLogin()
    }
  }
  const processLogin = ()=> {
    const id = idRef.current.value
    const password = passwordRef.current.value
    
    const parameter = {
      'userId': id,
      'password': password, 
    }
    axios({
      url: `/member/login`,
      method: 'POST',
      data:JSON.stringify(parameter),
      headers: { 'content-type': 'application/json' },
      async: true
    }).then((res) => {
      const redirectUrl = query.get("redirectUrl")
      const role = res.data.role
      // set redux.
      dispatch({type:'register', payload : {role : role, id: id} }) 

      if(redirectUrl=== null){
        history.push(`/`)
      } else {
        history.push(redirectUrl)
      }
    }).catch(err=>{
    });
  }

  useEffect(
    () => {
    }, []
  )

  return (
      <div className="Login">
        <div>{state.role}</div>
        <p>ID</p>
        <input id="id" name="id" type="text"
          ref= {idRef}
          onKeyDown={(e) => activeEnter(e)}
        ></input>
        <p>PW</p>
        <input id="password" name="password" type="password"
          ref= {passwordRef}
          onKeyDown={(e) => activeEnter(e)}
        ></input>
        <button className="custom-button" id="submit"
          onClick={()=>{
            processLogin()
          }}
        >
          로그인
        </button>
      </div>
    )
}

export default Login
// function state를props화(state){
//   return {
//     state : state
//   }
// }

// export default connect(state를props화)(Login);