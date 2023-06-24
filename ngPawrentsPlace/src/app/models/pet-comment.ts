export class PetComment {
  id: number;
  body: string;
  createdAt: string;
  updatedAt: string;
  imageUrl: string;

  constructor(
    id: number = 0,
    body: string = "",
    createdAt: string = "",
    updatedAt: string = "",
    imageUrl: string = ""
  ){
    this.id = id;
    this.body = body;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.imageUrl = imageUrl;
  }
}
