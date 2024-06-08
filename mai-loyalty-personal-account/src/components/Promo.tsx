import React, {useState} from 'react';
import axios from "axios";
import {useNavigate} from "react-router-dom";

const Promo = () => {
  const [formData, setFormData] = useState({
    title: '',
    description: '',
    category: '',
    region: '',
    ageFrom: '',
    ageTo: '',
  });

  const navigate =  useNavigate()

  const handleSubmit = (e: any) => {
    e.preventDefault();
    axios.post('http://localhost:8080/api/personal-account/promotions', formData, {withCredentials: true})
      .then(response => {
        if (response.status === 200) {
         navigate('/main')
        }
      })
  };

  const handleChange = (e: any) => {
    setFormData((prevData) => ({
      ...prevData,
      [e.target.name]: e.target.value
    }));
  };

  return (
    <div className="container center">
      <div className="row">
        <form className="col s6 offset-s3">
          <div className="input-field">
            <input id="title"
                   type="text"
                   name="title"
                   value={formData.title}
                   onChange={handleChange}
            />
            <label htmlFor="title">Заголовок</label>
          </div>
          <div className="input-field">
            <input id="description"
                   type="text"
                   name="description"
                   value={formData.description}
                   onChange={handleChange}
            />
            <label htmlFor="description">Описание</label>
          </div>
          <div className="input-field">
            <input id="category"
                   type="text"
                   name="category"
                   value={formData.category}
                   onChange={handleChange}
            />
            <label htmlFor="category">Категория</label>
          </div>
          <div className="input-field">
            <input id="region"
                   type="number"
                   name="region"
                   value={formData.region}
                   onChange={handleChange}
            />
            <label htmlFor="region">Регион</label>
          </div>
          <div className="input-field">
            <input id="ageFrom"
                   type="number"
                   name="ageFrom"
                   value={formData.ageFrom}
                   onChange={handleChange}
            />
            <label htmlFor="region">Возраст от</label>
          </div>
          <div className="input-field">
            <input id="ageTo"
                   type="number"
                   name="ageTo"
                   value={formData.ageTo}
                   onChange={handleChange}
            />
            <label htmlFor="region">Возраст до</label>
          </div>
          <button className="btn waves-effect waves-light" name="action" onClick={handleSubmit}>Создать</button>
        </form>
      </div>
    </div>
  );
};

export default Promo;
