import { AuthService } from 'src/app/services/auth.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { MedicalNote } from '../models/medical-note';
import { Pet } from '../models/pet';

@Injectable({
  providedIn: 'root'
})
export class MedicalNoteService {

  private url = environment.baseUrl + 'api/pets';
  private pet: Pet = new Pet();
  private medicalNotes: MedicalNote[] = [];

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

  index(id: number): Observable<MedicalNote[]> {
    return this.http.get<MedicalNote[]>(this.url + '/' + id + "/medicalNotes", this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('MedicalNoteService.index(): error retrieving medicalNotes: ' + err)
        );
      })
    );
  }

  create(petId: number, medicalNote: MedicalNote): Observable<MedicalNote> {
    return this.http.post<MedicalNote>(this.url + '/' + petId + '/medicalNotes', medicalNote, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('MedicalNoteService.create(): error creating medicalNote: ' + err)
        );
      })
    );
  }

  update(petId: number, editMedicalNote: MedicalNote): Observable<MedicalNote> {
    return this.http.put<MedicalNote>(this.url + '/' + petId + '/medicalNotes/' + editMedicalNote.id, editMedicalNote, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('MedicalNoteService.update(): error updating medicalNote: ' + err)
        );
      })
    );
  }

  destroy(petId: number, medicalNoteId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + petId + '/medicalNotes/' + medicalNoteId, this.getHttpOptions());
  }



}
