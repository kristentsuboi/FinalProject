import { Pet } from './../../models/pet';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PetService } from 'src/app/services/pet.service';

@Component({
  selector: 'app-pet-list',
  templateUrl: './pet-list.component.html',
  styleUrls: ['./pet-list.component.css']
})
export class PetListComponent {

  pets: Pet[] = [];
  newPet: Pet = new Pet();
  editPet: Pet | null = null;
  selected: Pet | null = null;

    constructor(
    private petService: PetService,
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



}
