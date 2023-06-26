import { Pet } from './../../models/pet';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Business } from 'src/app/models/business';
import { ServiceType } from 'src/app/models/service-type';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { BusinessService } from 'src/app/services/business.service';
import { PetService } from 'src/app/services/pet.service';
import { ServiceTypeService } from 'src/app/services/service-type.service';

@Component({
  selector: 'app-pet-list',
  templateUrl: './pet-list.component.html',
  styleUrls: ['./pet-list.component.css'],
})
export class PetListComponent {
  pets: Pet[] = [];
  newPet: Pet = new Pet();
  editPet: Pet | null = null;
  selected: Pet | null = null;

  loggedInUser: User | null = null;

  selectedServiceType: ServiceType = new ServiceType();
  serviceTypes: ServiceType[] = [];

  typedBusinesses: Business[] = [];
  addBusiness: Business = new Business();

  constructor(
    private petService: PetService,
    private authService: AuthService,
    private serviceTypeService: ServiceTypeService,
    private businessService: BusinessService,
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
            // this.selected = pet;
            this.router.navigateByUrl('pets/' + pet.id);
          },
          error: (theError) => {
            console.error('PetListComponent.ngOnInit(): Error loading pet.');
            console.error(theError);
            this.router.navigateByUrl('**');
          },
        });
      }
    } else {
      this.getLoggedInUser();
      this.getServiceTypes();
      this.reload();
    }
  }

  getLoggedInUser() {
    this.authService.getLoggedInUser().subscribe({
      next: (user) => {
        this.loggedInUser = user;
      },
      error: function (theError) {
        console.error('PetListComponent.reload(): Error loading user.');
        console.error(theError);
      },
    });
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

  calculateAge(birthdateStr: string): string {
    let birthdate = new Date(birthdateStr);
    let timeDiff = Math.abs(Date.now() - birthdate.getTime());
    let years = Math.floor(timeDiff / (1000 * 3600 * 24) / 365.25);
    let rmngTime =
      timeDiff / (1000 * 3600 * 24) / 365.25 -
      Math.floor(timeDiff / (1000 * 3600 * 24) / 365.25);
    let months = Math.floor(rmngTime * 12);
    return years + ' years, ' + months + ' months old';
  }

  getServiceTypes(): void {
    this.serviceTypeService.index().subscribe({
      next: (result) => {
        this.serviceTypes = result;
      },
      error: (nojoy) => {
        console.error(
          'PetListHttpComponent.getServiceTypes(): error indexing service types:' +
            nojoy
        );
        console.error(nojoy);
      },
    });
  }

  getBusinesses(serviceTypeId: number) {
    this.businessService.showByServiceType(serviceTypeId).subscribe({
      next: (result) => {
        this.typedBusinesses = result;
        this.reload();
      },
      error: (nojoy) => {
        console.error(
          'PetListHttpComponent.getBusinesses(): error getting businesses by type:' +
            nojoy
        );
        console.error(nojoy);
      },
    });
  }

  addBusinessUsed(userId: number, businessId: number) {

  }

  addPet(newPet: Pet): void {
    console.log(newPet);
    this.petService.create(newPet).subscribe({
      next: (result) => {
        this.newPet = new Pet();
        this.reload();
        this.selected = result;
        this.router.navigateByUrl('pets/' + result.id);
      },
      error: (nojoy) => {
        console.error(
          'PetListHttpComponent.addTodo(): error creating pet:' + nojoy
        );
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
      },
      error: (nojoy) => {
        console.error('PetListHttpComponent.deleteTodo(): error deleting pet:');
        console.error(nojoy);
      },
    });
  }
}
