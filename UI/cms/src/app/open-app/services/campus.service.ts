import { Injectable } from '@angular/core';
import { CampusApiResponse } from '../interfaces/Campus';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DepartmentOfferByACampusApiResponse } from '../interfaces/DepartmentOfferByACampusApiResponse';

@Injectable({
  providedIn: 'root'
})
export class CampusService {

  constructor(private http: HttpClient) {
    console.log("CampusService Object created..");
   }

  getCampus(): Observable<CampusApiResponse> {
    return this.http.get<CampusApiResponse>("/college/api/v1/campus?page=0&size=6");
  }

  getCampusPaginated(page: number, pageSize: number): Observable<CampusApiResponse> {
    return this.http.get<CampusApiResponse>(`/college/api/v1/campus?page=${page}&size=${pageSize}`);
  }

  getDepartmentOfferByCampus(id: string): Observable<DepartmentOfferByACampusApiResponse> {
    return this.http.get<DepartmentOfferByACampusApiResponse>(`/college/api/v1/campus/offer/department/${id}`);
  }

}
