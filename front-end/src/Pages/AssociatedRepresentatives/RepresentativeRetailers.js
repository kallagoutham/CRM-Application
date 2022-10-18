import React from "react";
import { useState, useEffect } from "react";
const RepresentativeRetailers = (props) => {
  const [list, setlist] = useState([]);
  const token = localStorage.getItem("token");
  const fetchRetailers = async () => {
    const response = await fetch(
      `http://localhost:8080/api/v1/representatives/${props.id}/retailers`,
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
    console.log(data);
    setlist(data.content);
  };

  useEffect(() => {
    fetchRetailers();
  }, []);
  return (
    <table className="table table-hover">
      <thead>
        <tr>
          <td>Id</td>
          <td>Name</td>
          <td>Phone No.</td>
          <td>Business Name</td>
          <td>Address</td>
        </tr>
      </thead>
      <tbody>
        {list.map((data) => (
          <tr key={data.id}>
            <td>{data.id}</td>
            <td>{data.name}</td>
            <td>{data.phoneNo}</td>
            <td>{data.businessName}</td>
            <td>{data.address === null ? "-" : data.address.city}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};
export default RepresentativeRetailers;
