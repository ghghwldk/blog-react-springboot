import React, {useState, useEffect, useRef} from 'react';
import axios from 'axios'
import './PostingViewer.css';
import { useHistory, useParams } from 'react-router-dom';

import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Viewer } from '@toast-ui/react-editor';
import { Link, Route, Switch } from 'react-router-dom';

const Posting = ()=>{
  const {boardCollectionId, boardId, postingId} = useParams()
  
  return (
    <div className="Posting">


      <Viewer initialValue={`<p>스터디에 대한 정보를 간략히 작성해asdf주세요 '-'asd\\</p>
                            <h1>ㅁㄴㅇㄹ</h1>
                            <h2>ㅁㄴㅇㄹ</h2>
                            <h3>ㅁㄴㅇㄹ</h3>
                            <h4>ㅁㄴㅇㄹ</h4>
                            <hr>
                            <p>ㅁㄴㅇㄹ</p>
                            <pre><code>ㅁㄴㅇㄹ
                              ㅁㄴㅇㄻㄴㅇㄹㄹㄹㄹ
                            진행된다는 점을 기억해야 한다.
                            </code></pre>`} 
      />
      <Link to={`/postingEdit/${boardCollectionId}/${boardId}/${postingId}`}>
        <button>수정</button>
      </Link>
    </div>
  )
}

export default Posting;