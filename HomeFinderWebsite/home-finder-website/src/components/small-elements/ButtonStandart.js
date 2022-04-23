const ButtonStandart = ({
  btnType = "link",
  href = "#",
  label = "",
  type = 0,
  style = {},
  onClick = null,
}) => {
  return btnType == "link" ? (
    <a href={href} onClick={onClick}>
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
      onClick={onClick}
    >
      {label}
    </button>
  );
};

export default ButtonStandart;
