import React from "react";
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button } from "@mui/material";
import { deleteStudent } from "../services/api";

const StudentTable = ({ students, fetchStudents, setEditing, setSelectedStudent }) => {
  const handleEdit = (student) => {
    setSelectedStudent(student);
    setEditing(true);
  };

  const handleDelete = async (id) => {
    await deleteStudent(id);
    fetchStudents();
  };

  return (
    <TableContainer component={Paper}>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>ID</TableCell>
            <TableCell>Name</TableCell>
            <TableCell>Roll Number</TableCell>
            <TableCell>Grade</TableCell>
            <TableCell>Actions</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {students.map((student) => (
            <TableRow key={student.id}>
              <TableCell>{student.id}</TableCell>
              <TableCell>{student.name}</TableCell>
              <TableCell>{student.rollNumber}</TableCell>
              <TableCell>{student.grade}</TableCell>
              <TableCell>
                <Button variant="outlined" color="primary" onClick={() => handleEdit(student)}>Edit</Button>
                <Button variant="outlined" color="secondary" onClick={() => handleDelete(student.id)} style={{ marginLeft: 10 }}>Delete</Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default StudentTable;
