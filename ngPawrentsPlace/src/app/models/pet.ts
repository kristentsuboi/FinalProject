import { Diet } from "./diet";
import { MedicalNote } from "./medical-note";
import { Medication } from "./medication";
import { PetComment } from "./pet-comment";
import { Shot } from "./shot";
import { User } from "./user";

export class Pet {

  id: number;
  name: string;
  species: string;
  breed: string;
  height: number;
  weight: number;
  gender: string;
  birth: string;
  color: string;
  about: string;
  microchipped: boolean;
  imageUrl: string;
  allergies: string;
  enabled: boolean;
  createdAt: string;
  updatedAt: string;
  user: User | null;
  diets: Diet[];
  shots: Shot[];
  medications: Medication[];
  medicalNotes: MedicalNote[];
  petComments: PetComment[];
  providers: User[];


  constructor(
    id: number = 0,
    name: string = '',
    species: string = '',
    breed: string = '',
    height: number = 0,
    weight: number = 0,
    gender: string = '',
    birth: string = '',
    color: string = '',
    about: string = '',
    microchipped: boolean = false,
    imageUrl: string = '',
    allergies: string = '',
    enabled: boolean = false,
    createdAt: string = '',
    updatedAt: string = '',
    user: User | null = null,
    diets: Diet[] = [],
    shots: Shot[] = [],
    medications: Medication[] = [],
    medicalNotes: MedicalNote[] = [],
    petComments: PetComment[] = [],
    providers: User[] = []
  ) {
    this.id = id;
    this.name = name;
    this.species = species;
    this.breed = breed;
    this.height = height;
    this.weight = weight;
    this.gender = gender;
    this.birth = birth;
    this.color = color;
    this.about = about;
    this.microchipped = microchipped;
    this.imageUrl = imageUrl;
    this.allergies = allergies;
    this.enabled = enabled;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.user = user;
    this.diets = diets;
    this.shots = shots;
    this.medications = medications;
    this.medicalNotes = medicalNotes;
    this.petComments = petComments;
    this.providers = providers;
  }
}
