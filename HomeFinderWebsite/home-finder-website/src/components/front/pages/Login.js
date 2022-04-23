import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import AuthManager from "../../../classes/AuthManager";
import ButtonStandart from "../../small-elements/ButtonStandart";

const Login = (props) => {
  const navigate = useNavigate();
  const [isError, setIsError] = useState(false);

  useEffect(() => {
    if (AuthManager.CheckLoginUser()) navigate("/user-profile");
  }, []);

  const onSubmit = (e) => {
    e.preventDefault();
    const email = e.target.elements.email.value;
    const passwd = e.target.elements.password.value;

    AuthManager.LoginUser(email, passwd).then((response) => {
      if (response != "error") {
        navigate("/user-profile");
        window?.location?.reload();
        setIsError(false);
      } else {
        AuthManager.LogoutUser();
        setIsError(true);
      }
    });
  };

  return (
    <>
      <div className="login-root">
        <div className="login-container">
          <h2>Zaloguj się</h2>
          <div className="w-75">
            {isError && (
              <div class="alert alert-danger m-4" role="alert">
                Nieprawidłowe hasło lub e-mail.
              </div>
            )}
            <form onSubmit={onSubmit}>
              <div class="form-group my-4">
                <label for="exampleInputEmail1">E-mail</label>
                <input
                  type="email"
                  name="email"
                  class="form-control"
                  id="exampleInputEmail1"
                  aria-describedby="emailHelp"
                  placeholder="user@mail.com"
                  required
                />
              </div>
              <div class="form-group my-4">
                <label for="exampleInputPassword1">Hasło</label>
                <input
                  type="password"
                  name="password"
                  class="form-control"
                  id="exampleInputPassword1"
                  placeholder="haslo123..."
                  required
                />
              </div>
              <ButtonStandart btnType="submit" label="Wyślij" type={0} />
            </form>
          </div>
          <div className="mt-4">
            Nie masz konta? <a href="/register">Zarejestruj się.</a>
          </div>
        </div>
      </div>
    </>
  );
};

export default Login;
