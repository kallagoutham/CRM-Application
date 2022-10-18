import { useNavigate } from "react-router-dom";
import React from "react";
const ListItem=(props)=>{

    const navigate=useNavigate();
    const ClickHandler=()=>{
        if(props.type==="rep"){
            // eslint-disable-next-line
        navigate("/AssociatedRetailers/"+`${props.item.id}`)
        }
    }
    return(
        <tr onClick={ClickHandler} key={props.item.id}>
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
export default ListItem;