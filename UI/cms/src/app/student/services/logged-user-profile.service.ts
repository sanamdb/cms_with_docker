import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoggedInProfile } from '../interfaces/LoggedInProfile';
import { Admission } from '../interfaces/Admission';
import { Campus } from '../interfaces/Campus';
import { Department } from '../interfaces/Department';

@Injectable({
  providedIn: 'root'
})
export class LoggedUserProfileService {

  constructor(
    private http: HttpClient
  ) { }

  getLoggedUserProfile(userId: string): Observable<LoggedInProfile> {
    return this.http.get<LoggedInProfile>(`/student/api/v1/student/${userId}`);
  }

  getAdmission(userId: string): Observable<Admission> {
    return this.http.get<Admission>(`/college/api/v1/admission/${userId}`);
  }

  getStudentCampus(campusId: string): Observable<Campus> {
    return this.http.get<Campus>(`/college/api/v1/campus/${campusId}`);
  }

  getStudentDepartment(departmentId: string): Observable<Department> {
    return this.http.get<Department>(`/college/api/v1/department/${departmentId}`);
  }

}
