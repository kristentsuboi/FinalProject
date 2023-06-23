import { AuthService } from 'src/app/services/auth.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Diet } from '../models/diet';
import { Pet } from '../models/pet';

@Injectable({
  providedIn: 'root'
})
export class DietService {

  private url = environment.baseUrl + 'api/pets';
  private pet: Pet = new Pet();
  private diets: Diet[] = [];

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  index(id: number): Observable<Diet[]> {
    return this.http.get<Diet[]>(this.url + '/' + id + "/diets", this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('DietService.index(): error retrieving diets: ' + err)
        );
      })
    );
  }



}
