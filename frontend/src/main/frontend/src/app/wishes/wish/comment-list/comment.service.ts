import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {ItemComment} from "./comment";

@Injectable()
export class CommentService {

  constructor(private http: HttpClient) {}

  add(idItem : string, comment:ItemComment): Observable<any> {
    let headers = new HttpHeaders()
      .set("Authorization", `Bearer ${JSON.parse(localStorage.currentUser).token}`);

    return this.http.post<any>('second/comment/'+idItem, comment, {headers:headers}).map(comment => {
      return comment;
    });
  }

  getAll(idItem: string): Observable<any[]> {
    let headers = new HttpHeaders()
      .set("Authorization", `Bearer ${JSON.parse(localStorage.currentUser).token}`);

    return this.http.get<any>('second/comment/'+idItem+'/comments', {headers:headers})
      .map(comments => {
        return comments;
      })
  }

  destroy(idComment):Observable<any> {
    let headers = new HttpHeaders()
      .set("Authorization", `Bearer ${JSON.parse(localStorage.currentUser).token}`);

    return this.http.delete<any>('second/comment/'+idComment, {headers:headers})
      .map(comment => {
        return comment;
      })
  }
}
