export interface Service {
    id?: number;
    nom: string;
    description: string;
    prix: number;
    image: string;
    typeService: string; // Par exemple: 'NETTOYAGESTANDARD', 'CUISINE', 'SALLE_DE_BAIN'
  }
  