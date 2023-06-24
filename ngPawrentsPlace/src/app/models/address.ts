export class Address {
  id: number;
  street: string;
  city: string;
  state: string;
  zipCode: string;

  constructor(
    id: number,
  street: string,
  city: string,
  state: string,
  zipCode: string
  ) {
    this.id = id;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
  }
}
