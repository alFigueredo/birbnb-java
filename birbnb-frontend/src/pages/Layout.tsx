import Footer from "../components/Footer";
import Header from "../components/Header";
import AuthProvider from "../context/AuthProvider";
import { Outlet } from "react-router";

export default function Layout() {
  return (
    <AuthProvider>
      <Header />
      <Outlet />
      <Footer />
    </AuthProvider>
  );
}
