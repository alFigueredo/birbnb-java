import Footer from "../components/Footer";
import Header from "../components/Header";
import UserProvider from "../context/UserProvider";
import { Outlet } from "react-router";

export default function Layout() {
  return (
    <html lang="en">
      <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="/favicon.ico" sizes="any" />
      </head>
      <body>
        <UserProvider>
          <Header />
          <Outlet />
          <Footer />
        </UserProvider>
      </body>
    </html>
  );
}
