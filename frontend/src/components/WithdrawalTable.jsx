import styles from "./WithdrawalTable.module.css";

function WithdrawalTable({ withdrawals }) {
  if (!withdrawals || withdrawals.length === 0) {
    return <div className={styles.empty}>No withdrawals found.</div>;
  }

  const getStatusClass = (status) => {
    if (status === "APPROVED") return styles.approved;
    if (status === "REJECTED") return styles.rejected;
    return styles.pending;
  };

  return (
    <div className={styles.tableWrapper}>
      <table className={styles.table}>
        <thead>
          <tr>
            <th>ID</th>
            <th>Investor</th>
            <th>Portfolio</th>
            <th>Product</th>
            <th>Amount</th>
            <th>Reason</th>
            <th>Status</th>
            <th>Created At</th>
          </tr>
        </thead>

        <tbody>
          {withdrawals.map((withdrawal) => (
            <tr key={withdrawal.id}>
              <td>{withdrawal.id}</td>
              <td>{withdrawal.investorName}</td>
              <td>{withdrawal.portfolioName}</td>
              <td>{withdrawal.productName}</td>
              <td>R {Number(withdrawal.amount).toLocaleString()}</td>
              <td>{withdrawal.reason}</td>
              <td>
                <span className={`${styles.status} ${getStatusClass(withdrawal.status)}`}>
                  {withdrawal.status}
                </span>
              </td>
              <td>
                {withdrawal.createdAt
                  ? new Date(withdrawal.createdAt).toLocaleString()
                  : "N/A"}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default WithdrawalTable;