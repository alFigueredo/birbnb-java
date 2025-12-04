import { useParams } from "react-router";
import AlojamientoInfo from "../../components/Alojamientos/AlojamientoInfo";

export default function AlojamientoPage() {
  const { alojamientoid } = useParams();
  return (
    <main id="aloja-page-main">
      <section id="aloja-page-section">
        <AlojamientoInfo id={alojamientoid ?? ""} />
      </section>
    </main>
  );
}
