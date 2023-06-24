export class MedicalNote {
  id: number;
  notes: string;
  createAt: string;
  updatedAt: string;

  constructor(
    id: number = 0,
  notes: string = "",
  createAt: string = "",
  updatedAt: string =  ""
  ){
    this.id = id;
    this.notes = notes;
    this.createAt = createAt;
    this.updatedAt = updatedAt;
  }

}
