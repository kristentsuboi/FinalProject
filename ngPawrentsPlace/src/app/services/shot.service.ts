import { AuthService } from 'src/app/services/auth.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Shot } from '../models/shot';
import { Pet } from '../models/pet';


@Injectable({
  providedIn: 'root'
})
export class ShotService {

  private url = environment.baseUrl + 'api/pets';
  private pet: Pet = new Pet();
  private shots: Shot[] = [];

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

  index(id: number): Observable<Shot[]> {
    return this.http.get<Shot[]>(this.url + '/' + id + "/shots", this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('ShotService.index(): error retrieving shots: ' + err)
        );
      })
    );
  }

  create(petId: number, shot: Shot): Observable<Shot> {
    return this.http.post<Shot>(this.url + '/' + petId + '/shots', shot, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('ShotService.create(): error creating shot: ' + err)
        );
      })
    );
  }

  update(petId: number, editShot: Shot): Observable<Shot> {
    return this.http.put<Shot>(this.url + '/' + petId + '/shots/' + editShot.id, editShot, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('ShotService.update(): error updating shot: ' + err)
        );
      })
    );
  }

  destroy(petId: number, shotId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + petId + '/shots/' + shotId, this.getHttpOptions());
  }



}
