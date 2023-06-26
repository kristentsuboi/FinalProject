import { AuthService } from 'src/app/services/auth.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Address } from '../models/address';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  private url = environment.baseUrl + 'api';

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


  createForBusiness(businessId: number, address: Address): Observable<Address> {
    return this.http.post<Address>(this.url + '/business/' + businessId + '/address', address, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('AddressService.create(): error creating address: ' + err)
        );
      })
    );
  }

  updateForBusiness(businessId: number, editAddress: Address): Observable<Address> {
    return this.http.put<Address>(this.url + '/business/' + businessId + '/address/' + editAddress.id, editAddress, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('AddressService.update(): error updating address: ' + err)
        );
      })
    );
  }

  destroyForBusiness(businessId: number, addressId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/business/' + businessId + '/address/' + addressId, this.getHttpOptions());
  }


  createForUser(userId: number, address: Address): Observable<Address> {
    return this.http.post<Address>(this.url + '/account/' + userId + '/address', address, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('AddressService.create(): error creating address: ' + err)
        );
      })
    );
  }

  updateForUser(userId: number, editAddress: Address): Observable<Address> {
    return this.http.put<Address>(this.url + '/account/' + userId + '/address/' + editAddress.id, editAddress, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error('AddressService.update(): error updating address: ' + err)
        );
      })
    );
  }

  destroyForUser(userId: number, addressId: number): Observable<void> {
    return this.http.delete<void>(this.url + '/account/' + userId + '/address/' + addressId, this.getHttpOptions());
  }



}
