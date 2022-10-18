import React from "react";
import RepresentativeListItem from "./RepresentativeListItem";
const RepresentativeList = (props) => {
  return (
    <div>
      <table className="table table-hover">
        <thead>
          <tr key="TableHead">
            <th>Representative Name</th>
            <th>Phone</th>
            <th>Representative ID</th>
            <th>Street</th>
            <th>City</th>
            <th>Pin Code</th>
            <th>state</th>
            <th>Country</th>
          </tr>
        </thead>
        <tbody>
          {props.list.map((data) => (
            <RepresentativeListItem key={data.id} data={data} />
          ))}
        </tbody>
      </table>
    </div>
  );
};
export default RepresentativeList;
