import { configureStore, createSlice } from "@reduxjs/toolkit";
// eslint-disable-next-line
import React from "react";
const role = localStorage.getItem("role");
const retailerSlice = createSlice({
  name: "retailer",
  initialState: {
    role: role===null ? "REPRESENTATIVE" : role,
  },
  reducers: {
    loginReducer(state) {
      state.isLoggedIn = !state.isLoggedIn;
    },
    repReducer(state) {
      state.role = "REPRESENTATIVE";
    },
    manreducer(state) {
      state.role = "MANAGER";
    },
  },
});
const store = configureStore({
  reducer: { retailer: retailerSlice.reducer },
});

export const retailerActions = retailerSlice.actions;
export default store;
