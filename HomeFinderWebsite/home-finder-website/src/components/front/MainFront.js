import Footer from "./fragments/Footer";
import Header from "./fragments/Header";
import Home from "./pages/Home";
import UserProfile from "./pages/UserProfile";

const getBody = (pageName) => {
  switch (pageName) {
    case "user-profile":
      return <UserProfile />;
    default:
      return <Home />;
  }
};

const MainFront = (props) => {
  return (
    <>
      <Header />
      <div style={{ backgroundColor: "#fffff5" }}>{getBody(props.page)}</div>
      <Footer />
    </>
  );
};

export default MainFront;
