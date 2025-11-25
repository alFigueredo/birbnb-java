import type { Dispatch, SetStateAction } from "react";
import { Link } from "react-router";

const links = [
  { name: "Alojamientos", href: "/alojamientos" },
  { name: "Reservas", href: "/reservas" },
];

type Props = {
  setOpen: Dispatch<SetStateAction<boolean>>;
};

export default function NavLinks({ setOpen }: Props) {
  return (
    <>
      {links.map((link) => (
        <li key={link.name}>
          <Link
            className="text-black font-medium hover:text-neutral-100 transition-colors duration-200"
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
