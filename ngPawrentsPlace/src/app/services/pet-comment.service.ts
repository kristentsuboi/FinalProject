import { AuthService } from 'src/app/services/auth.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PetComment } from '../models/pet-comment';
import { Pet } from '../models/pet';

@Injectable({
  providedIn: 'root'
})
export class PetCommentService {

  private url = environment.baseUrl + 'api/pets';
  private pet: Pet = new Pet();
  private petComments: PetComment[] = [];

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

  index(petId: number): Observable<PetComment[]> {
    return this.http.get<PetComment[]>(this.url + '/' + petId + "/petcomments", this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PetCommentService.index(): error retrieving petComments: ' + err)
        );
      })
    );
  }

  create(petId: number, petComment: PetComment): Observable<PetComment> {
    return this.http.post<PetComment>(this.url + '/' + petId + '/petcomments', petComment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('PetCommentService.create(): error creating petComment: ' + err)
        );
      })
    );
  }

  update(petId: number, editPetComment: PetComment): Observable<PetComment> {
    return this.http.put<PetComment>(this.url + '/' + petId + '/petcomments/' + editPetComment.id, editPetComment, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('PetCommentService.update(): error updating petComment: ' + err)
        );
      })
    );
  }

  destroy(petId: number, petCommentId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + petId + '/petcomments/' + petCommentId, this.getHttpOptions());
  }


}
