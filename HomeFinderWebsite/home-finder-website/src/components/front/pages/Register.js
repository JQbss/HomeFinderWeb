import { useNavigate } from "react-router";
import AuthManager from "../../../classes/AuthManager";
import ButtonStandart from "../../small-elements/ButtonStandart";

const Register = (props) => {
  const navigate = useNavigate();

  const onSubmit = (e) => {
    e.preventDefault();
    const email = e.target.elements.email.value;
    const passwd = e.target.elements.password.value;

    AuthManager.RegisterUser(email, passwd).then((result) =>
      navigate('/login')
    );
  };

  return (
    <>
      <div className="login-root">
        <div className="login-container">
          <h2>Zarejestruj się</h2>
          <div className="w-75">
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
                <small id="passwordHelp" class="form-text text-muted">
                  Minimalna długość - 6, musi zawierać litery oraz cyfry
                </small>
              </div>
              <div class="form-check my-4">
                <input
                  type="checkbox"
                  class="form-check-input"
                  id="Check1"
                  required
                />
                <label class="form-check-label" for="Check1">
                  Zatwierdzam nieistniejący <a href="#">regulamin</a>
                </label>
              </div>
              <div>
                <ButtonStandart btnType="submit" label="Wyślij" type={0} />
              </div>
            </form>
          </div>
          <div className="mt-4">
            Masz konto? <a href="/login">Zaloguj się.</a>
          </div>
        </div>
      </div>
    </>
  );
};

export default Register;
