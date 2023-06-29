import { ServiceTypeService } from './../../services/service-type.service';
import { Component, OnInit, Sanitizer } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from 'src/app/models/address';
import { Business } from 'src/app/models/business';
import { ServiceType } from 'src/app/models/service-type';
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
  newAddress: Address = new Address();
  editBusiness: Business | null = null;
  newBusiness: Business = new Business();
  selectedServiceType: ServiceType = new ServiceType();
  serviceTypes: ServiceType[] = [];
  typedBusinesses: Business[] = [];
  addBusinessProvider: Business = new Business();

  constructor(private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private businessService: BusinessService,
    private addressService: AddressService,
    private sanitizer: DomSanitizer,
    private serviceTypeService: ServiceTypeService){}



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
        this.router.navigateByUrl('business');
      }
      this.getLoggedInUser();
      this.getServiceTypes();
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



    addBusiness(newBusiness: Business): void {
      console.log(newBusiness);
      newBusiness.serviceTypes.push(this.selectedServiceType)
      this.businessService.create(newBusiness).subscribe({
        next: (result) => {
          this.newBusiness = new Business();
          this.reload(result.id);
          this.selected = result;
        },
        error: (nojoy) => {
          console.error('BusinessComponent.addBuiness(): error creating business:');
          console.error(nojoy);
        },
      });
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
      this.addressService.createForBusiness(businessId, newAddress).subscribe({
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

    cancelEditBusiness(businessId: number) {
      this.editBusiness = null;
      this.reload(businessId);
    }

    editBusinessInfo(editBusiness: Business) {
      this.businessService.update(editBusiness).subscribe({
        next: (result) => {
          this.editBusiness = null;
          this.reload(editBusiness.id);
        },
        error: (nojoy) => {
          console.error('BusinessComponent.editBusiness(): error editing business info:');
          console.error(nojoy);
        },

      })
    }


    sanitizeUrl(zipCode: string){
     return this.sanitizer.bypassSecurityTrustResourceUrl('https://www.google.com/maps?q='+zipCode+'&z=9&output=embed')
      // "'https://www.google.com/maps?q='+selected.address.zipCode+'&z=9&output=embed'"
    }

    getBusinesses(serviceTypeId: number) {
      this.businessService.showByServiceType(serviceTypeId).subscribe({
        next: (result) => {
          this.typedBusinesses = result;
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
    addBusinessEmployer(userId: number, businessId: number) {
      this.businessService.addEmployeetoProvider(userId, businessId).subscribe({
        next: (result) => {
          this.addBusinessProvider = new Business();
          this.getLoggedInUser();
          this.getServiceTypes();
          this.reload(businessId);
          this.router.navigateByUrl('/business/' + businessId)
        },
        error: (nojoy) => {
          console.error(
            'BusinessComponent.addBusinessUsed(): error adding business:' + nojoy
          );
          console.error(nojoy);
        },
      });
    }

    removeBusinessEmployer(userId: number, businessId: number) {
      this.businessService.removeEmployeetoProvider(userId, businessId).subscribe({
        next: (result) => {
          this.addBusinessProvider = new Business();
          this.getLoggedInUser();
          this.getServiceTypes();
          this.router.navigateByUrl('/business')
        },
        error: (nojoy) => {
          console.error(
            'BusinessComponent.removeBusinessEmployer(): error removing business:' + nojoy
          );
          console.error(nojoy);
        },
      });
    }

    getServiceTypes(): void {
      this.serviceTypeService.index().subscribe({
        next: (result) => {
          this.serviceTypes = result;
          console.log(this.serviceTypes)
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



}
