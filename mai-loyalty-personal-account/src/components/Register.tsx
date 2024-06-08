import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";

interface UserRegisterRequest {
  username: string;
  password: string;
  mail: string;
  company: string;
}

const Register: React.FC = () => {

  const [user, setUser] = useState<UserRegisterRequest>({
    username: '',
    password: '',
    mail: '',
    company: '',
  });
  const navigate = useNavigate();
  const onChangeUsername = (username: string) => setUser({...user, username})
  const onChangePassword = (password: string) => setUser({...user, password})
  const onChangeMail = (mail: string) => setUser({...user, mail})
  const onChangeCompany = (company: string) => setUser({...user, company})
  const onSubmit = () => {
    axios.post('http://localhost:8080/api/personal-account/register', {...user}, {})
      .then(_ => navigate('/login'))
      .catch(err => console.log(err))
  }

  return (
    <div className="container center">
      <div className="row">
        <form className="col s6 offset-s3">
          <div className="input-field">
            <input id="first_name" type="text" className="validate"
                   value={user?.username}
                   onChange={event => onChangeUsername(event.target.value)}
            />
            <label htmlFor="first_name">Логин</label>
          </div>
          <div className="input-field">
            <input id="last_name" type="password" className="validate"
                   value={user?.password}
                   onChange={event => onChangePassword(event.target.value)}
            />
            <label htmlFor="last_name">Пароль</label>
          </div>
          <div className="input-field">
            <input id="mail" type="email" className="validate"
                   value={user?.mail}
                   onChange={event => onChangeMail(event.target.value)}
            />
            <label htmlFor="mail">Email</label>
          </div>
          <div className="input-field">
            <input id="company" type="text" className="validate"
                   value={user?.company}
                   onChange={event => onChangeCompany(event.target.value)}
            />
            <label htmlFor="company">Компания</label>
          </div>
        </form>
      </div>
      <button className="btn waves-effect waves-light" name="action" onClick={onSubmit}>Зарегистрироваться</button>
    </div>
  )
}

export default Register
