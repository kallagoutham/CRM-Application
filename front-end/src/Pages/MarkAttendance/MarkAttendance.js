import "./MarkAttendance.css";
import React from "react";
import { Calendar } from "react-calendar";
import moment from "moment";
import { useState, useEffect } from "react";
import "./Calendar.css";
const MarkAttendance = (props) => {
  const [date,setdate] = useState(new Date());
  const token = localStorage.getItem("token");
  const [mark, setmark] = useState([]);
  const [isMarkedData, setIsMarkedData] = useState(false);
  const fetchdatelist = async () => {
    const response = await fetch(
      `http://localhost:8080/api/v1/attendance?month=${date.getMonth()+1}&year=${date.getFullYear()}`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
          Authorization: "Bearer " + token,
        },
      }
    );
    const isMarked = await fetch(
      "http://localhost:8080/api/v1/attendance/today",
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
    const tempMarkData = await isMarked.json();
    setIsMarkedData(tempMarkData);
  };

  useEffect(() => {
    fetchdatelist();
  }, [date]);
  const attendanceHandler = (event) => {
    event.preventDefault();
    const tod=new Date();
    const today =
      tod.getFullYear() +
      "-" +
      (tod.getMonth() > 8
        ? tod.getMonth() + 1
        : "0" + (tod.getMonth() + 1)) +
      "-" +
      (tod.getDate() > 9 ? tod.getDate() : "0" + tod.getDate());
    fetch("http://localhost:8080/api/v1/attendance", {
      method: "post",
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
        Authorization: "Bearer " + token,
      },
    });
    setmark([...mark, today]);
    setIsMarkedData(true);
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
        onActiveStartDateChange={({ activeStartDate}) =>
          setdate(activeStartDate)
        }
      />
      <br />
      <br />
      <button
        disabled={isMarkedData}
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