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
  const [html, setHtml] = useState('');

  const editorRef = useRef();
  // 1. load Html from database
  useEffect(()=>{
    if(postingId!=undefined){
      setIsEditMode(false)
      axios({
        url: `/posting/data?boardCollectionId=${boardCollectionId}&boardId=${boardId}&id=${postingId}`,
        method: 'GET',
        async: true
      }).then((res) => {
        // 초깃값 세팅
        const content = res.data.content 
        setHtml(content)
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
      setHtml(editorInstance.getHtml())
    }
    
    setIsEditMode(!isEditMode)
  }

  const save = () => {
    //const str= boardCollectionId+boardId+postingId
    // 신규 저장
    if(postingId===undefined){
      
    }else{
      // 글 수정
      const editorInstance = editorRef.current.getInstance()
      const markup = editorInstance.getHtml()
      setHtml(markup)
      debugger
      const parameter = {
        'markup': markup,
        'boardCollectionId': boardCollectionId,
        'boardId': boardId,
        'postingId': postingId,
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
    const editorInstance = editorRef.current.getInstance()
    const getContent_html = editorInstance.getHtml();
    //editorInstance.setHtml("<p>스터디에 대한 정보를 간략히 작성해asdf주세요 '-'</p>");
  }
  return (
    <div>
      <div className={!isEditMode? 'tui-editor-contents':'editor-off'}  dangerouslySetInnerHTML={{__html: html}}></div>
      <div className={isEditMode? '': 'editor-off'}>
        <Editor
          
          usageStatistics={false}
          
          previewStyle='vertical'
          initialEditType="wysiwyg"
          useCommandShortcut={true}
          onChange={() => {
            
          }}
          ref={editorRef}
        />
      </div>
      {postingId!==undefined?(
        <div>
          <button style={{marginTop:'10px'}} onClick={processToggle}>{!isEditMode? '수정' : '미리보기' }</button>
        </div>
        ):(
        ''
        )
      }
      <button style={{marginTop:'10px'}} onClick={save}>저장</button>
    </div>
  )
}

export default Posting;