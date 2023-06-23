import { Pet } from './../../models/pet';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Diet } from 'src/app/models/diet';
import { DietService } from 'src/app/services/diet.service';
import { PetService } from 'src/app/services/pet.service';

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
  diets: Diet[] = [];
  newDiet: Diet = new Diet();
  editDiet: Diet | null = null;

  constructor(
    private petService: PetService,
    private dietService: DietService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    //check if route param for a todoid is present
    let idStr = this.route.snapshot.paramMap.get('id');
    if (!this.selected && idStr) {
      let id: number = Number.parseInt(idStr);
      if (isNaN(id)) {
        this.router.navigateByUrl('**');
      } else {
        this.petService.show(id).subscribe({
          next: (pet) => {
            this.selected = pet;
          },
          error: (theError) => {
            console.error('PetListComponent.ngOnInit(): Error loading pet.');
            console.error(theError);
            this.router.navigateByUrl('**');
          },
        });
      }
    } else {
      this.router.navigateByUrl('pets');
    }
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
        console.error('PetListHttpComponent.updatePet(): error updating pet:');
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

  cancelEditDiet() {
    this.editDiet = null;
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



}
