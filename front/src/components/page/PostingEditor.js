import React, {useState, useEffect, useRef} from 'react';
import axios from 'axios'
import './PostingEditor.css';
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from "@toast-ui/react-editor";
import { Link, Route, Switch } from 'react-router-dom';
import { useHistory, useParams } from 'react-router-dom';


const Posting = ()=>{
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
        url: `/posting/data?boardCollectionId=${boardCollectionId}&boardId=${boardId}&id=${postingId}`,
        method: 'GET',
        async: true
      }).then((res) => {
        // 초깃값 세팅
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

            axios.defaults.withCredentials = true;
            
            let downloadUrl=null;
            await axios
            .post(
              `/file/upload`,
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

  
  const changeDateTimeFormat=(before)=>{
    const date= before.date
    const time= before.time
    const after = date.year + '/' + date.month + '/' + date.day + ' ' +
        time.hour + ':' + time.minute
    return after
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
        url: `/posting/data/insert/posting`,
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
        url: `/posting/data/update`,
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
          {postingId!==undefined?(
              <button style={{marginTop:'10px'}} onClick={processToggle}>{!isEditMode? 'edit' : 'preview' }</button>
            ):(
            ''
            )
          }
          <button style={{marginLeft: '10px', marginTop:'10px'}} onClick={save}>save</button>
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
        
        
        <h6 className={!isEditMode? '': 'editor-off'}>
          {savedData == undefined ? '': changeDateTimeFormat(savedData.createdTime)}
        </h6>
        
      </div>

      {/* down-side is for content */}
      <div className={!isEditMode? 'tui-editor-contents':'editor-off'}  dangerouslySetInnerHTML={{__html: viewerHtml}}></div>
      <div className={isEditMode? '': 'editor-off'}>
        <Editor
          
          usageStatistics={false}
          
          previewStyle='vertical'
          initialEditType="wysiwyg"
          useCommandShortcut={true}
          onChange={() => {
            
          }}
          ref={editorRef}

          height='800px'
        />
      </div>
      

    </div>
  )
}

export default Posting;