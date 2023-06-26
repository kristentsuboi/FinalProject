import { Component } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent {
  public isCollapsed = false;

  constructor(private authService: AuthService) {
  }

  loggedInUser: User | null = null;

  ngOnInit() {
    // if(this.checkLogin()) {
    // this.getLoggedInUser();
    // }
  }

  checkLogin():boolean {
    return this.authService.checkLogin();
  }

  getLoggedInUser() {
  //   this.authService.getLoggedInUser().subscribe({
  //     next: (user) => {
  //       console.log(user);
  //       this.loggedInUser = user;
  //     },
  //     error: function (theError) {
  //       console.error('NavigationComponent.getLoggedInUser(): Error loading user.');
  //       console.error(theError);
  //     },
  //   });
  }

}
