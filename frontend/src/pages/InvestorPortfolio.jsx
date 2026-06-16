/* eslint-disable react-hooks/immutability */
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../api/api";
import styles from "./InvestorPortfolio.module.css";

function InvestorPortfolio() {
  const { id } = useParams();

  const [portfolioData, setPortfolioData] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchPortfolio();
  }, []);

  const fetchPortfolio = async () => {
    try {
      const response = await api.get(`/investors/${id}/portfolio`);

      setPortfolioData(response.data);
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return <div className={styles.loading}>Loading Portfolio...</div>;
  }

  return (
    <div className={styles.container}>
      <div className={styles.header}>
        <h1>{portfolioData.investorName}</h1>
        <p>Investor Portfolio Overview</p>
      </div>

      {portfolioData.portfolios.map((portfolio) => (
        <div key={portfolio.id} className={styles.portfolioCard}>
          <div className={styles.portfolioHeader}>
            <h2>{portfolio.portfolioName}</h2>

            <span className={styles.balance}>
              R {portfolio.balance?.toLocaleString()}
            </span>
          </div>

          <div className={styles.productsGrid}>
            {portfolio.products.map((product) => (
              <div key={product.id} className={styles.productCard}>
                <h3>{product.productName}</h3>

                <p>
                  <strong>Type:</strong>{" "}
                  <span className={styles.productType}>
                    {product.productType}
                  </span>
                </p>

                <p>
                  <strong>Balance:</strong> R{" "}
                  {product.balance?.toLocaleString()}
                </p>
              </div>
            ))}
          </div>
        </div>
      ))}
    </div>
  );
}

export default InvestorPortfolio;
