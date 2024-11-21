import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Formateur } from '../models/formateur';

const baseUrl = 'http://localhost:8090/api/formateur';

@Injectable({
  providedIn: 'root'
})

export class FormateurService {
  constructor(private http: HttpClient) { }
  
  getAll(): Observable<Formateur[]> {
    return this.http.get<Formateur[]>(baseUrl);
  }
  
  get(id: any): Observable<Formateur> {
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