import { Pet } from "./pet";

export class Medication {
  id: number;
  name: string;
  lastAdministered: string;
  frequency: string;
  withFood: string;
  dateStarted: string;
  notes: string;
  pet: Pet | null;

  constructor(
    id: number = 0,
    name: string = "",
    lastAdministered: string = "",
    frequency: string = "",
    withFood: string = "",
    dateStarted: string = "",
    notes: string = "",
    pet: Pet | null = null
  ){
    this.id = id;
    this.name = name;
    this.lastAdministered = lastAdministered;
    this.frequency = frequency;
    this.withFood = withFood;
    this.dateStarted = dateStarted;
    this.notes = notes;
    this.pet = pet;
  }
}
