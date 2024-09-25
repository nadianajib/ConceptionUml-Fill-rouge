export interface Reservation {
  id: number;         // ID de la réservation
  dateDebut: Date;   // Date de début
  dateFin: Date;     // Date de fin
  packId?: number;   // ID du pack (optionnel)
}
