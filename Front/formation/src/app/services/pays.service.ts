import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pays } from '../models/pays';

const baseUrl = 'http://localhost:8090/api/pays';

@Injectable({
  providedIn: 'root'
})

export class PaysService {
  constructor(private http: HttpClient) { }
  
  getAll(): Observable<Pays[]> {
    return this.http.get<Pays[]>(baseUrl);
  }
  
  get(id: any): Observable<Pays> {
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