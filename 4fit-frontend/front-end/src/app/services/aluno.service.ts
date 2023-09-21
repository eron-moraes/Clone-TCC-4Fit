import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Aluno } from "../models/aluno";
import { Observable } from "rxjs";
import { API_CONFIG } from "../config/api.config";

@Injectable({
  providedIn: "root",
})
export class AlunoService {
  constructor(private http: HttpClient) {}

  findById(id: any): Observable<Aluno> {
    return this.http.get<Aluno>(`${API_CONFIG.baseUrl}/alunos/${id}`);
  }

  findAll(): Observable<Aluno[]> {
    return this.http.get<Aluno[]>(`${API_CONFIG.baseUrl}/alunos`);
  }

  create(aluno: Aluno): Observable<Aluno> {
    return this.http.post<Aluno>(`${API_CONFIG.baseUrl}/alunos`, aluno);
  }

  update(aluno: Aluno): Observable<Aluno> {
    return this.http.put<Aluno>(`${API_CONFIG.baseUrl}/alunos`, aluno);
  }

  delete(id: any): Observable<Aluno> {
    return this.http.delete<Aluno>(`${API_CONFIG.baseUrl}/alunos/${id}`);
  }
}
