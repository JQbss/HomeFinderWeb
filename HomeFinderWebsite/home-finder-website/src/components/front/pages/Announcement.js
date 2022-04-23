import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { Oval } from "react-loader-spinner";
import { useNavigate } from "react-router";
import FetchManager from "../../../classes/FetchManager";

const Announcement = (props) => {
  const navigate = useNavigate();
  const { announcementId } = useParams();
  const [annData, setAnnData] = useState();

  useEffect(() => {
    FetchManager.GetOne("announcement", announcementId).then((data) =>
      setAnnData(data)
    );
  }, []);

  if (annData == "error") navigate("/404");

  return annData ? (
    <>
      <div className="ann-details-root">
        <div className="ann-details-container">
          <h2>{annData?.title}</h2>

          <div className="ann-details-body">
            <div>
              <img
                src={"/house-logo-1.png"}
                width={300}
                style={{ marginRight: 30 }}
              />
            </div>
            <div className="ann-details-descr">
              {annData?.link && (
                <a href={`${annData.link}`} target="_blank">
                  <h3>Link do ogłoszenia </h3>
                </a>
              )}
              <h3>Opis:</h3>
              <br />
              {annData?.description}
            </div>
          </div>
        </div>
      </div>
    </>
  ) : (
    <Oval color="#00BFFF" height={80} width={80} />
  );
};

export default Announcement;
