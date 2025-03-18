import React from "react";
import ATMInterface from "./components/ATMInterface";
import { Container, Typography } from "@mui/material";

function App() {
  return (
    <Container maxWidth="sm" style={{ textAlign: "center", marginTop: "50px" }}>
      <Typography variant="h4" gutterBottom>
        ATM Interface
      </Typography>
      <ATMInterface />
    </Container>
  );
}

export default App;
