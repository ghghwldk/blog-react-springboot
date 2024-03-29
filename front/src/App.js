import React, { useState, useEffect } from 'react';
import Header from './components/header/Header'
import Footer from './components/footer/Footer'

import PostingEditor from './components/page/PostingEditor'
import Board from './components/page/Board'
import Login from './components/page/Login'
import './common.js';
import './common.css';
import './App.css';
import { Link, Route, Switch } from 'react-router-dom';


function App() {


  return (
    <div className="App">
      <Header/>
      <div className="main-content">
        <Route exact path="/board/:boardCollectionId/:boardId"> <Board/> </Route> 
        
        <Route exact path="/postingEditor/:boardCollectionId/:boardId/:postingId" > <PostingEditor/> </Route> 
        <Route exact path="/postingEditor/:boardCollectionId/:boardId" > <PostingEditor/> </Route> 
        <Route exact path="/login" > <Login/> </Route>
        <Route exact path="/"> <Board/> </Route>  
      </div>
      
      <Footer/>
    </div>
  );
}

export default App;