const TitleLocationHome = ({ location }) => {
  return (
    <div className="title-location-home-container">
      <div className="title-location-home">
        <img src="/map-marker.png" className="title-location-marker" />
        Oferty dla:
        <span>&nbsp;&nbsp;</span>
        <b>{location}</b>
      </div>
    </div>
  );
};

export default TitleLocationHome;
