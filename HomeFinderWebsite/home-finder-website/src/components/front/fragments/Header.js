import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import AuthManager from "../../../classes/AuthManager";
import ButtonStandart from "../../small-elements/ButtonStandart";

const Header = (props) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    setIsLoggedIn(AuthManager.CheckLoginUser);
  }, []);

  return (
    <div className="header-container">
      <div className="header-container-image">
        <Link to="/">
          <img
            src="/house-logo-1.png"
            alt="house-logo-1"
            style={{ height: "105px" }}
          />
        </Link>
      </div>
      <div className="header-main-menu-container">
        <div className="header-main-menu">
          <li>
            <a href="/#offers">Wyszukaj</a>
          </li>
          <li>
            <a href="/news">Aktualności</a>
          </li>
          <li>
            <a href="/about">Jak to działa?</a>
          </li>
          <li>
            <Link to="/user-profile">Moje konto</Link>
          </li>
          <hr />
        </div>
        <div className="header-main-menu-buttons">
          <ButtonStandart
            label="Kontakt"
            type={1}
            style={{ margin: "0 20px 0 20px" }}
            href="contact"
          />
          {isLoggedIn ? (
            <ButtonStandart
              label="Wyloguj się"
              type={0}
              style={{ margin: "0 20px 0 20px" }}
              onClick={() => {
                AuthManager.LogoutUser();
                window?.location?.reload();
              }}
            />
          ) : (
            <ButtonStandart
              label="Zaloguj się"
              type={0}
              style={{ margin: "0 20px 0 20px" }}
              href="/login"
            />
          )}
        </div>
      </div>
      <div className="header-container-line" />
    </div>
  );
};

export default Header;
//E1E1C0
//265954
