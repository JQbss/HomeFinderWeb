// Core modules imports are same as usual
import SwiperCore, { Navigation, Pagination } from "swiper";
// Direct React component imports
import { Swiper, SwiperSlide } from "swiper/react/swiper-react.js";

// Styles must use direct files imports
import "swiper/swiper.scss"; // core Swiper
import "swiper/modules/navigation/navigation.scss"; // Navigation module
import "swiper/modules/pagination/pagination.scss"; // Pagination module
import ButtonStandart from "../../small-elements/ButtonStandart";

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

const SliderHeader = () => {
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
            style={{
              borderRadius: "40px",
              width: "250px",
              height: "48px",
              margin: "0 50px 0 0",
            }}
            placeholder="Wpisz miasto"
          />
          <ButtonStandart href="#" label="SZUKAJ" />
        </div>
      </div>
    </div>
  );
};

const HeroHome = (props) => {
  return (
    <div style={{ position: "relative" }}>
      <SliderHeader />
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
