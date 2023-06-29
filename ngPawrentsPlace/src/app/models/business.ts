import { Address } from "./address";
import { ServiceType } from "./service-type";

export class Business {
  id: number;
  name: string;
  about: string;
  phone: string;
  imageUrl: string;
  serviceTypes: ServiceType[];
  address: Address | null;


  constructor(
  id: number = 0,
  name: string = "",
  about: string = "",
  phone: string = "",
  imageUrl: string = "",
  serviceTypes: ServiceType[] = [],
  address: Address | null = null
  ){
    this.id = id;
    this.name = name;
    this.about = about;
    this.phone = phone;
    this.imageUrl = imageUrl;
    this.serviceTypes = serviceTypes;
    this.address = address;
  }
}
