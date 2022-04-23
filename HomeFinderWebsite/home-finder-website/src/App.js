import React from "react";
import "./App.css";
import MainAdmin from "./components/admin/MainAdmin";
import MainFront from "./components/front/MainFront";

function App(props) {
  return props?.admin ? <MainAdmin {...props} /> : <MainFront {...props} />;
}

export default App;
