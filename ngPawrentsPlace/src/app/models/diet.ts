import { Pet } from "./pet";

export class Diet {

  id: number;
  name: string;
  type: string;
  frequency: string;
  notes: string;
  amount: string;
  pet: Pet | null;

  constructor(
    id: number = 0,
    name: string = '',
    type: string = '',
    frequency: string = '',
    notes: string = '',
    amount: string = '',
    pet: Pet | null = null
  ) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.frequency = frequency;
    this.notes = notes;
    this.amount = amount;
    this.pet = pet;
  }
}
