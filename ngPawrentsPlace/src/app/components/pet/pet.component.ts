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
      this.reload();
      this.router.navigateByUrl('pets');
    }
  }

  reload() {
    this.petService.index().subscribe({
      next: (petList) => {
        this.pets = petList;
      },
      error: function (theError) {
        console.error('PetListComponent.reload(): Error loading pet list.');
        console.error(theError);
      },
    });
  }

  addPet(newPet: Pet): void {
    console.log(newPet);
    this.petService.create(newPet).subscribe({
      next: (result) => {
        this.newPet = new Pet();
        this.reload();
        this.selected = result;
      },
      error: (nojoy) => {
        console.error('PetListHttpComponent.addTodo(): error creating pet:');
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
        this.reload();
        this.selected = result;
      },
      error: (nojoy) => {
        console.error('PetListHttpComponent.updateTodo(): error updating pet:');
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
        this.reload();
        this.router.navigateByUrl('pets');
      },
      error: (nojoy) => {
        console.error('PetListHttpComponent.deleteTodo(): error deleting pet:');
        console.error(nojoy);
      },
    });
  }

  updateDiet(editDiet: Diet): void {
    console.log(editDiet);
    // this.dietService.update(editDiet).subscribe({
    //   next: (result) => {
    //     this.editDiet = null;
    //   },
    //   error: (nojoy) => {
    //     console.error('PetListHttpComponent.updateTodo(): error updating pet:');
    //     console.error(nojoy);
    //   },
    // });
  }
}
