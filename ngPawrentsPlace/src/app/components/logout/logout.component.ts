import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent {

  user: User = new User();

  constructor(
    private auth: AuthService,
    private route: ActivatedRoute,
    private router: Router
    ) {}

  logout(user: User) {
    this.auth.logout();
    if (!this.auth.checkLogin()) {
      this.router.navigateByUrl('/home');
    }
    else {
      console.error('LogoutComponent.logout(): Error logging out user.');
    }
  }

}
