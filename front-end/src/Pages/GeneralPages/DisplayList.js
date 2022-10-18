import ListItem from "./ListItem";
import React from "react";
import "./DisplayList.css";
const DisplayList=(props)=>{

    return(
        <div>
      <table className="table table-hover">
        <thead>
          <tr key="TableHead">
            <th>{props.type==="rep"?"Retailer":"Distributor"} Name</th>
            <th>Phone</th>
            <th>{props.type==="rep"?"Retailer":"Distributor"} ID</th>
            {props.type==="man" && <th>ERP</th>}
            <th>Street</th>
            <th>City</th>
            <th>Pin Code</th>
            <th>state</th>
            <th>Country</th>
          </tr>
        </thead>
        <tbody>
          {props.table.map((data) => (
            <ListItem key={data.id} item={data} type={props.type}></ListItem>
          ))}
        </tbody>
      </table>
    </div>
    );
};
export default DisplayList;