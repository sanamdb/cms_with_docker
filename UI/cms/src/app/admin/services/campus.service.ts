import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Campus, CampusApiResponse } from '../interfaces/Campus';
import { Observable } from 'rxjs';
import { DepartmentOfferByACampusApiResponse } from '../interfaces/DepartmentOfferByACampusApiResponse';
import { CampusRequest } from '../interfaces/CampusRequest';

@Injectable({
  providedIn: 'root'
})
export class CampusService {

  constructor(private http: HttpClient) { }
  
    getCampus(): Observable<CampusApiResponse> {
      return this.http.get<CampusApiResponse>("/college/api/v1/campus?page=0&size=6");
    }
  
    getCampusPaginated(page: number, pageSize: number): Observable<CampusApiResponse> {
      return this.http.get<CampusApiResponse>(`/college/api/v1/campus?page=${page}&size=${pageSize}`);
    }
  
    getDepartmentOfferByCampus(id: string): Observable<DepartmentOfferByACampusApiResponse> {
      return this.http.get<DepartmentOfferByACampusApiResponse>(`/college/api/v1/campus/offer/department/${id}`);
    }

    deleteACampus(id: string): Observable<Campus> {
      return this.http.delete<Campus>(`/college/api/v1/campus/${id}`);
    }

    createCampus(campus: CampusRequest): Observable<Campus> {
      return this.http.post<Campus>('/college/api/v1/campus', campus);
    }

    getCampusById(id: string): Observable<Campus> {
      return this.http.get<Campus>(`/college/api/v1/campus/${id}`);
    }

    updateCampusById(id: string, payload: CampusRequest): Observable<Campus> {
      return this.http.put<Campus>(`/college/api/v1/campus/${id}`, payload);
    }
}
