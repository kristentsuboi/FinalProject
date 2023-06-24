import { Pet } from "./pet";
import { User } from "./user";

export class MedicalNote {
  id: number;
  notes: string;
  createdAt: string;
  updatedAt: string;
  user: User | null;
  pet: Pet | null;

  constructor(
  id: number = 0,
  notes: string = "",
  createdAt: string = "",
  updatedAt: string =  "",
  user: User | null = null,
  pet: Pet | null = null
  ){
    this.id = id;
    this.notes = notes;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.user = user;
    this.pet = pet;
  }

}
