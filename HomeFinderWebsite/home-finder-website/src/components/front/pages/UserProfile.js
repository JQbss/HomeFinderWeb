import { useEffect, useState } from "react";
import ButtonStandart from "../../small-elements/ButtonStandart";

const UserProfile = (props) => {
  const [userData, setUserData] = useState({
    firstName: "Admin",
    lastName: "Testowy",
    phoneNumber: "1234567890",
    email: "black.kote1@gmail.com",
  });

  useEffect(() => {
    fetch("http://localhost:8080/auth/user-details", {
      method: "GET",
      headers: {
        Authorization:
          "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6ImMxMGM5MGJhNGMzNjYzNTE2ZTA3MDdkMGU5YTg5NDgxMDYyODUxNTgiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vaG9tZWZpbmRlci03YzAxOCIsImF1ZCI6ImhvbWVmaW5kZXItN2MwMTgiLCJhdXRoX3RpbWUiOjE2NDM2NTYyMzAsInVzZXJfaWQiOiJMS0lxWG85cFN1Z3BPZ3lGbnFDT1lERXdISEczIiwic3ViIjoiTEtJcVhvOXBTdWdwT2d5Rm5xQ09ZREV3SEhHMyIsImlhdCI6MTY0MzY1NjIzMCwiZXhwIjoxNjQzNjU5ODMwLCJlbWFpbCI6ImJsYWNrLmtvdGUxQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJlbWFpbCI6WyJibGFjay5rb3RlMUBnbWFpbC5jb20iXX0sInNpZ25faW5fcHJvdmlkZXIiOiJwYXNzd29yZCJ9fQ.CvDJ84crjZLpMkQvStu-U9gpOzgiV-19poBp32TJD9zmMNpUOboXduIT3vrYKgD0M2OndOjGEgK_Zg7humZmyk8cOInxJTMy0T28duZS-NvUsvbdveU6YCiGq1BonpNlUzHD1x3twRiDeiNXh2-rtwvznq3xDbh1-JbXb_QCRg8F64dr4cEEIRHnL4mgv-ICKpOtNfbZwoxs9Pquvr-slMhjiTRuT6pHZN0-5BmF0wx8XxTVXMRLcqrGAif-bZeBnyULlA_T8G5JxlZ5n4TtN51HYdrkNKwTFcyF6tZquTaquKRrljrOZdM4xZ6ABPLbmL48jKRTvVEkvBBGEqu9Og",
      },
    })
      .then((resp) => resp.json())
      .then((data) => setUserData({ ...userData }));
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
            <span>{userData.firstName}</span>
            <span style={{ color: "#265954", margin: 10 }}>Nazwisko:</span>{" "}
            <span>{userData.lastName}</span>
            <span style={{ color: "#265954", margin: 10 }}>
              Numer telefonu:
            </span>{" "}
            <span>{userData.phoneNumber}</span>
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
