import { useEffect, useState } from "react";
import api from "../api/api";
import styles from "./WithdrawalForm.module.css";

function WithdrawalForm({ onWithdrawalCreated }) {
  const [investors, setInvestors] = useState([]);
  const [portfolioData, setPortfolioData] = useState(null);
  const [message, setMessage] = useState("");
  const [isError, setIsError] = useState(false);

  const [form, setForm] = useState({
    investorId: "",
    portfolioId: "",
    productId: "",
    amount: "",
    reason: "",
  });

  useEffect(() => {
    api.get("/investors").then((res) => setInvestors(res.data));
  }, []);

  const handleInvestorChange = async (e) => {
    const investorId = e.target.value;

    setForm({
      investorId,
      portfolioId: "",
      productId: "",
      amount: "",
      reason: "",
    });

    setPortfolioData(null);
    setMessage("");

    if (investorId) {
      const res = await api.get(`/investors/${investorId}/portfolio`);
      setPortfolioData(res.data);
    }
  };

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");
    setIsError(false);

    try {
      await api.post("/withdrawals", {
        investorId: Number(form.investorId),
        portfolioId: Number(form.portfolioId),
        productId: Number(form.productId),
        amount: Number(form.amount),
        reason: form.reason,
      });

      setMessage("Withdrawal approved successfully.");
      setIsError(false);

      setForm({
        investorId: "",
        portfolioId: "",
        productId: "",
        amount: "",
        reason: "",
      });

      setPortfolioData(null);

      if (onWithdrawalCreated) {
        await onWithdrawalCreated();
      }
    } catch (error) {
      setIsError(true);
      setMessage(error.response?.data?.message || "Withdrawal failed.");
    }
  };

  const selectedPortfolio = portfolioData?.portfolios?.find(
    (p) => p.id === Number(form.portfolioId)
  );

  return (
    <form className={styles.form} onSubmit={handleSubmit}>
      <div className={styles.formHeader}>
        <h2>Create Withdrawal</h2>
        <p>Withdrawals are automatically approved when all business rules pass.</p>
      </div>

      {message && (
        <p className={`${styles.message} ${isError ? styles.error : styles.success}`}>
          {message}
        </p>
      )}

      <div className={styles.formGrid}>
        <div className={styles.field}>
          <label>Investor</label>
          <select
            name="investorId"
            value={form.investorId}
            onChange={handleInvestorChange}
            required
          >
            <option value="">Select investor</option>
            {investors.map((investor) => (
              <option key={investor.id} value={investor.id}>
                {investor.firstName} {investor.lastName}
              </option>
            ))}
          </select>
        </div>

        <div className={styles.field}>
          <label>Portfolio</label>
          <select
            name="portfolioId"
            value={form.portfolioId}
            onChange={handleChange}
            required
            disabled={!portfolioData}
          >
            <option value="">Select portfolio</option>
            {portfolioData?.portfolios?.map((portfolio) => (
              <option key={portfolio.id} value={portfolio.id}>
                {portfolio.portfolioName} - R {Number(portfolio.balance).toLocaleString()}
              </option>
            ))}
          </select>
        </div>

        <div className={styles.field}>
          <label>Product</label>
          <select
            name="productId"
            value={form.productId}
            onChange={handleChange}
            required
            disabled={!selectedPortfolio}
          >
            <option value="">Select product</option>
            {selectedPortfolio?.products?.map((product) => (
              <option key={product.id} value={product.id}>
                {product.productName} ({product.productType}) - R{" "}
                {Number(product.balance).toLocaleString()}
              </option>
            ))}
          </select>
        </div>

        <div className={styles.field}>
          <label>Amount</label>
          <input
            type="number"
            name="amount"
            value={form.amount}
            onChange={handleChange}
            min="1"
            required
            placeholder="Enter amount"
          />
        </div>
      </div>

      <div className={styles.field}>
        <label>Reason</label>
        <textarea
          name="reason"
          value={form.reason}
          onChange={handleChange}
          required
          placeholder="Reason for withdrawal"
        />
      </div>

      <button type="submit">Submit Withdrawal</button>
    </form>
  );
}

export default WithdrawalForm;