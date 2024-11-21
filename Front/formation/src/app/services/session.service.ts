import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Session } from '../models/session';

const baseUrl = 'http://localhost:8090/api/session';

@Injectable({
  providedIn: 'root'
})

export class SessionService {
  constructor(private http: HttpClient) { }
  getAll(): Observable<Session[]> {
    return this.http.get<Session[]>(baseUrl);
  }
  
  get(id: any): Observable<Session> {
    return this.http.get(`${baseUrl}/${id}`);
  }
  
  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }
  
  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }
  
  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }
  
  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }
}