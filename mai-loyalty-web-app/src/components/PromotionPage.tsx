import PromotionCard from "./PromotionCard";
import {useEffect, useState} from "react";
import {Promotion} from "./Promotion";
import {TextField} from "@mui/material";

const PromotionPage = () => {

  const [promotions, setPromotions] = useState<Promotion[]>([])
  const [isLoad, setLoad] = useState<boolean>(true)
  const [err, setErr] = useState<string>('')

  const getPromotionsPage = (searchQuery: string | null) => {
    let url = 'https://loyalty-platform.ru/api/webApp/promotions'
    if (searchQuery) {
      url += `?searchQuery=${searchQuery}`
    }
    fetch(url)
      .then(response => response.json())
      .then(response => response.promotions)
      .then(promotionsPage => setPromotions(promotionsPage))
      .then(() => setLoad(false))
      .catch(reason => setErr(reason.toString()))
  }

  useEffect(() => getPromotionsPage(null), []);

  return (
    <>
      <p>Err {err}</p>
      <TextField
        id="filled-basic"
        label="Search"
        onChange={e => getPromotionsPage(e.target.value)}
        style={{background: "gray", borderRadius: "20px", minWidth: "345px", marginBottom: 20}}
      />
      {isLoad ? <h1 className="center">Loading...</h1> : promotions?.map(promotion => (
        <PromotionCard {...promotion}></PromotionCard>
      ))}
    </>
  )
};

export default PromotionPage;
