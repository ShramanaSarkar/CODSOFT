import React, { useState } from "react";
import { TextField, Button, Card, CardContent, Typography, Grid } from "@mui/material";
import axios from "axios";

const ATMInterface = () => {
  const [accountId, setAccountId] = useState("");
  const [amount, setAmount] = useState("");
  const [balance, setBalance] = useState(null);
  const [message, setMessage] = useState("");

  const handleDeposit = async () => {
    if (!accountId || !amount) {
      setMessage("Please enter both Account ID and Amount.");
      return;
    }
    try {
      const response = await axios.post(`http://localhost:8080/api/atm/deposit`, {
        accountId,
        amount: parseFloat(amount),
      });
      setBalance(response.data.balance);
      setMessage("Deposit successful!");
    } catch (error) {
      setMessage("Error processing deposit.");
    }
  };

  const handleWithdraw = async () => {
    if (!accountId || !amount) {
      setMessage("Please enter both Account ID and Amount.");
      return;
    }
    try {
      const response = await axios.post(`http://localhost:8080/api/atm/withdraw`, {
        accountId,
        amount: parseFloat(amount),
      });
      setBalance(response.data.balance);
      setMessage("Withdrawal successful!");
    } catch (error) {
      setMessage("Insufficient funds or invalid request.");
    }
  };

  const checkBalance = async () => {
    if (!accountId) {
      setMessage("Please enter Account ID.");
      return;
    }
    try {
      const response = await axios.get(`http://localhost:8080/api/atm/balance/${accountId}`);
      setBalance(response.data);
      setMessage("Balance fetched successfully.");
    } catch (error) {
      setMessage("Error fetching balance.");
    }
  };

  return (
    <Card sx={{ maxWidth: 400, margin: "auto", padding: 2, mt: 5 }}>
      <CardContent>
        <Typography variant="h5" align="center" gutterBottom>
          ATM Interface
        </Typography>

        {/* Account ID Input */}
        <TextField
          label="Account ID"
          variant="outlined"
          fullWidth
          value={accountId}
          onChange={(e) => setAccountId(e.target.value)}
          margin="normal"
        />

        {/* Amount Input */}
        <TextField
          label="Amount"
          variant="outlined"
          fullWidth
          type="number"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
          margin="normal"
        />

        {/* Buttons */}
        <Grid container spacing={2} justifyContent="center">
          <Grid item>
            <Button variant="contained" color="primary" onClick={handleDeposit}>
              Deposit
            </Button>
          </Grid>
          <Grid item>
            <Button variant="contained" color="secondary" onClick={handleWithdraw}>
              Withdraw
            </Button>
          </Grid>
          <Grid item>
            <Button variant="contained" color="success" onClick={checkBalance}>
              Check Balance
            </Button>
          </Grid>
        </Grid>

        {/* Display Balance */}
        {balance !== null && (
          <Typography variant="h6" align="center" sx={{ mt: 2 }}>
            Balance: ₹{balance}
          </Typography>
        )}

        {/* Display Message */}
        {message && (
          <Typography color="error" align="center" sx={{ mt: 1 }}>
            {message}
          </Typography>
        )}
      </CardContent>
    </Card>
  );
};

export default ATMInterface;
