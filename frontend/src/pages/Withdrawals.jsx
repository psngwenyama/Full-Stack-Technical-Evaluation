/* eslint-disable react-hooks/immutability */
import { useEffect, useState } from "react";
import api from "../api/api";
import WithdrawalForm from "../components/WithdrawalForm";
import WithdrawalTable from "../components/WithdrawalTable";
import styles from "./Withdrawals.module.css";

function Withdrawals() {
  const [withdrawals, setWithdrawals] = useState([]);
  const [loading, setLoading] = useState(true);
  const [statusFilter, setStatusFilter] = useState("");

  useEffect(() => {
    fetchWithdrawals();
  }, []);

  const fetchWithdrawals = async () => {
    try {
      const response = await api.get("/withdrawals");
      setWithdrawals(response.data);
    } catch (error) {
      console.error("Failed to fetch withdrawals:", error);
    } finally {
      setLoading(false);
    }
  };

  const downloadCsv = async () => {
    const params = statusFilter ? `?status=${statusFilter}` : "";

    const response = await api.get(`/withdrawals/export${params}`, {
      responseType: "blob",
    });

    const blob = new Blob([response.data], {
      type: "text/csv;charset=utf-8;",
    });

    const url = window.URL.createObjectURL(blob);
    const link = document.createElement("a");

    link.href = url;
    link.download = "withdrawals.csv";
    document.body.appendChild(link);
    link.click();

    link.remove();
    window.URL.revokeObjectURL(url);
  };

  const filteredWithdrawals = statusFilter
    ? withdrawals.filter((withdrawal) => withdrawal.status === statusFilter)
    : withdrawals;

  if (loading) {
    return <div className={styles.loading}>Loading withdrawals...</div>;
  }

  return (
    <div className={styles.container}>
      <header className={styles.header}>
        <h1>Withdrawals</h1>
        <p>Submit withdrawals and view withdrawal history.</p>
      </header>

      <section className={styles.section}>
        <WithdrawalForm onWithdrawalCreated={fetchWithdrawals} />
      </section>

      <section className={styles.section}>
        <div className={styles.historyHeader}>
          <div>
            <h2>Withdrawal History</h2>
            <p>Filter and download withdrawal statements.</p>
          </div>

          <div className={styles.actions}>
            <select
              value={statusFilter}
              onChange={(e) => setStatusFilter(e.target.value)}
            >
              <option value="">All statuses</option>
              <option value="APPROVED">Approved</option>
              <option value="PENDING">Pending</option>
              <option value="REJECTED">Rejected</option>
            </select>

            <button onClick={downloadCsv}>Download CSV</button>
          </div>
        </div>

        <WithdrawalTable withdrawals={filteredWithdrawals} />
      </section>
    </div>
  );
}

export default Withdrawals;