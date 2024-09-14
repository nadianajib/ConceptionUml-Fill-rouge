import { Personne } from './Personne';
import { Reservation } from './Reservation';

export class Utilisateur extends Personne {
  reservation: Reservation[] | undefined; // Liste des réservations de l'utilisateur
}
