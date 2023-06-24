export class Business {
  id: number;
  name: string;
  about: string;
  phone: string;
  imageUrl: string;

  constructor(
    id: number = 0,
  name: string = "",
  about: string = "",
  phone: string = "",
  imageUrl: string = ""
  ){
    this.id = id;
    this.name = name;
    this.about = about;
    this.phone = phone;
    this.imageUrl = imageUrl
  }
}
