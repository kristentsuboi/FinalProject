import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from 'src/app/models/address';
import { Business } from 'src/app/models/business';
import { User } from 'src/app/models/user';
import { AddressService } from 'src/app/services/address.service';
import { AuthService } from 'src/app/services/auth.service';
import { BusinessService } from 'src/app/services/business.service';

@Component({
  selector: 'app-business',
  templateUrl: './business.component.html',
  styleUrls: ['./business.component.css']
})
export class BusinessComponent {

  selected: Business | null = null;
  loggedInUser: User | null = null;
  editingAddress: Address | null = null;
  newAddress: Address | null = null;
  editBusiness: Business | null = null;

  constructor(private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private businessService: BusinessService,
    private addressService: AddressService){}



    ngOnInit(): void {

      let idStr = this.route.snapshot.paramMap.get('id');
      if (idStr) {
        let id: number = Number.parseInt(idStr);
        if (isNaN(id)) {
          this.router.navigateByUrl('**');
        } else {
         this.reload(id);
        }
      } else {
        this.router.navigateByUrl('home');
      }
      this.getLoggedInUser();
    }


    reload(buinessId: number) {

      this.businessService.show(buinessId).subscribe({
        next: (business) => {
          this.getLoggedInUser();
          this.selected = business;
        },
        error: (theError) => {
          console.error('BusinessComponent.ngOnInit(): Error loading pet.');
          console.error(theError);
          this.router.navigateByUrl('**');
        },
      });

      this.authService.getLoggedInUser().subscribe({
        next: (user) => {
          console.log(user);
          this.loggedInUser = user;

        },
        error: function (theError) {
          console.error('BusinessComponent.ngOnInit(): Error loading user.');
          console.error(theError);
        },
      });
    }



    getLoggedInUser() {
      this.authService.getLoggedInUser().subscribe({
        next: (user) => {
          console.log(user);
          this.loggedInUser = user;
        },
        error: function (theError) {
          console.error('BusinessComponent.reload(): Error loading user.');
          console.error(theError);
        },
      });
    }

    getBusiness(businessId: number){
      this.businessService.show(businessId).subscribe({
        next: (business) => {
          this.selected = business;
        },
        error: function (theError) {
          console.error('BusinessComponent.reload(): Error loading pet.');
          console.error(theError);
        }
      })
    }

    editAddress(businessId: number, addressId: number, editingAddress: Address) {
      console.log(editingAddress);
      this.addressService.updateForBusiness(businessId, editingAddress).subscribe({
        next: (result) => {
          this.editingAddress = null;
          this.reload(businessId);
        },
        error: (nojoy) => {
          console.error('BusinessComponent.updateAddresss(): error updating address:');
          console.error(nojoy);
        },
      });
    }

    deleteAddress(addressId: number, businessId: number) {
      if (this.loggedInUser) {
      this.addressService.destroyForBusiness(businessId, addressId).subscribe({
        next: (result) => {
          this.router.navigateByUrl('business/' + businessId);
          this.reload(businessId);
        },
        error: (nojoy) => {
          console.error('BusinessComponent.deleteAddress(): error deleting address:');
          console.error(nojoy);
        },
      });
      }
    }

    addAddress(businessId: number, newAddress: Address): void {
      console.log(newAddress);
      this.addressService.createForUser(businessId, newAddress).subscribe({
        next: (result) => {
          this.newAddress = new Address();
          this.reload(businessId);
        },
        error: (nojoy) => {
          console.error('BusinessComponent.addAddress(): error creating address:');
          console.error(nojoy);
        },
      });
    }

    setEditAddress() {
      if (this.selected) {
      this.editingAddress = Object.assign({}, this.selected.address);


      }
    }

    cancelEditAddress(businessId: number) {
      this.editingAddress = null;
      this.reload(businessId);
    }




    setEditBusiness(){
      if(this.selected){
        this.editBusiness = Object.assign({}, this.selected);

      }
    }

    editBusinessInfo(editBusiness: Business) {
      this.businessService.update(editBusiness).subscribe({
        next: (result) => {
          this.editBusiness = new Business();
          this.reload(editBusiness.id);
        },
        error: (nojoy) => {
          console.error('BusinessComponent.editBusiness(): error editing business info:');
          console.error(nojoy);
        },

      })
    }




}
