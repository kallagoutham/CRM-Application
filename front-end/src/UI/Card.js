import "./Card.css";
import React from "react";
const Card = (props) => {
  return (
    <td>
      <div style={{ textAlign: "left" }}>
      <img
        className="user_logo"
        src={require("../Images/user_icon_001.jpg")}
        alt={"Logo"}
        style={{ width: "5%", height: "5%", alignContent: "center" }}
      />
        <strong style={{ color: "black" }}>Date:{props.data.date}</strong>
        <br></br>
        <strong style={{ color: "black" }}>Posted by:{props.data.name}</strong>
        <br></br>
        Comments:{props.data.text}
      </div>
    </td>
  );
};
export default Card;
