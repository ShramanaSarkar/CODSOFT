import React, { useState, useEffect } from "react";
import { getStudents } from "../services/api";
import StudentTable from "./StudentTable";
import StudentForm from "./StudentForm";
import { Button, Container, Typography } from "@mui/material";

const StudentList = () => {
  const [students, setStudents] = useState([]);
  const [editing, setEditing] = useState(false);
  const [selectedStudent, setSelectedStudent] = useState(null);

  useEffect(() => {
    fetchStudents();
  }, []);

  const fetchStudents = async () => {
    const response = await getStudents();
    setStudents(response.data);
  };

  return (
    <Container>
      <Typography variant="h4" gutterBottom>Student Management System</Typography>
      <Button variant="contained" color="primary" onClick={() => setEditing(!editing)}>
        {editing ? "Cancel" : "Add Student"}
      </Button>
      {editing && <StudentForm student={selectedStudent} fetchStudents={fetchStudents} setEditing={setEditing} />}
      <StudentTable students={students} fetchStudents={fetchStudents} setEditing={setEditing} setSelectedStudent={setSelectedStudent} />
    </Container>
  );
};

export default StudentList;
