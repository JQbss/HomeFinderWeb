import { useState } from "react";
import ButtonStandart from "../../small-elements/ButtonStandart";

const FiltersHome = ({ filtersList, filtersHandler }) => {
  const onSubmitHandler = (e) => {
    e.preventDefault();
    const data = {};
    for (let i = 0; i < e.target.elements.length; i++) {
      let field = e.target.elements[i];
      if (field.value == "true") data[`Furnishes__${field.name}`] = true;
    }
    filtersHandler(data);
  };

  return (
    <form onSubmit={onSubmitHandler}>
      <div className="filters-home-container">
        <div className="filters-home">
          {filtersList?.map((filter) => (
            <FilterFragment
              name={filter[0]}
              label={filter[1]}
              filtersHandler={filtersHandler}
            />
          ))}
        </div>
        <ButtonStandart btnType="btn" label="Zastosuj" name="filtersSubmit" />
      </div>
    </form>
  );
};

const FilterFragment = ({ name, label }) => {
  const [val, setVal] = useState(null);

  return (
    <div>
      <input
        type="checkbox"
        name={name}
        value={val}
        onChange={(e) => setVal(e.target.checked)}
      />
      <label>{label}</label>
    </div>
  );
};

export default FiltersHome;
