import styles from "./Footer.module.css";

function Footer() {
  const year = new Date().getFullYear();

  return (
    <footer className={styles.footer}>
      <div className={styles.content}>
        <p>
          © {year} Enviro365 Assessment System
        </p>

        <p>
          Created by{" "}
          <span className={styles.author}>
            Sthembiso Ngwenyama
          </span>
        </p>
      </div>
    </footer>
  );
}

export default Footer;