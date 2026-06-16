import { BrowserRouter, Routes, Route } from "react-router-dom";

import Navbar from "./components/Navbar";
import Footer from "./components/Footer";

import Dashboard from "./pages/Dashboard";
import InvestorPortfolio from "./pages/InvestorPortfolio";
import Withdrawals from "./pages/Withdrawals";

import "./App.css";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
       <Routes>
              <Route path="/" element={<Dashboard />} />
              <Route path="/investor/:id" element={<InvestorPortfolio />} />
              <Route path="/withdrawals" element={<Withdrawals />} />
       </Routes>

      <Footer />
    </BrowserRouter>
  );
}

export default App;