import { useEffect, useState } from "react";
import { useParams } from "react-router";
import FetchManager from "../../../classes/FetchManager";
import ButtonStandart from "../../small-elements/ButtonStandart";
import { entities } from "../config/entities";

const AdminAction = (props) => {
  const { entity, action, id } = useParams();
  const [fields, setFields] = useState([]);
  console.log(entity, action, id);

  useEffect(() => {
    switch (action) {
      case "edit":
        FetchManager.GetOne(entity, id).then((response) => {
          FieldGenerator(response, action);
        });
      case "create":
    }
  }, []);

  const FieldGenerator = (data, action) => {
    let _fieldsValues = {};
    const _fieldsLabels = entities[entity]?.[`${action}Labels`];
    const _elements = [];

    if (action == "edit") {
      _fieldsValues = [];
      entities[entity]?.[`${action}Fields`].map((field, i) => {
        for (const [key, value] of Object.entries(field)) {
          _fieldsValues[key] = data[key];
          _elements.push(
            <div className="admin-action-field">
              <span>{_fieldsLabels[i]}:</span>

              {value == "textarea" ? (
                <textarea
                  name={key}
                  defaultValue={_fieldsValues[key]}
                  rows={10}
                  className="admin-action-input"
                />
              ) : (
                <input
                  name={key}
                  defaultValue={_fieldsValues[key]}
                  type={value}
                  className="admin-action-input"
                />
              )}
            </div>
          );
        }
      });
    }

    setFields(_elements);
  };

  return (
    <div className="admin-action-form-container">
      <form>
        {fields}
        <ButtonStandart
          name="submit"
          btnType="btn"
          type={1}
          label="Zapisz"
          style={{ magin: 10 }}
        />
        <ButtonStandart
          name="delete"
          btnType="btn"
          type={0}
          label="Usuń"
          style={{ magin: 10 }}
        />
        <ButtonStandart
          btnType="link"
          href={`/admin/${entity}`}
          type={1}
          label="Powrót"
          style={{ magin: 10 }}
        />
      </form>
    </div>
  );
};

export default AdminAction;
