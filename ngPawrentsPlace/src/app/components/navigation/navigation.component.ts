import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css'],
})
export class NavigationComponent implements OnInit {
  public isCollapsed = false;
  loggedInUser: User | null = null;

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.getLoggedInUser();
  }

  checkLogin(): boolean {
    return this.authService.checkLogin();
  }

  getLoggedInUser() {
    this.authService.getLoggedInUser().subscribe({
      next: (user) => {
        console.log(user);
        this.loggedInUser = user;
      },
      error: function (theError) {
        console.error('NavigationComponent.getLoggedInUser(): Error loading user.');
        console.error(theError);
      },
    });
  }
}
