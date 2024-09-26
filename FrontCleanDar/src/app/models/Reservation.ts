export interface Reservation {
  id: number;
  dateDebut: string;
  dateFin: string;
  packId: number; // ID du pack
  utilisateurId: number; // ID de l'utilisateur
}
