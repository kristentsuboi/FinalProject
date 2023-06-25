import { AuthService } from './../../services/auth.service';
import { Pet } from './../../models/pet';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Diet } from 'src/app/models/diet';
import { MedicalNote } from 'src/app/models/medical-note';
import { Medication } from 'src/app/models/medication';
import { PetComment } from 'src/app/models/pet-comment';
import { Shot } from 'src/app/models/shot';
import { User } from 'src/app/models/user';
import { DietService } from 'src/app/services/diet.service';
import { MedicalNoteService } from 'src/app/services/medical-note.service';
import { MedicationService } from 'src/app/services/medication.service';
import { PetService } from 'src/app/services/pet.service';
import { ShotService } from 'src/app/services/shot.service';

@Component({
  selector: 'app-pet',
  templateUrl: './pet.component.html',
  styleUrls: ['./pet.component.css'],
})
export class PetComponent {
  pets: Pet[] = [];
  newPet: Pet = new Pet();
  editPet: Pet | null = null;
  selected: Pet | null = null;

  loggedInUser: User | null = null;
  user: User | null = null;

  newDiet: Diet = new Diet();
  editDiet: Diet | null = null;

  newShot: Shot = new Shot();
  editShot: Shot | null = null;

  newMedication: Medication = new Medication();
  editMedication: Medication | null = null;

  newMedicalNote: MedicalNote = new MedicalNote();
  editMedicalNote: MedicalNote | null = null;

  newPetComment: PetComment = new PetComment();
  editPetComment: PetComment | null = null;

  newUser: User = new User();
  editUser: User | null = null;

  constructor(
    private petService: PetService,
    private authService: AuthService,
    private dietService: DietService,
    private shotService: ShotService,
    private medicationService: MedicationService,
    private medicalNoteService: MedicalNoteService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {

    let idStr = this.route.snapshot.paramMap.get('id');
    if (!this.selected && idStr) {
      let id: number = Number.parseInt(idStr);
      if (isNaN(id)) {
        this.router.navigateByUrl('**');
      } else {
        this.petService.show(id).subscribe({
          next: (pet) => {
            this.getLoggedInUser();
            this.selected = pet;
          },
          error: (theError) => {
            console.error('PetComponent.ngOnInit(): Error loading pet.');
            console.error(theError);
            this.router.navigateByUrl('**');
          },
        });
      }
    } else {
      this.router.navigateByUrl('pets');
    }
  }

  getLoggedInUser() {
    this.authService.getLoggedInUser().subscribe({
      next: (user) => {
        console.log(user);
        this.loggedInUser = user;
      },
      error: function (theError) {
        console.error('PetComponent.reload(): Error loading user.');
        console.error(theError);
      },
    });
  }

  reload(petId: number) {
    this.petService.show(petId).subscribe({
      next: (pet) => {
        this.selected = pet;
      },
      error: function (theError) {
        console.error('PetComponent.reload(): Error loading pet.');
        console.error(theError);
      },
    });
  }

  calculateAge(birthdateStr: string): string {
    let birthdate =  new Date(birthdateStr);
    let timeDiff = Math.abs(Date.now() - birthdate.getTime());
    let years = Math.floor((timeDiff / (1000 * 3600 * 24))/365.25);
    let rmngTime = ((timeDiff / (1000 * 3600 * 24))/365.25) - Math.floor((timeDiff / (1000 * 3600 * 24))/365.25);
    let months = Math.floor(rmngTime * 12);
    return (years + ' years, ' + months + ' months old');
  }

  addPet(newPet: Pet): void {
    console.log(newPet);
    this.petService.create(newPet).subscribe({
      next: (result) => {
        this.newPet = new Pet();
        this.reload(result.id);
        this.selected = result;
      },
      error: (nojoy) => {
        console.error('PetHttpComponent.addPet(): error creating pet:');
        console.error(nojoy);
      },
    });
  }

  setEditPet() {
    this.editPet = Object.assign({}, this.selected);
  }

  cancelEdit() {
    this.editPet = null;
  }

  updatePet(editPet: Pet): void {
    console.log(editPet);
    this.petService.update(editPet).subscribe({
      next: (result) => {
        this.editPet = null;
        this.reload(result.id);
        this.selected = result;
      },
      error: (nojoy) => {
        console.error('PetHttpComponent.updatePet(): error updating pet:');
        console.error(nojoy);
      },
    });
  }

  displayPet(selectedPet: Pet) {
    this.router.navigateByUrl('pets/' + selectedPet.id);
    this.selected = selectedPet;
  }

  displayTable() {
    this.router.navigateByUrl('pets');
    this.selected = null;
  }

  deletePet(id: number) {
    this.petService.destroy(id).subscribe({
      next: (result) => {
        this.router.navigateByUrl('pets');
      },
      error: (nojoy) => {
        console.error('PetHttpComponent.deletePet(): error deleting pet:');
        console.error(nojoy);
      },
    });
  }

  addDiet(petId: number, newDiet: Diet): void {
    console.log(newDiet);
    this.dietService.create(petId, newDiet).subscribe({
      next: (result) => {
        this.newDiet = new Diet();
        this.reload(petId);

      },
      error: (nojoy) => {
        console.error('PetHttpComponent.addDiet(): error creating diet:');
        console.error(nojoy);
      },
    });
  }

  updateDiet(petId: number, editDiet: Diet): void {
    console.log(editDiet);
    this.dietService.update(petId, editDiet).subscribe({
      next: (result) => {
        this.editDiet = null;
        this.reload(petId);
      },
      error: (nojoy) => {
        console.error('PetHttpComponent.updateDiet(): error updating diet:');
        console.error(nojoy);
      },
    });
  }

  cancelEditDiet(petId: number) {
    this.editDiet = null;
    this.reload(petId);
  }

  deleteDiet(petId: number, dietId: number) {
    this.dietService.destroy(petId, dietId).subscribe({
      next: (result) => {
        this.reload(petId);
        this.editDiet = null;
      },
      error: (nojoy) => {
        console.error('PetHttpComponent.deleteDiet(): error deleting diet:');
        console.error(nojoy);
      },
    });
  }

  addShot(petId: number, newShot: Shot): void {
    console.log(newShot);
    this.shotService.create(petId, newShot).subscribe({
      next: (result) => {
        this.newShot = new Shot();
        this.reload(petId);

      },
      error: (nojoy) => {
        console.error('PetHttpComponent.addShot(): error creating shot:');
        console.error(nojoy);
      },
    });
  }

  updateShot(petId: number, editShot: Shot): void {
    console.log(editShot);
    this.shotService.update(petId, editShot).subscribe({
      next: (result) => {
        this.editShot = null;
        this.reload(petId);
      },
      error: (nojoy) => {
        console.error('PetHttpComponent.updateShot(): error updating shot:');
        console.error(nojoy);
      },
    });
  }

  cancelEditShot(petId: number) {
    this.editShot = null;
    this.reload(petId);
  }

  deleteShot(petId: number, shotId: number) {
    this.shotService.destroy(petId, shotId).subscribe({
      next: (result) => {
        this.reload(petId);
        this.editShot = null;
      },
      error: (nojoy) => {
        console.error('PetHttpComponent.deleteShot(): error deleting shot:');
        console.error(nojoy);
      },
    });
  }

  addMedication(petId: number, newMedication: Medication): void {
    console.log(newMedication);
    this.medicationService.create(petId, newMedication).subscribe({
      next: (result) => {
        this.newMedication = new Medication();
        this.reload(petId);

      },
      error: (nojoy) => {
        console.error('PetHttpComponent.addMedication(): error creating medication:');
        console.error(nojoy);
      },
    });
  }

  updateMedication(petId: number, editMedication: Medication): void {
    console.log(editMedication);
    this.medicationService.update(petId, editMedication).subscribe({
      next: (result) => {
        this.editMedication = null;
        this.reload(petId);
      },
      error: (nojoy) => {
        console.error('PetHttpComponent.updateMedication(): error updating medication:');
        console.error(nojoy);
      },
    });
  }

  cancelEditMedication(petId: number) {
    this.editMedication = null;
    this.reload(petId);
  }

  deleteMedication(petId: number, medicationId: number) {
    this.medicationService.destroy(petId, medicationId).subscribe({
      next: (result) => {
        this.reload(petId);
        this.editMedication = null;
      },
      error: (nojoy) => {
        console.error('PetHttpComponent.deleteMedication(): error deleting medication:');
        console.error(nojoy);
      },
    });
  }

  addMedicalNote(petId: number, newMedicalNote: MedicalNote): void {
    console.log(newMedicalNote);
    this.medicalNoteService.create(petId, newMedicalNote).subscribe({
      next: (result) => {
        this.newMedicalNote = new MedicalNote();
        this.reload(petId);

      },
      error: (nojoy) => {
        console.error('PetHttpComponent.addMedicalNote(): error creating medical note:');
        console.error(nojoy);
      },
    });
  }

  updateMedicalNote(petId: number, editMedicalNote: MedicalNote): void {
    console.log(editMedicalNote);
    this.medicalNoteService.update(petId, editMedicalNote).subscribe({
      next: (result) => {
        this.editMedicalNote = null;
        this.reload(petId);
      },
      error: (nojoy) => {
        console.error('PetHttpComponent.updateMedicalNote(): error updating medicalNote:');
        console.error(nojoy);
      },
    });
  }

  cancelEditMedicalNote(petId: number) {
    this.editMedicalNote = null;
    this.reload(petId);
  }

  deleteMedicalNote(petId: number, noteId: number) {
    this.medicalNoteService.destroy(petId, noteId).subscribe({
      next: (result) => {
        this.reload(petId);
        this.editMedicalNote = null;
      },
      error: (nojoy) => {
        console.error('PetHttpComponent.deleteMedicalNote(): error deleting medicalNote:');
        console.error(nojoy);
      },
    });
  }


}
