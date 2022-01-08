import { useState } from "react";

const FurnitureObject = ({ name }) => {
  return (
    <div className="offer-home-info-list-object">
      <img src="/check-icon.png" height={18} />
      {name}
    </div>
  );
};

const OfferHome = (props) => {
  const [blur, setBlur] = useState(false);

  const blurBg = () => {
    setBlur(true);
  };

  const unBlurBg = () => {
    setBlur(false);
  };

  return (
    <div className={`offer-home-container`}>
      {blur && (
        <div
          className="offer-home-blurred-more"
          onMouseEnter={blurBg}
          onMouseLeave={unBlurBg}
        >
          <a href="#">
            <div>Kliknij aby zobaczyć więcej</div>
          </a>
        </div>
      )}
      <div
        className={`offer-home-subcontainer ${
          blur ? "offer-home-blurred" : ""
        }`}
      >
        <div>
          <img src="/offer-template.jpg" className="offer-home-image" />
          <br />
          <span>Kliknij w obrazek aby powiększyć</span>
        </div>
        <div
          className="offer-home-info"
          onMouseEnter={blurBg}
          onMouseLeave={unBlurBg}
        >
          <span>Ilość pokojów: 2</span>
          <span>Rozpoznane umeblowanie:</span>
          <div className="offer-home-info-list">
            <FurnitureObject name={"Łóżko"} />
            <FurnitureObject name={"Dywan"} />
            <FurnitureObject name={"Fotel"} />
            <FurnitureObject name={"Lodówka"} />
            <FurnitureObject name={"Pralka"} />
            <FurnitureObject name={"Wanna"} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default OfferHome;
