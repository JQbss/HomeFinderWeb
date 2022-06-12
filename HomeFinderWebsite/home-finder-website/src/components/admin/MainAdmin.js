import { useEffect } from "react";
import { useNavigate } from "react-router";
import AuthManager from "../../classes/AuthManager";
import Footer from "./fragments/Footer";
import Header from "./fragments/Header";
import AdminAction from "./pages/AdminAction";
import AdminHome from "./pages/AdminHome";
import AdminList from "./pages/AdminList";

const getBody = (pageName) => {
  switch (pageName) {
    case "admin-home":
      return <AdminHome />;
    case "admin-login":
      return null;
    case "admin-list":
      return <AdminList />;
    case "admin-action":
      return <AdminAction />;
    default:
      return null;
  }
};

const MainAdmin = (props) => {
  const navigate = useNavigate();

  useEffect(() => {
    if (!AuthManager.CheckLoginUser()) navigate("/");
  }, []);

  return (
    <>
      <Header />
      <div style={{ backgroundColor: "#fffff5" }}>{getBody(props.page)}</div>
      <Footer />
    </>
  );
};

export default MainAdmin;
