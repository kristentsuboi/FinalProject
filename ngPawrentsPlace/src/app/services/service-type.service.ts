import { AuthService } from 'src/app/services/auth.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ServiceType } from '../models/service-type';

@Injectable({
  providedIn: 'root'
})
export class ServiceTypeService {

 private url = environment.baseUrl + 'api/services';

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

  index(): Observable<ServiceType[]> {
    return this.http.get<ServiceType[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('ServicetypeService.index(): error retrieving serviceTypes: ' + err)
        );
      })
    );
  }



}
