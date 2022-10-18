import "./App.css";
import { Route, Routes, Navigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { retailerActions } from "./store";
import Login from "./components/Login";
import React from "react";
import DashBoard from "./components/DashBoard";
import NavBar from "./components/NavBar";
import AssociatedRetailers from "./Pages/AssociatedRetailers/AssociatedRetailers";
import NewRetailer from "./Pages/NewRetailer/NewRetailer";
import AddForm from "./Pages/GeneralPages/AddForm";
import Report from "./Pages/Reports/Report";
import Dummy from "./Pages/AssociatedRetailers/Dummy";
import Calendar from "./Pages/MarkAttendance/Calendar";
import AssociatedRepresentatives from "./Pages/AssociatedRepresentatives/AssociatedRepresentatives";
import RepresentativeDetail from "./Pages/AssociatedRepresentatives/RepresentativeDetail";
function App() {
  const token = localStorage.getItem("token");
  const isLoggedIn = localStorage.getItem("isLoggedIn");
  const retailer = useSelector((state) => state.retailer);
  const dispatch = useDispatch();
  const LoginHandler = (event) => {
    dispatch(retailerActions.loginReducer());
  };
  const role = retailer.role;
  return (
    <div className="Appdiv text-center">
      {role === "REPRESENTATIVE" && isLoggedIn && (
        <NavBar onClick={LoginHandler} type="rep" />
      )}
      {role === "MANAGER" && isLoggedIn && (
        <NavBar onClick={LoginHandler} type="man" />
      )}
      <div className="route-div">
        {role === "REPRESENTATIVE" && (
          <Routes>
            <Route
              path="/"
              element={
                token && isLoggedIn ? (
                  <Navigate to="/dashboard" />
                ) : (
                  <Navigate to="/login" />
                )
              }
            />
            <Route
              path="/login"
              element={
                token && isLoggedIn ? (
                  <Navigate to="/dashboard" />
                ) : (
                  <Login onClick={LoginHandler} />
                )
              }
            />
            <Route path="/dashboard" element={<DashBoard />} />
            <Route
              path="/AssociateRetailers/"
              element={<AssociatedRetailers type="rep" />}
            />
            <Route
              path="/AssociatedRetailers/:UID"
              element={<Dummy type="rep" />}
            ></Route>
            <Route path="/MarkAttendance" element={<Calendar type="rep" />} />
            <Route path="/Reports" element={<Report type="Retailer" />} />
            <Route path="/NewRetailer" element={<NewRetailer type="rep" />} />
            <Route
              path="/NewRetailer/new"
              element={<AddForm type="Retailer" />}
            />
          </Routes>
        )}
        {role === "MANAGER" && (
          <Routes>
            <Route
              path="/"
              element={
                token && isLoggedIn ? (
                  <Navigate to="/dashboard" />
                ) : (
                  <Navigate to="/login" />
                )
              }
            />
            <Route
              path="/login"
              element={
                token && isLoggedIn ? (
                  <Navigate to="/dashboard" />
                ) : (
                  <Login onClick={LoginHandler} />
                )
              }
            />
            <Route path="/dashboard" element={<DashBoard />} />
            <Route
              path="/AssociateDistributors/"
              element={<AssociatedRetailers type="man" />}
            />
            <Route
              path="/AssociatedRepresentatives"
              element={<AssociatedRepresentatives />}
            ></Route>
            <Route
              path="/AssociatedRepresentatives/:UID"
              element={<RepresentativeDetail />}
            ></Route>
            <Route path="/MarkAttendance" element={<Calendar type="man" />} />
            <Route path="/Reports" element={<Report type="Distributor" />} />
            <Route
              path="/NewDistributor"
              element={<NewRetailer type="man" />}
            />
            <Route
              path="/NewDistributor/new"
              element={<AddForm type="Distributor" />}
            />
          </Routes>
        )}
      </div>
    </div>
  );
}

export default App;
