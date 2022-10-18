import React from "react";
import { useNavigate } from "react-router-dom";
const RepresentativeListItem = (props) => {
  const navigate = useNavigate();
  const ClickHandler = () => {
    // eslint-disable-next-line
    navigate("/AssociatedRepresentatives/" + `${props.data.id}`);
  };
  return (
    <tr onClick={ClickHandler} key={props.data.id}>
      <td>{props.data.name}</td>
      <td>{props.data.phoneNo}</td>
      <td>{props.data.id}</td>
      <td>{props.data.address == null ? "-" : props.data.address.street}</td>
      <td>{props.data.address == null ? "-" : props.data.address.city}</td>
      <td>{props.data.address == null ? "-" : props.data.address.pinCode}</td>
      <td>{props.data.address == null ? "-" : props.data.address.state}</td>
      <td>{props.data.address == null ? "-" : props.data.address.country}</td>
    </tr>
  );
};
export default RepresentativeListItem;
