import NavItem from "./NavItem";
import "./NavBar.css";
import React from "react";
import { useNavigate } from "react-router-dom";
const NavBar = (props) => {
  const navigate = useNavigate();
  const uname=props.type;
  return (
    <div className="navdiv ">
      {uname==="rep" && <ul className="crmNavBar">
        <li>
          {" "}
          <img
            src={require("../Images/crm_logo-modified.png")}
            alt={"Logo"}
            style={{ width: 150, height: 150, alignContent: "center",padding:"4%"}}
          />
        </li>
        <NavItem title="Associate Retailers"></NavItem>
        <NavItem title="Mark Attendance"></NavItem>
        <NavItem title="Reports"></NavItem>
        <NavItem title="New Retailer"></NavItem>
        <button
          className="btn btn-primary"
          onClick={() => {
            localStorage.removeItem("token");
            localStorage.removeItem('isLoggedIn');
            localStorage.removeItem('role');
            navigate("/");
            
            props.onClick();
          }}
        >
          Logout
        </button>
      </ul>}
      {uname==="man" && <ul className="crmNavBar">
        <li>
          {" "}
          <img
            src={require("../Images/crm_logo-modified.png")}
            alt={"Logo"}
            style={{ width: 150, height: 150, alignContent: "center",padding:"4%"}}
          />
        </li>
        <NavItem title="Associate Distributors"></NavItem>
        <NavItem title="Associated Representatives" />
        <NavItem title="Mark Attendance"></NavItem>
        <NavItem title="Reports"></NavItem>
        <NavItem title="New Distributor"></NavItem>
        <button
          className="btn btn-primary"
          onClick={() => {
            localStorage.removeItem("token");
            localStorage.removeItem('isLoggedIn');
            localStorage.removeItem('role');
            navigate("/");
            props.onClick();
          }}
        >
          Logout
        </button>
      </ul>}
    </div>
  );
};
export default NavBar;
