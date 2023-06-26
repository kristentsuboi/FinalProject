import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Comment } from '../models/comment';

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  url = 'http://api.skilldistillery.com:8080/comments/data/comment';

  constructor(private http: HttpClient) {}

  index(): Observable<Comment[]> {
    return this.http.get<Comment[]>(this.url + '?sorted=true').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          new Error('CommentService.index(): error retrieving comments: ' + err)
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
