import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { Oval } from "react-loader-spinner";
import { useNavigate } from "react-router";
import FetchManager from "../../../classes/FetchManager";
// Core modules imports are same as usual
import SwiperCore, { Navigation, Pagination } from "swiper";
// Direct React component imports
import { Swiper, SwiperSlide } from "swiper/react/swiper-react.js";

// Styles must use direct files imports
import "swiper/swiper.scss"; // core Swiper
import "swiper/modules/navigation/navigation.scss"; // Navigation module
import "swiper/modules/pagination/pagination.scss"; // Pagination module
import { FurnitureObject } from "../fragments/FurnitureObject";

const Announcement = (props) => {
  const navigate = useNavigate();
  const { announcementId } = useParams();
  const [annData, setAnnData] = useState();
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    FetchManager.GetOne("announcement", announcementId)
      .then((data) => setAnnData(data))
      .finally(() => setLoading(false));
  }, []);

  if (annData == "error" || (!loading && !annData)) navigate("/404");

  return annData ? (
    <>
      <div className="ann-details-root">
        <div className="ann-details-container">
          <h2>{annData?.title}</h2>

          <div className="ann-details-body">
            <div className="ann-swiper-wrapper">
              <Swiper pagination autoplay loop>
                {annData?.imageLinks?.map((x, index) => (
                  <SwiperSlide key={index}>
                    <img
                      src={x ? x : "/house-logo-1.png"}
                      style={{ maxWidth: 420 }}
                    />
                  </SwiperSlide>
                ))}
              </Swiper>
              <h3>Wyposażenie:</h3>
              <div className="offer-home-info-list">
                <FurnitureObject name={"Łóżko"} />
                <FurnitureObject name={"Dywan"} />
                <FurnitureObject name={"Fotel"} />
                <FurnitureObject name={"Lodówka"} />
                <FurnitureObject name={"Pralka"} />
                <FurnitureObject name={"Wanna"} />
              </div>
            </div>
            <div className="ann-details-descr">
              {annData?.link && (
                <a href={`${annData.link}`} target="_blank">
                  <h3>Link do ogłoszenia </h3>
                </a>
              )}
              {annData?.price && <h3>Cena: {annData?.price} zł</h3>}
              <h3>Opis:</h3>
              <br />
              {annData?.description}
            </div>
          </div>
        </div>
      </div>
    </>
  ) : (
    <div
      style={{
        height: 720,
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      <Oval color="#00BFFF" height={80} width={80} />
    </div>
  );
};

export default Announcement;
