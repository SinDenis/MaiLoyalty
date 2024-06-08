import React, {useEffect, useState} from "react";
import axios from "axios";
import PromoItem from "./PromoItem";

export type Promotion = {
  id: number;
  title: string;
  description: string;
  category: string
}

type PromotionResponse = {
  promotions: Promotion[];
}

const Main: React.FC = () => {

  const [promotions, setPromotions] = useState<Promotion[]>([])
  const [isLoad, setLoad] = useState<boolean>(true)

  const getPromotionsPage = () => {
    let url = 'http://localhost:8080/api/personal-account/promotions'
    axios.get<PromotionResponse>(url, {withCredentials: true})
      .then(response => response.data.promotions)
      .then(promotionsPage => setPromotions(promotionsPage))
      .then(() => setLoad(false))
  }

  useEffect(() => getPromotionsPage(), []);

  return <div
    style={{display: "flex", justifyContent: "space-around", alignItems: "flex-end", flexWrap: "wrap", marginTop: 20}}>
    {isLoad ? <h1 className="center">Loading...</h1> : promotions?.map(promotion => (
      <PromoItem {...promotion}/>
    ))}
  </div>

}

export default Main
