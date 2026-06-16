/* eslint-disable react-hooks/immutability */
import { useEffect, useState } from "react";
import api from "../api/api";
import InvestorCard from "../components/InvestorCard";
import styles from "./Dashboard.module.css";

function Dashboard() {
  const [investors, setInvestors] = useState([]);

  useEffect(() => {
    fetchInvestors();
  }, []);

  const fetchInvestors = async () => {
    try {
      const response = await api.get("/investors");
      setInvestors(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className={styles.container}>
      <h1 className={styles.title}>Investors</h1>

      <div className={styles.grid}>
        {investors.map((investor) => (
          <InvestorCard key={investor.id} investor={investor} />
        ))}
      </div>
    </div>
  );
}

export default Dashboard;