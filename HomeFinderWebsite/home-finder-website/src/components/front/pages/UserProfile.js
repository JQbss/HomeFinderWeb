import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import { Oval } from "react-loader-spinner";
import AuthManager from "../../../classes/AuthManager";
import FetchManager from "../../../classes/FetchManager";
import ButtonStandart from "../../small-elements/ButtonStandart";

const UserProfile = (props) => {
  const navigate = useNavigate();
  const [userData, setUserData] = useState(null);

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

  return userData ? (
    <div style={{ display: "flex", justifyContent: "center" }}>
      <div
        style={{
          display: "flex",
          width: 1000,
          backgroundColor: "#f8f6da",
          padding: "20px 20px 140px 20px",
        }}
      >
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
          <ButtonStandart label="Zmień zdjęcie" />
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
          <div
            style={{ display: "flex", flexDirection: "column", fontSize: 24 }}
          >
            <span style={{ color: "#265954", margin: 10 }}>Imię:</span>{" "}
            <span>{userData.firstName ? userData.firstName : "-"}</span>
            <span style={{ color: "#265954", margin: 10 }}>Nazwisko:</span>{" "}
            <span>{userData.lastName ? userData.lastName : "-"}</span>
            <span style={{ color: "#265954", margin: 10 }}>
              Numer telefonu:
            </span>{" "}
            <span>{userData.phoneNumber ? userData.phoneNumber : "-"}</span>
            <span style={{ color: "#265954", margin: 10 }}>Email:</span>{" "}
            <span>{userData.email}</span>
            <img
              src="/check-icon.png"
              alt="user_icon"
              style={{ width: "24px" }}
            />
          </div>
          <div>
            <ButtonStandart label="Edytuj" type={1} style={{ margin: 20 }} />
          </div>
        </div>
      </div>
    </div>
  ) : (
    <Oval color="#00BFFF" height={80} width={80} />
  );
};

export default UserProfile;
