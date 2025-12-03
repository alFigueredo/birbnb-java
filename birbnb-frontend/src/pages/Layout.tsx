import Footer from "../components/Footer";
import Header from "../components/Header";
import UserProvider from "../context/UserProvider";
import { Outlet } from "react-router";

export default function Layout() {
  return (
    <UserProvider>
      <Header />
      <Outlet />
      <Footer />
    </UserProvider>
  );
}
