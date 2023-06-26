import { AuthService } from 'src/app/services/auth.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Business } from '../models/business';

@Injectable({
  providedIn: 'root'
})
export class BusinessService {

  private url = environment.baseUrl + 'api/business';

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

  index(id: number): Observable<Business[]> {
    return this.http.get<Business[]>(this.url + '/' + id + "/businesss", this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('BusinessService.index(): error retrieving businesss: ' + err)
        );
      })
    );
  }

  create(userId: number, business: Business): Observable<Business> {
    return this.http.post<Business>(this.url + '/' + userId + '/businesss', business, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('BusinessService.create(): error creating business: ' + err)
        );
      })
    );
  }

  update(userId: number, editBusiness: Business): Observable<Business> {
    return this.http.put<Business>(this.url + '/' + userId + '/businesss/' + editBusiness.id, editBusiness, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('BusinessService.update(): error updating business: ' + err)
        );
      })
    );
  }

  destroy(userId: number, businessId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + userId + '/businesss/' + businessId, this.getHttpOptions());
  }


}
