import { Pack } from './Pack';
import { Utilisateur } from './Utilisateur';

export class Reservation {
  id!: number;
  dateDebut!: string;
  dateFin!: string;
  utilisateur!: Utilisateur;  // Lien vers l'objet Utilisateur complet
  pack!: Pack;                // Lien vers l'objet Pack complet
}
