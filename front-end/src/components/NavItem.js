import { NavLink } from "react-router-dom";
import "./NavItem.css";
import React from "react";
const NavItem = (props) => {
  return (
    <li className="crmNavItem">
      <NavLink
        to={`${props.title.replace(/ /g, "")}`}
        style={({ isActive }) =>
          isActive
            ? { backgroundColor: "fff", color: "black", display: "block" }
            : {}
        }
      >
        {props.title}
      </NavLink>
    </li>
  );
};
export default NavItem;
