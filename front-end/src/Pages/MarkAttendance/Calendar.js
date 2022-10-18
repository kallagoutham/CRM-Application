import "./Calendar.css";
import MarkAttendance from "./MarkAttendance";
import React from "react";
const Calendar=(props)=>{
    return(
        <div>
            <br/><br/>
    <center><MarkAttendance type={props.type}/></center>
    </div>);
};
export default Calendar;