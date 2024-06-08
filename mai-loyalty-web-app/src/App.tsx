import React from 'react';
import './App.css';
import {useInitData} from "@vkruglikov/react-telegram-web-app";
import PromotionPage from "./components/PromotionPage";

const App = () => {

  // const [initDataUnsafe] = useInitData();

  return (
    <div className="App">
      <header className="App-header">
        {/*<p>Hi {initDataUnsafe?.user?.username} </p>*/}
        <PromotionPage></PromotionPage>
      </header>
    </div>
  );
}

export default App;
