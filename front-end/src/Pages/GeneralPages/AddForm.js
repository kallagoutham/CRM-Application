import "./AddForm.css";
import React from "react";
import { useNavigate } from "react-router-dom";
const AddForm = (props) => {
  const token = localStorage.getItem("token");
  const navigate = useNavigate();

  const submitHandler = (event) => {
    event.preventDefault();
    console.log(event);
    if (props.type === "Retailer") {
      const data = {
        name: event.target[0].value,
        phoneNo: event.target[1].value,
        address: {
          street: event.target[2].value,
          city: event.target[3].value,
          pincode: event.target[4].value,
          state: event.target[5].value,
          country: event.target[6].value,
        },
      };
      fetch("http://localhost:8080/api/v1/retailers", {
        method: "post",
        body: JSON.stringify(data),
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
          Authorization: "Bearer " + token,
        },
      });
      navigate("/NewRetailer");
    } else {
      const data = {
        name: event.target[0].value,
        phoneNo: event.target[1].value,
        erp: event.target[2].value,
        address: {
          street: event.target[3].value,
          city: event.target[4].value,
          pincode: event.target[5].value,
          state: event.target[6].value,
          country: event.target[7].value,
        },
      };
      fetch("http://localhost:8080/api/v1/distributors", {
        method: "post",
        body: JSON.stringify(data),
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
          Authorization: "Bearer " + token,
        },
      });
      navigate("/NewDistributor");
    }
  };
  return (
    <form onSubmit={submitHandler}>
      <center>
        <table className="new-retailer-table ">
          <thead className="new-form-header text-center">
            <tr colSpan={2}>
              <td>New {props.type}</td>
            </tr>
          </thead>
          <tbody>
            <tr className="UserName">
              <td>
                <label>Name:</label>
              </td>
              <td>
                <input type="text" required></input>
              </td>
            </tr>
            <tr className="Phone">
              <td>
                <label>Phone Number:</label>
              </td>
              <td>
                <input
                  type="tel"
                  pattern="[6-9]{1}[0-9]{9}"
                  required
                ></input>
              </td>
            </tr>
            {props.type === "Distributor" && (
              <tr className="ERP">
                <td>
                  <label>ERP:</label>
                </td>
                <td>
                  <input type="text" required></input>
                </td>
              </tr>
            )}
            <tr className="Colony">
              <td>
                <label>Street:</label>
              </td>
              <td>
                <input type="text" required></input>
              </td>
            </tr>
            <tr className="city">
              <td>
                <label>City:</label>
              </td>
              <td>
                <input type="text" required></input>
              </td>
            </tr>
            <tr className="pincode">
              <td>
                <label>pincode:</label>
              </td>
              <td>
                <input
                  type="tel"
                  pattern="[0-9]{6}"
                  required
                ></input>
              </td>
            </tr>
            <tr className="state">
              <td>
                <label>State:</label>
              </td>
              <td>
                <input type="text" required></input>
              </td>
            </tr>
            <tr className="country">
              <td>
                <label>Country:</label>
              </td>
              <td>
                <input type="text" required></input>
              </td>
            </tr>
            <tr className="submit-button text-center">
              <td colSpan={2}>
                <button
                  type="submit"
                  value="submit"
                  className="btn btn-primary"
                  width="40px"
                >
                  Submit
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </center>
    </form>
  );
};
export default AddForm;
