import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Business } from 'src/app/models/business';
import { User } from 'src/app/models/user';
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

  constructor(private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private businessService: BusinessService){}



    ngOnInit(): void {

      let idStr = this.route.snapshot.paramMap.get('id');
      if (idStr) {
        let id: number = Number.parseInt(idStr);
        if (isNaN(id)) {
          this.router.navigateByUrl('**');
        } else {
          this.businessService.show(id).subscribe({
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
        }
      } else {
        this.router.navigateByUrl('home');
      }
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
          console.error('PetComponent.reload(): Error loading pet.');
          console.error(theError);
        }
      })
    }






}
