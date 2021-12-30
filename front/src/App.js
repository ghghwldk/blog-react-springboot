import React, { useState, useEffect } from 'react';
import Header from './components/header/Header'
import Latest from './components/page/Latest'
import PostingEditor from './components/page/PostingEditor'
import PostingViewer from './components/page/PostingViewer'
import BoardCollection from './components/page/BoardCollection'
import Board from './components/page/Board'
import './common.js';
import './common.css';
import './App.css';
import { Link, Route, Switch } from 'react-router-dom';


function App() {


  return (
      <div className="App">
        <Header/>
        <Route exact path="/"> <Latest/> </Route>  

        <Route exact path="/boardCollection/:boardCollectionId"> <BoardCollection/> </Route>
        <Route exact path="/board/:boardCollectionId/:boardId"> <Board/> </Route> 
        <Route exact path="/postingViewer/:boardCollectionId/:boardId/:postingId"> <PostingViewer/> </Route> 
        
        <Route exact path="/postingEditor/:boardCollectionId/:boardId/:postingId" > <PostingEditor/> </Route> 
        <Route exact path="/postingEditor/:boardCollectionId/:boardId" > <PostingEditor/> </Route> 
        
        <div>
          <Link to="/">Latest</Link>
        </div>
        <div>
          <Link to={`/posting/1/2/3`}>posting</Link>
        </div>
        <div>
          <Link to="/posting-new">posting-new</Link>
        </div>
        
      </div>
  );
}

export default App;