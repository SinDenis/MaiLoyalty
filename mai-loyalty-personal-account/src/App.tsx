import React from 'react';
import './App.css';
import {Route, Routes} from "react-router-dom";
import Login from "./components/Login";
import Register from "./components/Register";
import Main from "./components/Main";
import Navigation from "./components/Navigation";
import ErrorPage from "./components/ErrorPage";
import Promo from "./components/Promo";

const App: React.FC = () => {

  return (
    <>
      <Navigation/>
      <Routes>
        <Route index element={<Main />} />
        <Route path="main" element={<Main />} />
        <Route path="promo" element={<Promo />} />
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Register />} />
        <Route path="logout" element={<Register />} />
        <Route path="*" element={<ErrorPage />} />
      </Routes>
    </>

  )

}
export default App;
