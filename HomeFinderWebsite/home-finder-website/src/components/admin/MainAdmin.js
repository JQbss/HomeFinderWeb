import Footer from "./fragments/Footer";
import Header from "./fragments/Header";
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
      return null;
    default:
      return null;
  }
};

const MainAdmin = (props) => {
  return (
    <>
      <Header />
      <div style={{ backgroundColor: "#fffff5" }}>{getBody(props.page)}</div>
      <Footer />
    </>
  );
};

export default MainAdmin;
