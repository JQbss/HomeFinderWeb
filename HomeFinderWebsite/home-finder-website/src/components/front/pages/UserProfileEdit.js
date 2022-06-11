import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import { Oval } from "react-loader-spinner";
import AuthManager from "../../../classes/AuthManager";
import FetchManager from "../../../classes/FetchManager";
import ButtonStandart from "../../small-elements/ButtonStandart";

const UserProfileEdit = (props) => {
  const navigate = useNavigate();
  const [userData, setUserData] = useState(null);
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    FetchManager.GetUserData().then((data) => {
      if (data == "error") {
        AuthManager.LogoutUser();
        navigate("/login");
        window?.location?.reload();
      }
      setUserData(data);
    });
  }, []);

  const onSubmit = (e) => {
    e.preventDefault();
    const data = {};
    for (let i = 0; i < e.target.elements.length; i++) {
      let field = e.target.elements[i];
      if (field.name != "submit") data[field.name] = field.value;
    }
    FetchManager.EditUserData(userData.uid, data).then((response) => {
      console.log(response);
    });
  };

  return userData ? (
    <div style={{ display: "flex", justifyContent: "center" }}>
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          maxWidth: 1000,
          backgroundColor: "#f8f6da",
          padding: "20px 20px 140px 20px",
        }}
      >
        {success && (
          <div class="alert alert-success m-4" role="alert">
            Nowe dane są zapisane
          </div>
        )}
        <div
          style={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <img
            src="/user_icon.png"
            alt="user_icon"
            style={{ height: "200px" }}
          />
        </div>
        <div
          style={{
            display: "flex",
            flexDirection: "column",
            margin: "0 0 0 15px",
          }}
        >
          <div>
            <span
              style={{
                color: "#265954",
                fontWeight: 600,
                fontSize: 42,
                margin: "0 0 20px 20px",
              }}
            >
              Moje konto
            </span>
          </div>
          <form onSubmit={onSubmit}>
            <div
              style={{ display: "flex", flexDirection: "column", fontSize: 24 }}
            >
              <span style={{ color: "#265954", margin: 10 }}>Imię:</span>{" "}
              <input name="firstName" defaultValue={userData.firstName} />
              <span style={{ color: "#265954", margin: 10 }}>
                Nazwisko:
              </span>{" "}
              <input name="lastName" defaultValue={userData.lastName} />
              <span style={{ color: "#265954", margin: 10 }}>
                Numer telefonu:
              </span>{" "}
              <input name="phoneNumber" defaultValue={userData.phoneNumber} />
              <span style={{ color: "#265954", margin: 10 }}>Email:</span>{" "}
              <input name="email" defaultValue={userData.email} />
              <img
                src="/check-icon.png"
                alt="user_icon"
                style={{ width: "24px" }}
              />
            </div>
            <div>
              <ButtonStandart
                label="Zapisz"
                btnType="btn"
                name="submit"
                type={1}
                style={{ margin: 20 }}
              />
            </div>
          </form>
        </div>
      </div>
    </div>
  ) : (
    <Oval color="#00BFFF" height={80} width={80} />
  );
};

export default UserProfileEdit;
