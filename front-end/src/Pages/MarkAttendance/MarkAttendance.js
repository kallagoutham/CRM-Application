import "./MarkAttendance.css";
import React from "react";
import { Calendar } from "react-calendar";
import moment from "moment";
import { useState, useEffect } from "react";
import "./Calendar.css";
const MarkAttendance = (props) => {
  const date = new Date();
  const token = localStorage.getItem("token");
  const [mark, setmark] = useState([]);
  const fetchdatelist = async () => {
    const response = await fetch(
      "http://localhost:8080/api/v1/attendance?month=10&year=2022",
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
  const attendanceHandler = (event) => {
    event.preventDefault();
    const today =
      date.getFullYear() +
      "-" +
      (date.getMonth() > 8
        ? date.getMonth() + 1
        : "0" + (date.getMonth() + 1)) +
      "-" +
      (date.getDate() > 9 ? date.getDate() : "0" + date.getDate());
    fetch("http://localhost:8080/api/v1/attendance", {
      method: "post",
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
        Authorization: "Bearer " + token,
      },
    });
    setmark([...mark, today]);
  };
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
      <br />
      <br />
      <button
        className={"btn btn-primary"}
        type="submit"
        onClick={attendanceHandler}
      >
        Mark Attendance
      </button>
    </div>
  );
};
export default MarkAttendance;
