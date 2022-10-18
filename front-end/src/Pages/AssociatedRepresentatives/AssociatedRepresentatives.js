import React from "react";
import { useState, useEffect } from "react";
import RepresentativeList from "./RepresentativeList";
const AssociatedRepresentatives = () => {
  const [list, setlist] = useState([]);
  const token = localStorage.getItem("token");
  const fetchRepresentativelist = async () => {
    const response = await fetch(
      "http://localhost:8080/api/v1/representatives",
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
    fetchRepresentativelist();
  }, []);
  return <RepresentativeList list={list} />;
};
export default AssociatedRepresentatives;
