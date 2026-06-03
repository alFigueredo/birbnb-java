import { Link } from "react-router";
import type { Usuario } from "../../types/Usuario";

interface Props {
  user: Usuario;
}

export default function SessionButton({ user }: Props) {
  return (
    <Link to="/login">
      <button id="session-button" className="transition">
        {user.email || "Iniciar sesión"}
      </button>
    </Link>
  );
}
