import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Organisme } from '../models/organisme';

const baseUrl = 'http://localhost:8090/api/organisme';

@Injectable({
  providedIn: 'root'
})

export class OrganismeService {
  constructor(private http: HttpClient) { }
  
  getAll(): Observable<Organisme[]> {
    return this.http.get<Organisme[]>(baseUrl);
  }
  
  get(id: any): Observable<Organisme> {
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