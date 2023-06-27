import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Comment } from '../models/comment';
import { environment } from 'src/environments/environment.development';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  url = environment.baseUrl + 'api/account/comments';

  constructor(private http: HttpClient, private auth: AuthService) {}

  getHttpOptions() {
    const credentials = this.auth.getCredentials();
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + credentials,
      'X-Requested-With': 'XMLHttpRequest',
    });
    return {"headers": headers};
  }

  index(): Observable<Comment[]> {
    return this.http.get<Comment[]>(this.url,  this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log('Error fetching Comment list' + err);
        return throwError(
          () => new Error('CommentService.index(): error retrieving comments: ' + err)
        );
      })
    );
  }

  create(comment: Comment): Observable<Comment> {
    return this.http.post<Comment>(this.url, comment).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('CommentService.create(): error creating comment')
        );
      })
    );
  }
}
