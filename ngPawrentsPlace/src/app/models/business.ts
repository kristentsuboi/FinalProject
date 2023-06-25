import { ServiceType } from "./service-type";

export class Business {
  id: number;
  name: string;
  about: string;
  phone: string;
  imageUrl: string;
  serviceTypes: ServiceType[];

  constructor(
  id: number = 0,
  name: string = "",
  about: string = "",
  phone: string = "",
  imageUrl: string = "",
  serviceTypes: ServiceType[] = []
  ){
    this.id = id;
    this.name = name;
    this.about = about;
    this.phone = phone;
    this.imageUrl = imageUrl;
    this.serviceTypes = serviceTypes;
  }
}
