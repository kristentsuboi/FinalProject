import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from 'src/app/models/address';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent {

  loggedInUser: User | null = null;
  editUser: User | null = null;

  newAddress: Address = new Address();
  editingAddress: Address | null = null;

  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {

    let idStr = this.route.snapshot.paramMap.get('id');
    if (idStr) {
      let id: number = Number.parseInt(idStr);
      if (isNaN(id)) {
        this.router.navigateByUrl('**');
      } else {
        this.authService.getLoggedInUser().subscribe({
          next: (user) => {
            console.log(user);
            this.loggedInUser = user;
          },
          error: function (theError) {
            console.error('AccountComponent.ngOnInit(): Error loading user.');
            console.error(theError);
          },
        });
      }
    } else {
      this.router.navigateByUrl('home');
    }
  }

  addAddress(userId: number, newAddress: Address) {

  }

  setEditAddress() {
    if (this.loggedInUser) {
    this.editingAddress = Object.assign({}, this.loggedInUser?.address);
    }
  }

  cancelEditAddress() {
    this.editingAddress = null;
  }

  editAddress(userId: number, addressId: number, editingAddress: Address) {

  }

  deleteAddress(addressId: number) {
    if (this.loggedInUser) {
    // this.addressService.destroy(addressId).subscribe({
    //   next: (result) => {
    //     this.router.navigateByUrl('account/' + this.loggedInUser.id);
    //   },
    //   error: (nojoy) => {
    //     console.error('PetHttpComponent.deletePet(): error deleting pet:');
    //     console.error(nojoy);
    //   },
    // });
    }
  }

}
