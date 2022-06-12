import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route exact path="/" element={<App page={"home"} />} />
        <Route path="login" element={<App page={"login"} />} />
        <Route path="register" element={<App page={"register"} />} />
        <Route path="user-profile" element={<App page={"user-profile"} />} />
        <Route path="user-profile-edit" element={<App page={"user-profile-edit"} />} />       
        {/* <Route path="news" element={<App page={"news"} />} />
        <Route path="news/:newsId" element={<App page={"news-details"} />} /> */}
        <Route path="contact" element={<App page={"contact"} />} />
        <Route path="about" element={<App page={"about"} />} />
        <Route
          path="announcement/:announcementId"
          element={<App page={"announcement"} />}
        />       
        <Route path="admin" element={<App admin page={"admin-home"} />} />
        <Route path="admin/login" element={<App admin page={"admin-login"} />} />
        <Route
          path="admin/:entity"
          element={<App admin page={"admin-list"} />}
        />
        <Route
          path="admin/:entity/:action/:id"
          element={<App admin page={"admin-action"} />}
        />
                <Route
          path="admin/:entity/:action"
          element={<App admin page={"admin-action"} />}
        />
        <Route path="*" element={<App />} />
      </Routes>
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById("root")
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
