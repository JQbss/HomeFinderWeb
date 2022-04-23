import { Link } from "react-router-dom";
import AuthManager from "../../../classes/AuthManager";
import ButtonStandart from "../../small-elements/ButtonStandart";

const Header = (props) => {
  return (
    <div className="admin-header-container">
      <div className="admin-header-container-image">
        <Link to="/admin">
          <img
            src="/house-logo-1.png"
            alt="house-logo-1"
            style={{ height: "105px" }}
          />
        </Link>
      </div>
      <div className="admin-header-main-menu-container">
        <div className="admin-header-main-menu">
          <h2>Home Finder - Panel administracyjny</h2>
          <hr />
        </div>
        <div className="admin-header-main-menu-buttons">
          <ButtonStandart
            label="Wyloguj się"
            type={0}
            style={{ margin: "0 20px 0 20px" }}
            onClick={() => {
              AuthManager.LogoutUser();
              window?.location?.reload();
            }}
          />
        </div>
      </div>
      <div className="admin-header-container-line" />
    </div>
  );
};

export default Header;
//E1E1C0
//265954
