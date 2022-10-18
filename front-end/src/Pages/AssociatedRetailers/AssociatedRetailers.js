import DisplayList from "../GeneralPages/DisplayList";
import React, { useState, useEffect } from "react";

const AssociatedRetailers = (props) => {
  const [list, setlist] = useState([]);
  const token = localStorage.getItem("token");
  if (props.type === "rep") {
    const fetchRetailerlist = async () => {
      const response = await fetch("http://localhost:8080/api/v1/retailers", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
          Authorization: "Bearer " + token,
        },
      });
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

  return (
    <>
      <DisplayList table={list} type={props.type}></DisplayList>
    </>
  );
};
export default AssociatedRetailers;
