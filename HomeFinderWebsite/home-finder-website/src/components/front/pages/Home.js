import { useEffect, useState } from "react";
import HeroHome from "../fragments/HeroHome";
import OfferHome from "../fragments/OfferHome";
import TitleLocationHome from "../fragments/TitleLocationHome";
import FetchManager from "../../../classes/FetchManager";
import { Oval } from "react-loader-spinner";
import Pagination from "react-js-pagination";
import FiltersHome from "../fragments/FiltersHome";
import { filtersList } from "../config/furnitureFilter";

const Offers = (props) => {
  const filters = props.filters;

  const limit = 5;
  const [currentPage, setCurrentPage] = useState(0);

  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(false);
  const [totalItems, setTotalItems] = useState(null);
  useEffect(() => {
    setLoading(true);
    FetchManager.GetMany("announcement", currentPage, limit, filters)
      .then((resp) => {
        setData(resp[0]?.items);
        setTotalItems(resp[0]?.totalItems);
      })
      .finally(() => setLoading(false));
  }, [filters, currentPage]);

  if (!loading && (!data || data?.length == 0))
    return <h3>Brak wyników dla podanych parametów wyszukiwania</h3>;

  return (
    <>
      {data?.length != 0 ? (
        data?.map((offer) => (
          <OfferHome
            img={offer.imageLinks}
            price={offer.price}
            id={offer.uid}
            title={offer.title}
            furnishes={offer.furnishes}
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
  const [address, setAddress] = useState("Warszawa");
  const [filters, setFilters] = useState({ localization: "Warszawa" });

  const filtersHandler = (_filters) => {
    setFilters((old) => {
      if (_filters.localization != old.localization && _filters.localization)
        return { ..._filters };
      else return { localization: old.localization, ..._filters };
    });
    _filters?.localization
      ? setAddress(_filters?.localization)
      : setAddress("Wszystkie regiony");
  };

  useEffect(() => {
    navigator.geolocation.getCurrentPosition(function (position) {
      FetchManager.GetAddressDetails(
        position.coords.latitude,
        position.coords.longitude
      ).then((resp) => {
        setAddress(resp.address.city);
        setFilters({ localization: resp.address.city });
      });
    });
  }, []);

  return (
    <>
      <HeroHome filtersHandler={filtersHandler} />
      <div className="home-container-line" />
      <TitleLocationHome location={address} />
      <div className="home-container-line" />
      <FiltersHome filtersList={filtersList} filtersHandler={filtersHandler} />
      <div className="home-container-line" />
      <div className="home-offers-container" id="offers">
        <Offers filters={filters} />
      </div>
    </>
  );
};

export default Home;
