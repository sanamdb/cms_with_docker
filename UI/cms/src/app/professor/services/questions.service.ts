import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Department } from '../interfaces/Department';
import { Batch } from '../interfaces/Batch';

@Injectable({
  providedIn: 'root'
})
export class QuestionsService {

  constructor(
    private http: HttpClient
  ) { }

  getAllDepartments(): Observable<Department[]> {
    return this.http.get<Department[]>('/college/api/v1/department');
  }

  getAllBatches(): Observable<Batch[]> {
    return this.http.get<Batch[]>('/college/api/v1/batch');
  }

  saveQuestion(payload: any): Observable<any> {
    return this.http.post('/question/api/v1/question', payload);
  }

}
