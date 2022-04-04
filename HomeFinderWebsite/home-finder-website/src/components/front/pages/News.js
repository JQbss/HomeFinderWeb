import HeroHome from "../fragments/HeroHome";

const NewsItem = (props) => {
  const data = props?.data;
  return data ? (
    <div className="news-item">
      <a href={`/news/${data?.id}`}>
        <h3>{data?.header}</h3>
        <span>{data?.textShort}</span>
      </a>
      <img
        src="/news-img.png"
        height={200}
        style={{ position: "absolute", right: 0, top: 0 }}
      />
    </div>
  ) : null;
};

const News = (props) => {
  const fakeData = {
    id: 1,
    header: "Ulepszenie działania strony",
    textShort:
      "Strona teraz posiada obsługę aktualności! Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque pellentesque urna sit amet metus suscipit, vel mollis lacus cursus. Pellentesque vel nisl diam. Suspendisse potenti.",
  };
  return (
    <>
      <div className="news-root">
        <div className="news-container">
          <NewsItem data={fakeData} />
          <NewsItem data={fakeData} />
          <NewsItem data={fakeData} />
        </div>
      </div>
    </>
  );
};

export default News;
