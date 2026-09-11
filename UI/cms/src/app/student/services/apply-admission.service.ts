import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Department } from '../interfaces/Department';
import { Batch } from '../interfaces/Batch';
import { Admission } from '../interfaces/Admission';
import { Observable } from 'rxjs';
import { AdmissionResponse } from '../interfaces/AdmissionResponse';
import { Apply } from '../interfaces/Apply';
import { StudentExamResponse } from '../interfaces/StudentExamResponse';

@Injectable({
  providedIn: 'root'
})
export class ApplyAdmissionService {

  constructor(
    private http: HttpClient
  ) { }

  getAllDepartments$ = this.http.get<Department[]>('/college/api/v1/department');
  getAllBatch$ = this.http.get<Batch[]>('/college/api/v1/batch');

  submitAdmissionForm(admissionForm: Apply): Observable<AdmissionResponse> {
    let username: string | null = sessionStorage.getItem('username');
    let customHeaders = new HttpHeaders({'userId': username?username: 'null'});
    return this.http.post<AdmissionResponse>(
      '/student/api/v1/apply', 
      admissionForm,
      {headers: customHeaders}
    );
  }

  getAppliedApplication$ = this.http.get<AdmissionResponse[]>('/student/api/v1/apply');
  getDepartmentById(id: string): Observable<Department> {
    return this.http.get<Department>(`/college/api/v1/department/${id}`);
  }

  getExaminationDetails(departmentId: string, batch: string): Observable<StudentExamResponse> {
    return this.http.get<StudentExamResponse>(`/exam/api/v1/examination/schedule/${departmentId}/${batch}`);
  }

}