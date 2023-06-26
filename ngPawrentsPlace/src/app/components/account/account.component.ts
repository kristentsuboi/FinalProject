import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from 'src/app/models/address';
import { User } from 'src/app/models/user';
import { AddressService } from 'src/app/services/address.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent {

  loggedInUser: User = new User();
  editUser: User | null = null;

  newAddress: Address = new Address();
  editingAddress: Address | null = null;

  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private addressService: AddressService
  ) {}

  ngOnInit(): void {

    let idStr = this.route.snapshot.paramMap.get('id');
    if (idStr) {
      let id: number = Number.parseInt(idStr);
      if (isNaN(id)) {
        this.router.navigateByUrl('**');
      } else {
        this.reload();
      }
    } else {
      this.router.navigateByUrl('home');
    }
  }

  reload() {
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

  addAddress(userId: number, newAddress: Address): void {
    console.log(newAddress);
    this.addressService.createForUser(userId, newAddress).subscribe({
      next: (result) => {
        this.newAddress = new Address();
        this.reload();
      },
      error: (nojoy) => {
        console.error('AccountHttpComponent.addAddress(): error creating address:');
        console.error(nojoy);
      },
    });
  }

  setEditAddress() {
    if (this.loggedInUser) {
    this.editingAddress = Object.assign({}, this.loggedInUser.address);

    }
  }

  cancelEditAddress() {
    this.editingAddress = null;
    this.reload();
  }

  editAddress(userId: number, addressId: number, editingAddress: Address) {
    console.log(editingAddress);
    this.addressService.updateForUser(userId, editingAddress).subscribe({
      next: (result) => {
        this.editingAddress = null;
        this.reload();
      },
      error: (nojoy) => {
        console.error('AccountHttpComponent.updateAddresss(): error updating address:');
        console.error(nojoy);
      },
    });
  }

  deleteAddress(addressId: number) {
    if (this.loggedInUser) {
    this.addressService.destroyForUser(this.loggedInUser.id, addressId).subscribe({
      next: (result) => {
        this.router.navigateByUrl('account/' + this.loggedInUser.id);
        this.reload();
      },
      error: (nojoy) => {
        console.error('AccountHttpComponent.deleteAddress(): error deleting address:');
        console.error(nojoy);
      },
    });
    }
  }

}
