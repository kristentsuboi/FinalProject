import { Address } from "./address";
import { Business } from "./business";
import { Pet } from "./pet";

export class User {


  id: number;
  username: string;
  password: string;
  enabled: boolean;
  role: string;
  email: string;
  phone: string;
  imageUrl: string;
  createdAt: string;
  updatedAt: string;
  address: Address | null;
  business: Business | null;
  petClients: Pet[];
  businessesUsed: Business[];

  constructor(
  id: number = 0,
  username: string = '',
  password: string = '',
  enabled: boolean = false,
  role: string = '',
  email: string = '',
  phone: string = '',
  imageUrl: string = '',
  createdAt: string = '',
  updatedAt: string = '',
  address: Address | null = null,
  business: Business | null = null,
  petClients: Pet[] = [],
  businessesUsed: Business[] = []
  ) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.role = role;
    this.email = email;
    this.phone = phone;
    this.imageUrl = imageUrl;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.address = address;
    this.business = business;
    this.petClients = petClients;
    this.businessesUsed = businessesUsed;
  }

}
