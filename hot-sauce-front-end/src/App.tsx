import "./App.css";
import { Cart } from "./pages/Cart";
import { Home } from "./pages/Home";
import { Routes, Route } from "react-router-dom";
import { UserAccount } from "./pages/UserAccount";
import { Shop } from "./pages/Shop";

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/shop" element={<Shop />} />
        {/* <Route path="/item/:id" element={<ShopItem />} /> */}
        <Route path="/cart" element={<Cart />} />
        <Route path="/account" element={<UserAccount />} />
      </Routes>
    </>
  );
}

export default App;
