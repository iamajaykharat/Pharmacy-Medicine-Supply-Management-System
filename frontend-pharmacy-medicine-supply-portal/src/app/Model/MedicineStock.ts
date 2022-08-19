export interface MedicineStock {
  medicineId: number;
  medicineName: string;
  chemicalComposition: string;
  targetAilment: string;
  dateOfExpiry: Date;
  numberOfTabletsInStock: number;
  pharmacyName: string;
}
