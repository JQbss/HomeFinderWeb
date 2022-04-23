import { useEffect, useState } from "react";
import { Oval } from "react-loader-spinner";
import { useParams } from "react-router";
import FetchManager from "../../../classes/FetchManager";
import ButtonStandart from "../../small-elements/ButtonStandart";

const AdminList = (props) => {
  const { entity } = useParams();
  const [data, setData] = useState();

  useEffect(() => {
    FetchManager.GetMany(entity).then((response) => {
      response = response.flatMap((el) => Array(10).fill(el)); //TODO remove when will be more data
      setData(response);
      console.log(response);
    });
  }, []);

  const TableElement = (props) => {
    return (
      <tr>
        <th scope="row">{props.uid}</th>
        <td>{props.name}</td>
        <td>{props.localization}</td>
        <td>{props.price}</td>
        <td>
          <ButtonStandart href="#" label="Edytuj" type={1} />
        </td>
      </tr>
    );
  };

  return data ? (
    <div className="admin-list-container">
      <div className="w-75">
        <table class="table table-hover">
          <thead>
            <tr>
              <th scope="col">id</th>
              <th scope="col">Nazwa</th>
              <th scope="col">Miejscowość</th>
              <th scope="col">Cena, zł</th>
              <th scope="col">Akcję</th>
            </tr>
          </thead>
          <tbody>
            {data.map((el) => (
              <TableElement {...el} />
            ))}
          </tbody>
        </table>
      </div>
    </div>
  ) : (
    <Oval color="#00BFFF" height={80} width={80} />
  );
};

export default AdminList;
