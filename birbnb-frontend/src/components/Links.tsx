import type { Dispatch, SetStateAction } from "react";
import { Link } from "react-router";

const links = [
  { name: "Alojamientos", href: "/alojamientos" },
  { name: "Reservas", href: "/reservas" },
];

interface Props {
  setOpen: Dispatch<SetStateAction<boolean>>;
}

export default function NavLinks({ setOpen }: Props) {
  return (
    <>
      {links.map((link) => (
        <li key={link.name}>
          <Link
            className="nav-links transition-colors"
            to={link.href}
            onClick={() => setOpen(false)}
          >
            {link.name}
          </Link>
        </li>
      ))}
    </>
  );
}
