import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { NavigationComponent } from '../navigation/navigation.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  user: User = new User();

  constructor(
    private auth: AuthService,
    private route: ActivatedRoute,
    private navigation: NavigationComponent,
    private router: Router
    ) {}

  login(user: User): void {
    console.log('Logging in user:');
    console.log(user);
    this.auth.login(user.username, user.password).subscribe({
          next: (loggedInUser) => {
            this.navigation.reload();
            if (loggedInUser.role === 'parent') {
              this.router.navigateByUrl('/pets');
            }
            if (loggedInUser.role === 'provider') {
              this.router.navigateByUrl('/clients');
            }
            user = new User();
          },
          error: (problem) => {
            console.error('LoginComponent.login(): Error logging in user.');
            console.error(problem);
          }
        });
      }

}
