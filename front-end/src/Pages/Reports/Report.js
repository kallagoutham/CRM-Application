import "./Report.css";
import React from "react";
import { useNavigate } from "react-router-dom";
const Report = (props) => {
  const navigate = useNavigate();
  const token = localStorage.getItem("token");
  const ReportSubmitHandler = (event) => {
    event.preventDefault();
    console.log("Report Handler");
    if (props.type === "Retailer") {
      const data = {
        totalMet: event.target[0].value,
        newOnboarded: event.target[1].value,
        existingMet: event.target[2].value,
        ordersPlaced: event.target[3].value,
        date: new Date(),
        comment: {
          name: "Retailer111",
          text: event.target[4].value,
          date: new Date(),
        },
      };
      fetch("http://localhost:8080/api/v1/reports", {
        method: "post",
        body: JSON.stringify(data),
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
          Authorization: "Bearer " + token,
        },
      });
      navigate("/dashboard");
    } else {
      const data = {
        totalMet: event.target[0].value,
        newOnboarded: event.target[1].value,
        existingMet: event.target[2].value,
        ordersPlaced: event.target[3].value,
        date: new Date(),
        comment: {
          name: "Retailer111",
          text: event.target[4].value,
          date: new Date(),
        },
      };
      fetch("http://localhost:8080/api/v1/reports", {
        method: "post",
        body: JSON.stringify(data),
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
          Authorization: "Bearer " + token,
        },
      });
      navigate("/dashboard");
    }
  };
  return (
    <center>
      <br />
      <br />
      <form className="my-form" onSubmit={ReportSubmitHandler}>
        <table className="report-table">
          <tbody>
            <tr>
              <td>
                <label>Number of {props.type} Met:</label>
              </td>
              <td>
                <input type="number" max={100} min={0} required></input>
              </td>
            </tr>
            <tr>
              <td>
                <label>Number of {props.type} Onboarded:</label>
              </td>
              <td>
                <input type="number" max={100} min={0} required></input>
              </td>
            </tr>
            <tr>
              <td>
                <label>Number of Previous {props.type} Met:</label>
              </td>
              <td>
                <input type="number" max={100} min={0} required></input>
              </td>
            </tr>
            <tr>
              <td>
                <label>Number of orders placed:</label>
              </td>
              <td>
                <input type="number" max={100} min={0} required></input>
              </td>
            </tr>
            <tr>
              <td>
                <label>Comments:</label>
              </td>
              <td>
                <textarea
                  type="text"
                  cols="40"
                  rows="5"
                  style={{ width: "250px", height: "150px" }}
                  name="comments"
                />
              </td>
            </tr>
            <tr>
              <td colSpan={2}>
                <button type="submit" className="btn btn-primary">
                  Submit Report
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </form>
    </center>
  );
};
export default Report;
