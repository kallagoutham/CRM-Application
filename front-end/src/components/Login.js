import React from "react";
import "mdb-react-ui-kit/dist/css/mdb.min.css";
import "./Login.css";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { retailerActions } from "../store";
const Login = (props) => {
  // eslint-disable-next-line
  const user=useSelector(state=>state.retailer);
  const dispatch=useDispatch();
  const history = useNavigate();
  const submitHandler = async (event) => {
    event.preventDefault();
    // if(event.target[0].value==="rep"){
    //   dispatch(retailerActions.repReducer());
    // }
    // if(event.target[0].value==="man"){
    //   dispatch(retailerActions.manreducer());
    // }
    const username=event.target[0].value;
    const password=event.target[1].value;
    const response= await fetch("http://localhost:8080/auth/login", {
      method: 'post',
      body: JSON.stringify({username,password}),
      headers: {'Content-Type':'application/json'},
     })
     console.log(response);
     const data = await response.json()
     console.log(data);
     if(data.role==="REPRESENTATIVE"){
      dispatch(retailerActions.repReducer());
     }
     if(data.role==="MANAGER"){
      dispatch(retailerActions.manreducer());
     }
     localStorage.setItem('token',data.token);
     localStorage.setItem('isLoggedIn',true);
     localStorage.setItem('role',data.role);
    props.onClick();
    history("/dashboard");
  };
  return (
    <div>
      <img
        className="crm_logo"
        src={require("../Images/crm_logo.jpg")}
        alt={"Logo"}
        style={{ width: 150, height: 150, alignContent: "center" }}
      />
      <form onSubmit={submitHandler} className="crm-login-form">
        <div>
          <label className="username-label">Username:</label>
        </div>
        <br />
        <div>
          <input type="text" className="username-textbox" required></input>
        </div>
        <br />
        <div>
          <label className="password-label">Password:</label>
        </div>
        <br />
        <div>
          <input type="password" className="password-textbox" required minLength={6}></input>
        </div>
        <br />
        <div>
          <button className="btn btn-primary mylogin" type="submit">
            Login
          </button>
        </div>
      </form>
    </div>
  );
};
export default Login;
