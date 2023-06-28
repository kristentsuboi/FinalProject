export class ServiceType {
  id: string;
  name: string;
  description: string;
  imageUrl: string;

  constructor(
    id: string = 'select',
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
