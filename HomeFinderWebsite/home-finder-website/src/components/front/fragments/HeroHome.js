// Core modules imports are same as usual
import SwiperCore, { Navigation, Pagination } from "swiper";
// Direct React component imports
import { Swiper, SwiperSlide } from "swiper/react/swiper-react.js";

// Styles must use direct files imports
import "swiper/swiper.scss"; // core Swiper
import "swiper/modules/navigation/navigation.scss"; // Navigation module
import "swiper/modules/pagination/pagination.scss"; // Pagination module
import ButtonStandart from "../../small-elements/ButtonStandart";
import { useState } from "react";

const Images = ["/hero-1.jpg", "/hero-2.jpg"];

SwiperCore.use([Navigation, Pagination]);

const Slide = ({ imgUrl }) => {
  return (
    <div
      style={{
        backgroundImage: `url(${imgUrl})`,
        backgroundSize: "cover",
        width: "100%",
        height: "800px",
        filter: "blur(3px)",
      }}
    />
  );
};

const SliderHeader = (props) => {
  const [city, setCity] = useState("");

  return (
    <div className="slider-header-container">
      <div className="slider-header-subcontainer">
        <div className="slider-header-title">Home Finder</div>
        <div className="slider-header-subtitle">
          inteligetna wyszukiwarka
          <br />
          mieszkania dla każdego
        </div>
        <div className="slider-header-search">
          <input
            placeholder="Wpisz miasto"
            value={city}
            onChange={(e) => {
              setCity(e.target.value);
            }}
          />
          <ButtonStandart
            btnType="link"
            href="/#offers"
            label="SZUKAJ"
            hashLink={true}
            onClick={(e) => {
              props.filtersHandler({ localization: city });
            }}
          />
        </div>
      </div>
    </div>
  );
};

const HeroHome = (props) => {
  return (
    <div style={{ position: "relative" }}>
      <SliderHeader filtersHandler={props?.filtersHandler} />
      <Swiper pagination={true}>
        {Images.map((x, index) => (
          <SwiperSlide key={index}>
            <Slide imgUrl={x} />
          </SwiperSlide>
        ))}
      </Swiper>
    </div>
  );
};
//<Slide imgUrl={x} key={index} />
export default HeroHome;
