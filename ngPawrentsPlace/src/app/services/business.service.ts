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

  index(): Observable<Business[]> {
    return this.http.get<Business[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('BusinessService.index(): error retrieving business: ' + err)
        );
      })
    );
  }


  show(id: number): Observable<Business> {
    return this.http.get<Business>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('BusinessService.show(): error retrieving businesses: ' + err)
        );
      })
    );
  }

  showByServiceType(serviceTypeId: number): Observable<Business[]> {
    return this.http.get<Business[]>(this.url + '/serviceType/' + serviceTypeId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('BusinessService.showByServcieType(): error retrieving businesses: ' + err)
        );
      })
    );
  }

  create(business: Business): Observable<Business> {
    return this.http.post<Business>(this.url, business, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('BusinessService.create(): error creating business: ' + err)
        );
      })
    );
  }

  update(editBusiness: Business): Observable<Business> {
    return this.http.put<Business>(this.url + '/' + editBusiness.id, editBusiness, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('BusinessService.update(): error updating business: ' + err)
        );
      })
    );
  }

  destroy(businessId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/' + businessId, this.getHttpOptions());
  }

  addBusinessToUserList(userId: number, businessId: number){

  }

  removeBusinessFromUserList(userId: number, businessId: number){

  }

}
