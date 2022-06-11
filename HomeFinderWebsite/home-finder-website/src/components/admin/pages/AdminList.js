import { useEffect, useState } from "react";
import { Oval } from "react-loader-spinner";
import { useParams } from "react-router";
import FetchManager from "../../../classes/FetchManager";
import ButtonStandart from "../../small-elements/ButtonStandart";
import { entities } from "../config/entities";
import Pagination from "react-js-pagination";

const AdminList = (props) => {
  const { entity } = useParams();
  const [fields, setFields] = useState([]);
  const [headers, setHeaders] = useState([]);

  const [currentPage, setCurrentPage] = useState(0);
  const [totalItems, setTotalItems] = useState(null);

  const limit = 10;

  useEffect(() => {
    FetchManager.GetMany(entity, currentPage, limit).then((response) => {
      FieldGenerator(response[0].items);
      setTotalItems(response[0]?.totalItems);
    });
  }, [currentPage]);

  const FieldGenerator = (data) => {
    let _fieldsValues = [];
    const _elements = [];

    data?.map((el) => {
      _fieldsValues = [];
      entities[entity]?.list.map((fieldName) => {
        if (fieldName instanceof Object) {
          Object.values(fieldName).map((sub) => {
            _fieldsValues.push(el[Object.keys(fieldName)?.[0]]?.[sub]);
          });
        } else _fieldsValues.push(el[fieldName]);
      });
      _elements.push(TableElement(_fieldsValues));
    });
    setFields(_elements);

    setHeaders(TableHeader(entities[entity]?.listLabels));
  };

  const TableElement = (props) => {
    return (
      <tr>
        {props?.map((field) => (
          <td>{field}</td>
        ))}
        {entities[entity]?.edit && (
          <td>
            <ButtonStandart href={`/admin/${entity}/edit/${props[0]}`} label="Edytuj" type={1} />
          </td>
        )}
      </tr>
    );
  };

  const TableHeader = (props) => {
    return (
      <tr>
        {props?.map((field) => (
          <th>{field}</th>
        ))}
      </tr>
    );
  };

  return fields ? (
    <div className="admin-list-container">
      <div className="w-75">
        <table class="table table-hover">
          <thead>{headers}</thead>
          <tbody>{fields}</tbody>
        </table>
      </div>
      <Pagination
        activePage={currentPage + 1}
        itemsCountPerPage={limit}
        totalItemsCount={totalItems}
        pageRangeDisplayed={4}
        onChange={(page) => {
          setCurrentPage(page - 1);
          setFields([]);
        }}
        itemClass="page-item"
        linkClass="page-link"
        hideDisabled
      />
    </div>
  ) : (
    <Oval color="#00BFFF" height={80} width={80} />
  );
};

export default AdminList;
