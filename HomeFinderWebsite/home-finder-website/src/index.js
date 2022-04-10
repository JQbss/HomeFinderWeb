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
        <Route path="/" element={<App />} />
        <Route path="login" element={<App page={"login"} />} />
        <Route path="register" element={<App page={"register"} />} />
        <Route path="user-profile" element={<App page={"user-profile"} />} />
        <Route path="news" element={<App page={"news"} />} />
        <Route path="news/:newsId" element={<App page={"news-details"} />} />
        <Route path="contact" element={<App page={"contact"} />} />
        <Route
          path="announcement/:announcementId"
          element={<App page={"announcement"} />}
        />
      </Routes>
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById("root")
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
