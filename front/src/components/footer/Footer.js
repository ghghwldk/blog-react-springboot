import React, {useState, useEffect} from 'react';
import "./Footer.scss";
import growth from '../../images/growth.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faHistory} from '@fortawesome/free-solid-svg-icons'

const Footer = ()=>{

  return (
    <div className='Footer'>
        <button className= "custom-button"
                        onClick={()=>{
                          window.open('https://github.com/ghghwldk')
                        }}
                      >깃허브</button>
        <font>ghghwldk@gmail.com</font>
    </div>
  )
}

export default Footer;