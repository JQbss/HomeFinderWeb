import HeroHome from "../fragments/HeroHome";
import OfferHome from "../fragments/OfferHome";
import TitleLocationHome from "../fragments/TitleLocationHome";

const Home = (props) => {
  return (
    <>
      <HeroHome />
      <div className="home-container-line" />
      <TitleLocationHome location={"Toruń, Kujawsko-Pomorskie"} />
      <div className="home-container-line" />
      <div className="home-offers-container">
        <OfferHome />
        <OfferHome />
        <OfferHome />
        <OfferHome />
      </div>
    </>
  );
};

export default Home;
