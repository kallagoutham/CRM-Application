import Card from "../../UI/Card";
import React, { useState, useEffect } from "react";
const Comments = (props) => {
  const [list, setlist] = useState([]);
  const token = localStorage.getItem("token");
  if (props.type === "rep") {
    const fetchRetailerorders = async () => {
      const response = await fetch(
        `http://192.168.29.5:8080/api/v1/retailers/${props.id}/comments`,
        {
          method: "GET",
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            Authorization: "Bearer " + token,
          },
        }
      );
      const data = await response.json();
      console.log(data);
      setlist(data.content);
    };

    useEffect(() => {
      fetchRetailerorders();
    }, []);
  }
  const commentsubmitHandler = (event) => {
    event.preventDefault();
    const myobj = {
      Name: "Representative",
      Text: event.target[0].value,
      Date: new Date().toDateString(),
    };
    setlist([...list, myobj]);
  };
  return (
    <>
      <form onSubmit={commentsubmitHandler}>
        <textarea rows={4} cols={100} className="comment"></textarea>
        <br></br>
        <br></br>
        <button type="submit" className="btn btn-primary">
          {" "}
          Add Comment
        </button>
      </form>
      <table className="table">
        <tbody>
          {list.map((data) => (
            <tr key={data.date + data.Text + new Date()}>
              <Card data={data}></Card>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
};
export default Comments;
