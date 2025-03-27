import React, { useState, useEffect } from "react";
import { TextField, Button, Card, CardContent, Typography } from "@mui/material";
import { addStudent, updateStudent } from "../services/api";

const StudentForm = ({ student, fetchStudents, setEditing }) => {
  const [formData, setFormData] = useState({ name: "", rollNumber: "", grade: "" });

  useEffect(() => {
    if (student) setFormData(student);
  }, [student]);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (student) {
      await updateStudent(student.id, formData);
      setEditing(false);
    } else {
      await addStudent(formData);
    }
    fetchStudents();
    setFormData({ name: "", rollNumber: "", grade: "" });
  };

  return (
    <Card sx={{ marginBottom: 3 }}>
      <CardContent>
        <Typography variant="h5">{student ? "Edit Student" : "Add Student"}</Typography>
        <form onSubmit={handleSubmit}>
          <TextField name="name" label="Name" value={formData.name} onChange={handleChange} fullWidth margin="normal" required />
          <TextField name="rollNumber" label="Roll Number" value={formData.rollNumber} onChange={handleChange} fullWidth margin="normal" required />
          <TextField name="grade" label="Grade" value={formData.grade} onChange={handleChange} fullWidth margin="normal" required />
          <Button type="submit" variant="contained" color="primary">{student ? "Update" : "Add"}</Button>
        </form>
      </CardContent>
    </Card>
  );
};

export default StudentForm;
