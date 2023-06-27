import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Pet } from 'src/app/models/pet';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent {
  clients: Pet[] = [];
  bizOwner: User | null = null;
  logged: User| null = null;
  selected: Pet | null = null;

  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router
  ) {}


  ngOnInit(){
    this.getLoggedInUser();

  }

  getLoggedInUser() {
    this.authService.getLoggedInUser().subscribe({
      next: (user) => {
        this.logged = user;
      },
      error: function (theError) {
        console.error('PetListComponent.reload(): Error loading user.');
        console.error(theError);
      },
    });
  }

  calculateAge(birthdateStr: string): string {
    let birthdate =  new Date(birthdateStr);
    let timeDiff = Math.abs(Date.now() - birthdate.getTime());
    let years = Math.floor((timeDiff / (1000 * 3600 * 24))/365.25);
    let rmngTime = ((timeDiff / (1000 * 3600 * 24))/365.25) - Math.floor((timeDiff / (1000 * 3600 * 24))/365.25);
    let months = Math.floor(rmngTime * 12);
    return (years + ' years, ' + months + ' months old');
  }

  displayClient(selectedClient: Pet) {
    this.router.navigateByUrl('pets/' + selectedClient.id);

  }



}
