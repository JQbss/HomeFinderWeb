import ButtonStandart from "../../small-elements/ButtonStandart";

const Header = (props) => {
  return (
    <div className="header-container">
      <div className="header-container-image">
        <img
          src="/house-logo-1.png"
          alt="house-logo-1"
          style={{ height: "105px" }}
        />
      </div>
      <div className="header-main-menu-container">
        <div className="header-main-menu">
          <li>
            <a href="#">Wyszukaj</a>
          </li>
          <li>
            <a href="#">Aktualności</a>
          </li>
          <li>
            <a href="#">Jak to działa?</a>
          </li>
          <li>
            <a href="#">Moje konto</a>
          </li>
          <hr />
        </div>
        <div className="header-main-menu-buttons">
          <ButtonStandart
            label="Kontakt"
            type={1}
            style={{ margin: "0 20px 0 20px" }}
          />
          <ButtonStandart
            label="Zaloguj się"
            type={0}
            style={{ margin: "0 20px 0 20px" }}
          />
        </div>
      </div>
      <div className="header-container-line" />
    </div>
  );
};

export default Header;
//E1E1C0
//265954
