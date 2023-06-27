import { Pet } from "./pet";
import { User } from "./user";

export class PetComment {
  id: number;
  body: string;
  createdAt: string;
  updatedAt: string;
  imageUrl: string;
  user: User | null;
  pet: Pet | null;
  mainComment: PetComment | null;
  replies: PetComment[];

  constructor(
    id: number = 0,
    body: string = "",
    createdAt: string = "",
    updatedAt: string = "",
    imageUrl: string = "",
    user: User | null = null,
    pet: Pet | null = null,
    mainComment: PetComment | null = null,
  replies: PetComment[] = []
  ){

    this.id = id;
    this.body = body;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.imageUrl = imageUrl;
    this.user = user;
    this.pet = pet;
    this.mainComment = mainComment;
    this.replies = replies;
  }
}
