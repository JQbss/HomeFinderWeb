import { useEffect, useState } from "react";
import HeroHome from "../fragments/HeroHome";
import OfferHome from "../fragments/OfferHome";
import TitleLocationHome from "../fragments/TitleLocationHome";
import FetchManager from "../../../classes/FetchManager";

const Home = (props) => {
  const [city, SetCity] = useState("Warszawa, województwo mazowieckie");

  useEffect(() => {
    navigator.geolocation.getCurrentPosition(function (position) {
      FetchManager.GetAddressDetails(
        position.coords.latitude,
        position.coords.longitude
      ).then((resp) => SetCity(`${resp.address.city}, ${ resp.address.state}`));
    });
  }, []);

  return (
    <>
      <HeroHome />
      <div className="home-container-line" />
      <TitleLocationHome location={city} />
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
