import { HashLink } from "react-router-hash-link";

const ButtonStandart = ({
  btnType = "link",
  href = "",
  label = "",
  type = 0,
  style = {},
  onClick = null,
  hashLink = false,
  name = "",
}) => {
  switch (btnType) {
    case "link":
      return !hashLink ? (
        <a href={href} onClick={onClick}>
          <div
            className={type == 0 ? "button-normal" : "button-trans"}
            style={style}
          >
            {label}
          </div>
        </a>
      ) : (
        <HashLink to={href} onClick={onClick}>
          <div
            className={type == 0 ? "button-normal" : "button-trans"}
            style={style}
          >
            {label}
          </div>
        </HashLink>
      );
    case "btn":
      return (
        <button
          type="submit"
          className={type == 0 ? "button-normal" : "button-trans"}
          onClick={onClick}
          name={name}
          style={style}
        >
          {label}
        </button>
      );
    default:
      return null;
  }
};

export default ButtonStandart;
