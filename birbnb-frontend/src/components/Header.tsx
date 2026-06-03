import Notificaciones from "./Notificaciones/Notificaciones";
import "../styles/Header.css";
import NavLinks from "./Links";
import { useState } from "react";
import { Link } from "react-router";
import SessionButton from "./inputs/SessionButton";
import { useUsuario } from "../context/useAuth";
import type { Usuario } from "../types/Usuario";

export default function Header() {
  const [open, setOpen] = useState(false);
  const usuarioActual: Usuario = useUsuario();

  return (
    <header id="root-header">
      <nav id="root-nav">
        <div id="logo">
          <Link to="/">
            <img src="/birbnb.svg" alt="birbnb logo" width={42} height={42} />
          </Link>
        </div>
        <button
          id="sandwich-logo"
          onClick={() => setOpen(!open)}
          aria-label="Toggle menu"
        >
          ☰
        </button>
        <ul id="nav-links">
          <NavLinks setOpen={setOpen} />
        </ul>
        <div id="session-div">
          <SessionButton user={usuarioActual} />
        </div>
        <div id="notificaciones">
          <Notificaciones userId={usuarioActual.id.toString() || ""} />
        </div>
      </nav>
      {open && (
        <ul id="sandwich-menu" className="animate-fade-in-down">
          <NavLinks setOpen={setOpen} />
          <SessionButton user={usuarioActual} />
        </ul>
      )}
    </header>
  );
}
