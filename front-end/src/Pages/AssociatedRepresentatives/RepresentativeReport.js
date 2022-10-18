import React from "react";
import { useState, useEffect } from "react";
const RepresentativeReport = (props) => {
  const [list, setlist] = useState([]);
  const token = localStorage.getItem("token");
  const fetchRetailerreports = async () => {
    const response = await fetch(
      `http://localhost:8080/api/v1/representatives/${props.id}/reports`,
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
    console.log(data.content);
    setlist(data.content);
  };
  useEffect(() => {
    fetchRetailerreports();
  }, []);
  return (
    <table className="table table-hover">
      <thead>
        <tr>
          <td>Date</td>
          <td>Total Retailers Met</td>
          <td>New Retailer Onbaorded</td>
          <td>Previous Retailers Met</td>
          <td>Orders Placed</td>
          <td>Comments</td>
        </tr>
      </thead>
      <tbody>
        {list.map(
          ({
            date,
            existingMet,
            newOnboarded,
            ordersPlaced,
            totalMet,
            comment,
          }) => (
            <tr key={date + comment.name}>
              <td>{date}</td>
              <td>{totalMet}</td>
              <td>{newOnboarded}</td>
              <td>{existingMet}</td>
              <td>{ordersPlaced}</td>
              <td>{comment.text}</td>
            </tr>
          )
        )}
      </tbody>
    </table>
  );
};
export default RepresentativeReport;
