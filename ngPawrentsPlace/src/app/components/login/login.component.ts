import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

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
    private router: Router
    ) {}

  login(user: User): void {
    console.log('Logging in user:');
    console.log(user);
    this.auth.login(user.username, user.password).subscribe({
          next: (loggedInUser) => {
            this.router.navigateByUrl('/pets');
          },
          error: (problem) => {
            console.error('LoginComponent.login(): Error logging in user.');
            console.error(problem);
          }
        });
      }

}
