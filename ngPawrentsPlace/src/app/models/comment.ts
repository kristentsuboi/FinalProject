export class Comment {
  id: number;
  body: string;
  createdAt: string;
  updatedAt: string;
  imageUrl: string;
  topic: string;

  constructor(
    id: number = 0,
  body: string = "",
  createdAt: string = "",
  updatedAt: string = "",
  imageUrl: string = "",
  topic: string = ""
  ){
    this.id = id;
    this.body = body;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.imageUrl = imageUrl;
    this.topic = topic;
  }

}
