const ButtonStandart = ({ href = "#", label = "", type = 0, style = {} }) => {
  return (
    <a href={href}>
      <div
        className={type == 0 ? "button-normal" : "button-trans"}
        style={style}
      >
        {label}
      </div>
    </a>
  );
};

export default ButtonStandart;
