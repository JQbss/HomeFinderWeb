import { useEffect, useState } from "react";
import FetchManager from "../../../classes/FetchManager";
import ButtonStandart from "../../small-elements/ButtonStandart";

const UserProfile = (props) => {
  const [userData, setUserData] = useState({
    firstName: "Admin",
    lastName: "Testowy",
    phoneNumber: "1234567890",
    email: "black.kote1@gmail.com",
  });

  useEffect(() => {
    FetchManager.GetUserData().then((data) => setUserData(data));
  }, []);

  return (
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
  );
};

export default UserProfile;
