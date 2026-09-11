import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Department } from '../interfaces/Department';
import { DepartmentRequest } from '../interfaces/DepartmentRequest';


@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  constructor(
    private http: HttpClient
  ) { }

  getDepartments(): Observable<Department[]> {
    return this.http.get<Department[]>('/college/api/v1/department');
  }

  createDepartment(payload: DepartmentRequest): Observable<Department> {
    return this.http.post<Department>('/college/api/v1/department', payload);
  }

}
