const ButtonStandart = ({
  btnType = "link",
  href = "#",
  label = "",
  type = 0,
  style = {},
}) => {
  return btnType == "link" ? (
    <a href={href}>
      <div
        className={type == 0 ? "button-normal" : "button-trans"}
        style={style}
      >
        {label}
      </div>
    </a>
  ) : (
    <button
      type="submit"
      className={type == 0 ? "button-normal" : "button-trans"}
    >
      {label}
    </button>
  );
};

export default ButtonStandart;
