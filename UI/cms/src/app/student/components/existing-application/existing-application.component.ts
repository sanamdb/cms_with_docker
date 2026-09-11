import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AdmissionResponse } from '../../interfaces/AdmissionResponse';
import { ApplyAdmissionService } from '../../services/apply-admission.service';
import { Department } from '../../interfaces/Department';

@Component({
  selector: 'app-existing-application',
  templateUrl: './existing-application.component.html',
  styleUrls: ['./existing-application.component.scss']
})
export class ExistingApplicationComponent implements OnInit {

  appliedApplications: AdmissionResponse[] = [];
  departments: Department[] = [];

  constructor(
    private applyAdmissionService: ApplyAdmissionService
  ) { }

  ngOnInit(): void {
    this.applyAdmissionService.getAppliedApplication$.subscribe(applications => {
      this.appliedApplications = applications;
      applications.forEach(application => {
        this.applyAdmissionService.getDepartmentById(application.departmentId).subscribe(department => {
          this.departments.push(department);
        })
      })
    })
  }

  getDepartment(departmentId: string): Department | undefined {
    return this.departments.find(dept => dept.id === departmentId);
  }

}
