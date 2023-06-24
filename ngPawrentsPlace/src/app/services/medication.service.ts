import { AuthService } from 'src/app/services/auth.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Medication } from '../models/medication';
import { Pet } from '../models/pet';

@Injectable({
  providedIn: 'root'
})
export class MedicationService {

  private url = environment.baseUrl + 'api/pets';
  private pet: Pet = new Pet();
  private medications: Medication[] = [];

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

  index(id: number): Observable<Medication[]> {
    return this.http.get<Medication[]>(this.url + '/' + id + "/medications", this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('MedicationService.index(): error retrieving medications: ' + err)
        );
      })
    );
  }

  create(petId: number, medication: Medication): Observable<Medication> {
    return this.http.post<Medication>(this.url + '/' + petId + '/medications', medication, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('MedicationService.create(): error creating medication: ' + err)
        );
      })
    );
  }

  update(petId: number, editMedication: Medication): Observable<Medication> {
    return this.http.put<Medication>(this.url + '/' + petId + '/medications/' + editMedication.id, editMedication, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('MedicationService.update(): error updating medication: ' + err)
        );
      })
    );
  }

  destroy(petId: number, medicationId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + petId + '/medications/' + medicationId, this.getHttpOptions());
  }


}
