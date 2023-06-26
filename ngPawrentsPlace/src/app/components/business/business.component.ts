import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Business } from 'src/app/models/business';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-business',
  templateUrl: './business.component.html',
  styleUrls: ['./business.component.css']
})
export class BusinessComponent {

  business: Business | null = null;
  loggedInUser: User | null = null;

  constructor(private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute){}


    ngOnInit(){
      this.getLoggedInUser();
      this.getBusiness();
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







}
