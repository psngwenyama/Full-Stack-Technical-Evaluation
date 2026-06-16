import { Link } from "react-router-dom";
import styles from "./InvestorCard.module.css";

function InvestorCard({ investor }) {
  return (
    <div className={styles.card}>
      <h3 className={styles.name}>
        {investor.firstName} {investor.lastName}
      </h3>

      <p className={styles.email}>{investor.email}</p>

      <Link className={styles.link} to={`/investor/${investor.id}`}>
        View Portfolio
      </Link>
    </div>
  );
}

export default InvestorCard;