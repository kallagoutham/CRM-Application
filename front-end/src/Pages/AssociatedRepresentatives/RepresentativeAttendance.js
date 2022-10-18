import React from "react";
import { useState, useEffect } from "react";
import { Calendar } from "react-calendar";
import moment from "moment";
const RepresentativeAttendance = (props) => {
  const date = new Date();
  const token = localStorage.getItem("token");
  const [mark, setmark] = useState([]);
  const fetchdatelist = async () => {
    const response = await fetch(
      `http://localhost:8080/api/v1/representatives/${props.id}/attendance`,
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
    setmark(data.content);
  };

  useEffect(() => {
    fetchdatelist();
  }, []);
  return (
    <div>
      <Calendar
        value={date}
        tileClassName={({ date, view }) => {
          if (mark.find((x) => x === moment(date).format("YYYY-MM-DD"))) {
            return "highlight";
          }
        }}
      />
    </div>
  );
};
export default RepresentativeAttendance;
