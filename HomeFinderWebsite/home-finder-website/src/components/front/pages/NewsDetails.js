import { useParams } from "react-router";

const NewsDetails = (props) => {
  const { newsId } = useParams();

  const fakeData = {
    id: 1,
    header: "Ulepszenie działania strony",
    textLong:
      "Strona teraz posiada obsługę aktualności! Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae metus viverra, efficitur purus id, finibus lectus. Suspendisse enim ex, viverra a nunc ut, placerat sodales lorem. Curabitur urna metus, faucibus ut risus quis, scelerisque placerat ex.Integer ut rutrum arcu. Fusce finibus lacinia rutrum. Integer tempor orci velit, a lobortis mi semper malesuada. Cras venenatis laoreet arcu, sed tincidunt erat. Vestibulum id efficitur arcu. Mauris et erat tellus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras vulputate felis odio, vel laoreet dolor porttitor et. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Praesent rhoncus libero dapibus, tincidunt mauris id, lobortis turpis. Quisque ultricies nibh vel nulla imperdiet, sit amet imperdiet urna consequat.",
  };

  return (
    <>
      <div className="news-details-root">
        <div className="news-details-container">
          <h2>{fakeData.header}</h2>
          <span>{fakeData.textLong}</span>
          <div className="news-details-team">
            <div>
              <img src={"/house-logo-1.png"} />
            </div>
            <br />
            <span>
              <img src={"/check-icon.png"} height={25} />
              Zespół Home Finder stara się dostraczyć użytkownikowi najlepszy
              produkt. <br />W razie pytań lub błędów serdercznie prosimy o{" "}
              <a href="/contact">kontakt</a>.
            </span>
          </div>
        </div>
      </div>
    </>
  );
};

export default NewsDetails;
