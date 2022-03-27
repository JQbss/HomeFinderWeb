import Footer from "./fragments/Footer";
import Header from "./fragments/Header";
import Home from "./pages/Home";
import News from "./pages/News";
import NewsDetails from "./pages/NewsDetails";
import UserProfile from "./pages/UserProfile";

const getBody = (pageName) => {
  switch (pageName) {
    case "user-profile":
      return <UserProfile />;
    case "news":
      return <News />;
      case "news-details":
        return <NewsDetails />;
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
