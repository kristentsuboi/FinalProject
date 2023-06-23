import { Diet } from "./diet";

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
  diets: Diet[];

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
    diets: Diet[] = []
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
    this.diets = diets;
  }
}
