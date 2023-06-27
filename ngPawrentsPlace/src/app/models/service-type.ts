export class ServiceType {
  id: number;
  name: string;
  description: string;
  imageUrl: string;

  constructor(
    id: number = 0,
    name: string = "Please Choose",
    description: string = "",
    imageUrl: string = ""
  ){
    this.id = id;
    this.name = name;
    this.description = description;
    this.imageUrl = imageUrl;
  }
}
