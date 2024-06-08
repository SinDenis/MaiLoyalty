import React from "react";
import {NavLink} from "react-router-dom";
import Cookies from "universal-cookie";

const Navigation: React.FC = () => {
  return (

    <nav className="nav-wrapper px1">
      <div className="container">
        <a href="#" className="brand-logo">Mai Loyalty</a>
        <ul id="nav-mobile" className="right hide-on-med-and-down">
          <li><NavLink to="/main">Мои акции</NavLink></li>
          <li><NavLink to="/promo">Создать акцию</NavLink></li>
          <li><NavLink to="/login">Войти</NavLink></li>
          <li><NavLink to="/register">Регистрация</NavLink></li>
          <li><a href="#" onClick={() => new Cookies().remove('jwt')}>Выход</a></li>
        </ul>
      </div>
    </nav>

  )
}

export default Navigation
