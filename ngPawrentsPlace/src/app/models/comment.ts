import { User } from './user';
export class Comment {
  id: number;
  body: string;
  createdAt: string;
  updatedAt: string;
  imageUrl: string;
  topic: string;
  mainComment: Comment | null;
  replies: Comment[];
  user: User | null;



  constructor(
    id: number = 0,
  body: string = "",
  createdAt: string = "",
  updatedAt: string = "",
  imageUrl: string = "",
  topic: string = "",
  mainComment: Comment | null = null,
  replies: Comment[] = [],
  user: User | null = null
  ){
    this.id = id;
    this.body = body;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.imageUrl = imageUrl;
    this.topic = topic;
    this.mainComment = mainComment;
    this.replies = replies;
    this.user = user;
  }

}
