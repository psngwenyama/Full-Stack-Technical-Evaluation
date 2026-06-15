import { Link, NavLink } from "react-router-dom";
import { useState } from "react";
import styles from "./Navbar.module.css";
import logo from "../assets/logo.png";

function Navbar() {
  const [menuOpen, setMenuOpen] = useState(false);

  return (
    <nav className={styles.navbar}>
      <Link to="/" className={styles.brand}>
        <img
          src={logo}
          alt="Enviro365"
          className={styles.logo}
        />
      </Link>

      <button
        className={styles.menuButton}
        onClick={() => setMenuOpen(!menuOpen)}
      >
        ☰
      </button>

      <div
        className={`${styles.links} ${
          menuOpen ? styles.showMenu : ""
        }`}
      >
        <NavLink
          to="/"
          end
          className={({ isActive }) =>
            isActive ? styles.activeLink : ""
          }
          onClick={() => setMenuOpen(false)}
        >
          Dashboard
        </NavLink>

        <NavLink
          to="/withdrawals"
          className={({ isActive }) =>
            isActive ? styles.activeLink : ""
          }
          onClick={() => setMenuOpen(false)}
        >
          Withdrawals
        </NavLink>
      </div>
    </nav>
  );
}

export default Navbar;