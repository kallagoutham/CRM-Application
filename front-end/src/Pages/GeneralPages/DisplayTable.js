import React, { useState, useEffect } from "react";
import TableItem from "./TableItem";
import AddButton from "./AddButton";
import "./DisplayTable.css";
import { useNavigate } from "react-router-dom";
const DisplayTable = (props) => {
  const [list, setlist] = useState([]);
  const token = localStorage.getItem("token");
  if (props.type === "rep") {
    const fetchRetailerlist = async () => {
      const response = await fetch(
        "http://localhost:8080/api/v1/retailers",
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            Authorization: "Bearer " + token,
          },
        }
      );
      const data = await response.json();
      setlist(data.content);
    };

    useEffect(() => {
      fetchRetailerlist();
    }, []);
  } else {
    const fetchDistributorlist = async () => {
      const response = await fetch(
        "http://localhost:8080/api/v1/distributors",
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            Authorization: "Bearer " + token,
          },
        }
      );
      const data = await response.json();
      setlist(data.content);
    };

    useEffect(() => {
      fetchDistributorlist();
    }, []);
  }
  const navigate = useNavigate();
  const clickHandler = () => {
    if (props.type === "rep") {
      navigate("/NewRetailer/new");
    } else {
      navigate("/NewDistributor/new");
    }
  };
  return (
    <div>
      <AddButton title={props.type} onClick={clickHandler} />
      <table className="crmTable table table-striped">
        <thead>
          <tr>
            <th>{props.type} Name</th>
            <th>Phone</th>
            <th>{props.type} ID</th>
            {props.type === "man" && <th>ERP</th>}
            <th>Street</th>
            <th>City</th>
            <th>Pin Code</th>
            <th>state</th>
            <th>Country</th>
          </tr>
        </thead>
        <tbody>
          {list.map((data) => (
            <TableItem key={data.id} item={data} type={props.type} />
          ))}
        </tbody>
      </table>
    </div>
  );
};
export default DisplayTable;
