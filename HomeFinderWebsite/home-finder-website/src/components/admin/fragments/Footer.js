const Footer = (props) => {
  return (
    <div>
      <div className="admin-home-container-line" />
      <div
        style={{ width: "100%", height: "60px", backgroundColor: "#f8f6da" }}
      >
        <div
          style={{
            display: "flex",
            height: "100%",
            alignItems: "center",
            margin: "0 40px 0 40px",
            justifyContent: "space-between",
            color: "#265954",
            fontWeight: 600
          }}
        >
          <span>Home Finder</span>
          <span>2021 - {new Date().getFullYear()}</span>
        </div>
      </div>
    </div>
  );
};

export default Footer;
