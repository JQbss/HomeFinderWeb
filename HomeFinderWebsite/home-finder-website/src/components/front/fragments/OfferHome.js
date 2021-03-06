import { useState } from "react";
// Core modules imports are same as usual
import SwiperCore, { Navigation, Pagination } from "swiper";
// Direct React component imports
import { Swiper, SwiperSlide } from "swiper/react/swiper-react.js";

// Styles must use direct files imports
import "swiper/swiper.scss"; // core Swiper
import "swiper/modules/navigation/navigation.scss"; // Navigation module
import "swiper/modules/pagination/pagination.scss"; // Pagination module
import { FurnitureObject } from "./FurnitureObject";
import { filtersList } from "../config/furnitureFilter";

SwiperCore.use([Navigation, Pagination]);

const OfferHome = (props) => {
  const [blur, setBlur] = useState(false);

  const blurBg = () => {
    setBlur(true);
  };

  const unBlurBg = () => {
    setBlur(false);
  };

  // props = {
  //   isBed: true,
  //   isTeapot: true,
  //   isOven: true,
  //   isWindowShade: true,
  //   isBed: true,
  //   isTable: true,
  //   ...props,
  // };

  return (
    <div className={`offer-home-container`}>
      {blur && (
        <div
          className="offer-home-blurred-more"
          onMouseEnter={blurBg}
          onMouseLeave={unBlurBg}
        >
          <a href={props.id ? `announcement/${props.id}` : "#"}>
            <div>Kliknij aby zobaczyć więcej</div>
          </a>
        </div>
      )}
      <div
        className={`offer-home-subcontainer ${
          blur ? "offer-home-blurred" : ""
        }`}
      >
        <div className="offer-home-image-container">
          {Array.isArray(props.img) ? (
            <Swiper pagination={true}>
              {props.img?.map((x, index) => (
                <SwiperSlide key={index}>
                  <img
                    src={x ? x : "/house-logo-1.png"}
                    className="offer-home-image"
                  />
                </SwiperSlide>
              ))}
            </Swiper>
          ) : (
            <img
              src={props.img ? props.img : "/house-logo-1.png"}
              className="offer-home-image"
            />
          )}
        </div>
        <div
          className="offer-home-info"
          onMouseEnter={blurBg}
          onMouseLeave={unBlurBg}
          onClick={() => {
            window.location.href = props.id ? `announcement/${props.id}` : "#";
          }}
        >
          {props.title && <span>{props.title}</span>}
          <span>Cena: {props.price ? props.price : "-"} zł</span>
          <span>Rozpoznane umeblowanie:</span>
          <div className="offer-home-info-list">
            {filtersList.map((filter) => {
              if (props?.furnishes) {
                for (const [key, value] of Object.entries(props?.furnishes)) {
                  if (filter[0] == key && value == "TRUE")
                    return <FurnitureObject name={filter[1]} />;
                }
              }
            })}
            {/* <FurnitureObject name={"Łóżko"} />
            <FurnitureObject name={"Dywan"} />
            <FurnitureObject name={"Fotel"} />
            <FurnitureObject name={"Lodówka"} />
            <FurnitureObject name={"Pralka"} />
            <FurnitureObject name={"Wanna"} /> */}
          </div>
        </div>
      </div>
    </div>
  );
};

export default OfferHome;
