import Notificaciones from "./Notificaciones/Notificaciones";
import "../styles/Header.css";
import NavLinks from "./Links";
import { useState } from "react";
import useUsuario from "../context/useUsuario.tsx";
import SetUsuario from "./inputs/SetUsuario";
import { Link } from "react-router";

export default function Header() {
  const [open, setOpen] = useState(false);
  const { usuarios, usuarioActual, setUsuarioActual } = useUsuario();

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
        <div id="usuario-setter">
          <SetUsuario
            usuarios={usuarios}
            usuarioActual={usuarioActual}
            setUsuarioActual={setUsuarioActual}
          />
        </div>
        <div id="notificaciones">
          <Notificaciones userId={usuarioActual?.id.toString() || ""} />
        </div>
      </nav>
      {open && (
        <ul id="sandwich-menu" className="animate-fade-in-down">
          <NavLinks setOpen={setOpen} />
          <SetUsuario
            usuarios={usuarios}
            usuarioActual={usuarioActual}
            setUsuarioActual={setUsuarioActual}
          />
        </ul>
      )}
    </header>
  );
}
