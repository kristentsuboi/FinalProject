import { Pet } from "./pet";

export class Shot {
  id: number;
  name: string;
  dateAdministered: string;
  frequency: string;
  notes: string;
  pet: Pet | null;

  constructor(
    id: number = 0,
    name: string = "",
    dateAdministered: string = "",
    frequency: string = "",
    notes: string = "",
    pet: Pet | null = null
  ){
    this.id = id;
    this.name = name;
    this.dateAdministered = dateAdministered;
    this.frequency = frequency;
    this.notes = notes;
    this.pet = pet;
  }
}
