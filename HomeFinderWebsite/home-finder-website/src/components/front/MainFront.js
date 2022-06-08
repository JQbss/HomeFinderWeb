import Footer from "./fragments/Footer";
import Header from "./fragments/Header";
import Home from "./pages/Home";
import News from "./pages/News";
import Contact from "./pages/Contact";
import NewsDetails from "./pages/NewsDetails";
import UserProfile from "./pages/UserProfile";
import Announcement from "./pages/Announcement";
import Login from "./pages/Login";
import Register from "./pages/Register";
import NotFound from "./pages/NotFound";
import About from "./pages/About";

const getBody = (pageName) => {
  switch (pageName) {
    case "home":
      return <Home />;
    case "login":
      return <Login />;
    case "register":
      return <Register />;
    case "user-profile":
      return <UserProfile />;
    case "news":
      return <News />;
    case "news-details":
      return <NewsDetails />;
    case "contact":
      return <Contact />;
    case "announcement":
      return <Announcement />;
    case "about":
      return <About />;
    default:
      return <NotFound />;
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
