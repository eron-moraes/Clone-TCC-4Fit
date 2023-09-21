import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Instrutor } from "../models/instrutor";
import { Observable } from "rxjs";
import { API_CONFIG } from "../config/api.config";

@Injectable({
  providedIn: "root",
})
export class InstrutorService {
  constructor(private http: HttpClient) {}

  findById(id: any): Observable<Instrutor> {
    return this.http.get<Instrutor>(`${API_CONFIG.baseUrl}/instrutor/${id}`);
  }

  findAll(): Observable<Instrutor[]> {
    return this.http.get<Instrutor[]>(`${API_CONFIG.baseUrl}/instrutor`);
  }

  create(instrutor: Instrutor): Observable<Instrutor> {
    return this.http.post<Instrutor>(
      `${API_CONFIG.baseUrl}/instrutor`,
      instrutor
    );
  }

  update(instrutor: Instrutor): Observable<Instrutor> {
    return this.http.put<Instrutor>(
      `${API_CONFIG.baseUrl}/instrutor`,
      instrutor
    );
  }

  delete(id: any): Observable<Instrutor> {
    return this.http.delete<Instrutor>(`${API_CONFIG.baseUrl}/instrutor/${id}`);
  }
}
