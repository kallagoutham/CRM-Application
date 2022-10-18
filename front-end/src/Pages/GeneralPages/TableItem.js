import React from "react";
const TableItem=(props)=>{
    return(
        <tr>
            <td>{props.item.name}</td>
            <td>{props.item.phoneNo}</td>
            <td>{props.item.id}</td>
            {props.type==="man" && <td>{props.item.erp}</td>}
            <td>{ props.item.address==null ?"-":props.item.address.street}</td>
            <td>{props.item.address==null ?"-":props.item.address.city}</td>
            <td>{props.item.address==null ?"-":props.item.address.pinCode}</td>
            <td>{props.item.address==null ?"-":props.item.address.state}</td>
            <td>{props.item.address==null ?"-":props.item.address.country}</td>
        </tr>
    );
};
export default TableItem;