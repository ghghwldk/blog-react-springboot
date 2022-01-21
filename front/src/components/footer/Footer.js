import React, {useState, useEffect} from 'react';
import "./Footer.css";
import growth from '../../images/growth.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faHistory} from '@fortawesome/free-solid-svg-icons'

const Footer = ()=>{

  return (
    <div className='Footer'>
        <img 
          src={growth}
          width='80'
          alt='growth'  
        />
        <p>성장하는 개발자가 되자!</p>
        <p>ghghwldk@gmail.com</p>
        
        <div className="history">
          <FontAwesomeIcon 
            icon={faHistory}
            className="history-icon" 
            // onClick={save}
          />
        </div>
        
    </div>
  )
}

export default Footer;