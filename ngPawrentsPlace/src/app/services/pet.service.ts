import { AuthService } from 'src/app/services/auth.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Pet } from '../models/pet';

@Injectable({
  providedIn: 'root'
})
export class PetService {

  private url = environment.baseUrl + 'api/pets';
  private pets: Pet[] = [];

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

  index(): Observable<Pet[]> {
    return this.http.get<Pet[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PetService.index(): error retrieving pets: ' + err)
        );
      })
    );
  }

  show(id: number): Observable<Pet> {
    return this.http.get<Pet>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PetService.show(): error retrieving pet: ' + err)
        );
      })
    );
  }

  create(pet: Pet): Observable<Pet> {
    return this.http.post<Pet>(this.url, pet, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('PetService.create(): error creating pet: ' + err)
        );
      })
    );
  }

  update(editPet: Pet): Observable<Pet> {
    return this.http.put<Pet>(this.url + '/' + editPet.id, editPet, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('PetService.update(): error updating pet: ' + err)
        );
      })
    );
  }

  destroy(id: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + id, this.getHttpOptions());
  }


}
