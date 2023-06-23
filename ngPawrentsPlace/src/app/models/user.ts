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
  updatedAt: string = ''
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
  }

}
