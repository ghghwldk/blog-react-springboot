import React, {useState, useEffect, useRef} from 'react';
import axios from 'axios'
import '@toast-ui/editor/dist/toastui-editor.css';
import './PostingEditor.css';
import 'codemirror/lib/codemirror.css';
import { Editor } from "@toast-ui/react-editor";
import { Link, Route, Switch } from 'react-router-dom';
import { useHistory, useParams } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faSearch, faPencilAlt, faSave, faMapMarkerAlt } from '@fortawesome/free-solid-svg-icons'
import {useSelector, connect, useDispatch} from 'react-redux'

const addZeroForOneDigit = (num)=>{
  if (num.toString().length == 1) {
    num = "0" + num;
  }
  return num
}

const changeDateTimeFormat=(before)=>{
  const date= before.date
  const time= before.time
  
  const after = date.year + '/' + addZeroForOneDigit(date.month) + '/' + addZeroForOneDigit(date.day) + ' ' +
  addZeroForOneDigit(time.hour) + ':' + addZeroForOneDigit(time.minute)
  
  return after
}

const Posting = ()=>{
  let state = useSelector((state) => state )
  const {boardCollectionId, boardId, postingId} = useParams()
  const [isEditMode, setIsEditMode] = useState(false)
  const [viewerHtml, setViewerHtml] = useState('');
  const [savedData, setSavedData] = useState(undefined);
  const [title, setTitle] = useState(undefined);
  const history = useHistory()

  const editorRef = useRef();
  // 1. load Html from database

  const isExisting = postingId!=undefined ? true : false 
  useEffect(()=>{
    if(isExisting){
      setIsEditMode(false)
      axios({
        url: `/api/posting?id=${postingId}`,
        method: 'GET',
        async: true
      }).then((res) => {
        setSavedData(res.data)
        const content = res.data.content 
        setTitle(res.data.title)
        setViewerHtml(content)
        const editorInstance = editorRef.current.getInstance()
        editorInstance.setHtml(content);
      })
    }
    else{
      setIsEditMode(true)
    }
  }, [])
  // 이미지를 url 방식으로 저장할 수 있게 하는 useEffect이다.
  useEffect(() => {
    if (editorRef.current) {
      editorRef.current.getInstance().removeHook("addImageBlobHook");
      editorRef.current
        .getInstance()
        .addHook("addImageBlobHook", (blob, callback) => {
          (async () => {
            let formData = new FormData();
            formData.append("file", blob);
            formData.append("postingId", postingId);

            axios.defaults.withCredentials = true;
            
            let downloadUrl=null;
            await axios
            .post(
              `/api/file/upload`,
              formData,
              {
                header: { "content-type": "multipart/formdata" },
              }
            ).then((res) => {
              downloadUrl=res.data.downloadUrl
            });
            callback(downloadUrl, "alt text");
          })();

          return false;
        });
    }

    return () => {};
  }, [editorRef]);

  const processToggle = ()=>{
    if(isEditMode){
      const editorInstance = editorRef.current.getInstance()
      setViewerHtml(editorInstance.getHtml())
    }
    
    setIsEditMode(!isEditMode)
  }

  const onTitleChange = (e) => {
    setTitle(e.target.value)
  }

  const save = () => {
    //const str= boardCollectionId+boardId+postingId
    // 신규 저장
    const editorInstance = editorRef.current.getInstance()
    const markup = editorInstance.getHtml()
    setViewerHtml(markup)
    const isPost = postingId === undefined ? true : false  
    if(isPost){
      const parameter = {
        'boardCollectionId': boardCollectionId,
        'boardId': boardId,
        'title': title,
        'content': markup,
      }

      axios({
        url: `/api/posting`,
        method: 'POST',
        data:JSON.stringify(parameter),
        headers: { 'content-type': 'application/json' },
        async: true
      }).then((res) => {
        // to show the board including new posting
        history.push(`/board/${boardCollectionId}/${boardId}`)
      })
    }else{
      // modification of the posting.
      const parameter = {
        'markup': markup,
        'boardCollectionId': boardCollectionId,
        'boardId': boardId,
        'postingId': postingId,
        'title': title,
      }

      axios({
        url: `/api/posting`,
        method: 'PUT',
        data:JSON.stringify(parameter),
        headers: { 'content-type': 'application/json' },
        async: true
      }).then((res) => {
        // 초깃값 세팅
        console.log(res)
      })
    }
  }
  return (
    <div className="Posting">
      <div id="info">
      <div>
          {savedData == undefined ? '':
            <Link to={`/board/${savedData.boardCollectionId}/${savedData.boardId}`}>
              <FontAwesomeIcon 
                icon={faMapMarkerAlt} 
              />&nbsp;{savedData.boardCollectionName+' | '+ savedData.boardName}
            </Link>  
          }
          {postingId!==undefined && state.role === 'master' && !isEditMode?(
              <FontAwesomeIcon 
                icon={faPencilAlt} 
                onClick={processToggle}
              />
            ):(
              <></>
            )
          }
          {postingId!==undefined && state.role === 'master' && isEditMode?(
              <FontAwesomeIcon 
                icon={faSearch} 
                onClick={processToggle}
              />
            ):(
              <></>
            )
          }
          {state.role === 'master' ? (
            <FontAwesomeIcon 
              icon={faSave} 
              onClick={save}
            />
            ):(
              <></>
            )
            
          }

          
        </div>
        {!isEditMode?(
          <h1>
            {savedData == undefined ? '': savedData.title}
          </h1>
        ):(
          <>
            <input
              value={title}
              onChange={onTitleChange}
            />
          </>
        )
        }
        <p className={!isEditMode? '': 'editor-off'}>
          {savedData == undefined ? '': savedData.createdTime}
          {/* {savedData == undefined ? '': changeDateTimeFormat(savedData.createdTime)} */}
        </p>
        
      </div>

      {/* down-side is for content */}
      <div className={!isEditMode? 'tui-editor-contents':'editor-off'}  dangerouslySetInnerHTML={{__html: viewerHtml}}></div>
      <div className={isEditMode? '': 'editor-off'}>
        <Editor
          
          usageStatistics={false}
          
          previewStyle='vertical'
          initialEditType="wysiwyg"
          onChange={() => {
            
          }}
          ref={editorRef}
          

          height='400px'
        />
      </div>
      
      

    </div>
  )
}

export default Posting;