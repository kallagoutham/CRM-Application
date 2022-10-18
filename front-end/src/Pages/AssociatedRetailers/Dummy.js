import "./Dummy.css";
import Orders from "./Orders";
import Comments from "./Comments";
import React from "react";
import {useState} from "react";
import { useParams } from "react-router-dom";
const Dummy = (props) => {
  const {UID}=useParams();
  const[orders,setorders]=useState(true);
  const OrdersHandler=()=>{
      setorders(true);
  }
  const CommentsHandler=()=>{
      setorders(false);
  }
  return (
    <div className="nav-for-retailers">
      <table className="associated-retailer-table">
        <tbody>
          <tr>
            <td>
              {" "}
              <button type="button" className="btn btn-primary" onClick={OrdersHandler}>
                Orders
              </button>
            </td>
            <td>
              <button type="button" className="btn btn-primary" onClick={CommentsHandler}>
                Comments
              </button>
            </td>
          </tr>
        </tbody>
      </table>
      {orders && <Orders type={props.type} id={UID}></Orders>}
      {!orders && <Comments type={props.type} id={UID}></Comments>}
    </div>
  );
};
export default Dummy;
