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
        break;
      case "create":
        FieldGenerator(null, action);
        break;
      default:
        break;
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
    } else if (action == "create") {
      entities[entity]?.[`${action}Fields`].map((field, i) => {
        for (const [key, value] of Object.entries(field)) {
          _elements.push(
            <div className="admin-action-field">
              <span>{_fieldsLabels[i]}:</span>
              {value == "textarea" ? (
                <textarea name={key} rows={10} className="admin-action-input" />
              ) : (
                <input name={key} type={value} className="admin-action-input" />
              )}
            </div>
          );
        }
      });
    }

    setFields(_elements);
  };

  const onSubmitHandle = (e) => {
    e.preventDefault();
    const data = {};
    for (let i = 0; i < e.target.elements.length; i++) {
      let field = e.target.elements[i];
      if (!["submit", "delete"].includes(field.name))
        data[field.name] = field.value;
    }

    if (action == "edit") FetchManager.Patch(entity, id, data);
    else if (action == "create") FetchManager.Post(entity, data);

    window.location.href = `/admin/${entity}`;
  };

  return (
    <div className="admin-action-form-container">
      <form onSubmit={onSubmitHandle}>
        {fields}
        <div className="admin-action-form-btns">
          <ButtonStandart
            name="submit"
            btnType="btn"
            type={1}
            label="Zapisz"
            style={{ magin: 10 }}
          />
          {action == "edit" && (
            <ButtonStandart
              name="delete"
              isSubmit={false}
              onClick={() => {
                FetchManager.Delete(entity, id);
                window.location.href = `/admin/${entity}`;
              }}
              btnType="btn"
              type={0}
              label="Usuń"
              style={{ magin: 10 }}
            />
          )}
          <ButtonStandart
            btnType="link"
            href={`/admin/${entity}`}
            type={1}
            label="Powrót"
            style={{ magin: 10 }}
          />
        </div>
      </form>
    </div>
  );
};

export default AdminAction;
