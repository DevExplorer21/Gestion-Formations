import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Domaine } from '../models/domaine';

const baseUrl = 'http://localhost:8090/api/domaine';

@Injectable({
  providedIn: 'root'
})

export class DomaineService {
  constructor(private http: HttpClient) { }
  
  getAll(): Observable<Domaine[]> {
    return this.http.get<Domaine[]>(baseUrl);
  }
  
  get(id: any): Observable<Domaine> {
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
  
  findByLibelle(title: any): Observable<Domaine[]> {
    return this.http.get<Domaine[]>(`${baseUrl}?libelle=${title}`);
  }
}