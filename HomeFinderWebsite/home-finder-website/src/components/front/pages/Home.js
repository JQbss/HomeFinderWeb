import { useEffect, useState } from "react";
import HeroHome from "../fragments/HeroHome";
import OfferHome from "../fragments/OfferHome";
import TitleLocationHome from "../fragments/TitleLocationHome";
import FetchManager from "../../../classes/FetchManager";
import { Oval } from "react-loader-spinner";
import Pagination from "react-js-pagination";

const Offers = (props) => {
  const filters = props.filters;

  const limit = 5;
  const [currentPage, setCurrentPage] = useState(0);

  const [data, setData] = useState([]);
  const [totalItems, setTotalItems] = useState(null);
  useEffect(() => {
    FetchManager.GetMany("announcement", currentPage, limit, filters).then(
      (resp) => {
        setData(resp[0]?.items);
        setTotalItems(resp[0].totalItems);
      }
    );
  }, [filters, currentPage]);

  return (
    <>
      {data.length != 0 ? (
        data?.map((offer) => (
          <OfferHome
            img={offer.imageLinks}
            price={offer.price}
            id={offer.uid}
          />
        ))
      ) : (
        <Oval color="#00BFFF" height={80} width={80} />
      )}
      <Pagination
        activePage={currentPage + 1}
        itemsCountPerPage={limit}
        totalItemsCount={totalItems}
        pageRangeDisplayed={4}
        onChange={(page) => {
          setCurrentPage(page - 1);
          setData([]);
        }}
        itemClass="page-item"
        linkClass="page-link"
        hideDisabled
      />
    </>
  );
};

const Home = (props) => {
  const [address, setAddress] = useState("Warszawa, województwo mazowieckie");
  const [city, setCity] = useState("Warszawa");

  useEffect(() => {
    navigator.geolocation.getCurrentPosition(function (position) {
      FetchManager.GetAddressDetails(
        position.coords.latitude,
        position.coords.longitude
      ).then((resp) => {
        setAddress(`${resp.address.city}, ${resp.address.state}`);
        setCity(resp.address.city);
      });
    });
  }, []);

  return (
    <>
      <HeroHome />
      <div className="home-container-line" />
      <TitleLocationHome location={address} />
      <div className="home-container-line" />
      <div className="home-offers-container">
        <Offers filters={""} />
      </div>
    </>
  );
};

export default Home;
