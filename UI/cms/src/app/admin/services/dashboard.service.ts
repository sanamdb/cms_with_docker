import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { forkJoin, map, Observable } from 'rxjs';

// 1. Interfaces based on your API responses
export interface Campus { id: string; name: string; city: string; establishedDate: string; campusPicture: string; }
export interface Department { id: string; code: string; name: string; description: string; }
export interface CampusDepartment { campusName: string; departmentName: string; intakeCapacity: number; fees: number; headOfDepartment: string; }
export interface Batch { id: string; academicYear: string; status: string; }

export interface DashboardMetrics {
  totalCampuses: number;
  totalDepartments: number;
  totalIntakeCapacity: number;
  activeAdmissionBatch: string;
  recentCampuses: Campus[];
  activePrograms: CampusDepartment[];
}

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private http: HttpClient) { }

  getDashboardData(): Observable<DashboardMetrics> {
    // forkJoin runs all API calls concurrently and waits for all to finish
    return forkJoin({
      campusRes: this.http.get<any>('/college/api/v1/campus?page=0&size=20'),
      departments: this.http.get<Department[]>('/college/api/v1/department'),
      programs: this.http.get<CampusDepartment[]>('/college/api/v1/campus/department?status=Active'),
      batches: this.http.get<Batch[]>('/college/api/v1/batch')
    }).pipe(
      map(responses => {
        const campuses: Campus[] = responses.campusRes.content || [];
        
        // Find the batch that is currently open for admission
        const activeBatch = responses.batches.find(b => b.status === 'ADMISSION')?.academicYear || 'None';
        
        // Calculate total student capacity across all active programs
        const totalIntake = responses.programs.reduce((sum, program) => sum + (program.intakeCapacity || 0), 0);

        // Package and return the clean data object for the component
        return {
          totalCampuses: campuses.length, // Replace with campusRes.totalElements if your API supports it
          totalDepartments: responses.departments.length,
          totalIntakeCapacity: totalIntake,
          activeAdmissionBatch: activeBatch,
          recentCampuses: campuses,
          activePrograms: responses.programs
        };
      })
    );
  }
}