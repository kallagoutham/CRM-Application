import { useParams } from "react-router-dom";
import { useState } from "react";
import React from "react";
import RepresentativeAttendance from "./RepresentativeAttendance";
import RepresentativeReport from "./RepresentativeReport";
import RepresentativeRetailers from "./RepresentativeRetailers";
const RepresentativeDetail = () => {
  const { UID } = useParams();
  const [state, setstate] = useState("Reports");
  const ReportsHandler = () => {
    setstate("Reports");
  };
  const AttendanceHandler = () => {
    setstate("Attendance");
  };
  const RetailerHandler = () => {
    setstate("Retailers");
  };
  return (
    <center>
      <div>
        <table>
          <tbody>
            <tr>
              <td>
                {" "}
                <button
                  type="button"
                  className="btn btn-primary"
                  onClick={ReportsHandler}
                >
                  Reports
                </button>
              </td>
              <td>
                <button
                  type="button"
                  className="btn btn-primary"
                  onClick={AttendanceHandler}
                >
                  Attendance{" "}
                </button>
              </td>
              <td>
                <button
                  type="button"
                  className="btn btn-primary"
                  onClick={RetailerHandler}
                >
                  Retailers
                </button>
              </td>
            </tr>
          </tbody>
        </table>
        {state === "Reports" && <RepresentativeReport id={UID} />}
        {state === "Attendance" && <RepresentativeAttendance id={UID} />}
        {state === "Retailers" && <RepresentativeRetailers id={UID} />}
      </div>
    </center>
  );
};
export default RepresentativeDetail;
