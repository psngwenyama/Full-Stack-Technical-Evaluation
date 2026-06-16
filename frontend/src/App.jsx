import { BrowserRouter, Routes, Route } from "react-router-dom";

import Navbar from "./components/Navbar";
import Footer from "./components/Footer";

import Dashboard from "./pages/Dashboard";

import "./App.css";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
       <Routes>
              <Route path="/" element={<Dashboard />} />
       </Routes>

      <Footer />
    </BrowserRouter>
  );
}

export default App;