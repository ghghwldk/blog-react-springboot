import React, { useState, useEffect } from 'react';
import Header from './components/header/Header'
import Footer from './components/footer/Footer'

import PostingEditor from './components/page/PostingEditor'
import BoardCollection from './components/page/BoardCollection'
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
      <Route exact path="/"> <Board/> </Route>  

      <Route exact path="/boardCollection/:boardCollectionId"> <BoardCollection/> </Route>
      <Route exact path="/board/:boardCollectionId/:boardId"> <Board/> </Route> 
      
      <Route exact path="/postingEditor/:boardCollectionId/:boardId/:postingId" > <PostingEditor/> </Route> 
      <Route exact path="/postingEditor/:boardCollectionId/:boardId" > <PostingEditor/> </Route> 
      <Route exact path="/login" > <Login/> </Route>
      
      <Footer/>
    </div>
  );
}

export default App;