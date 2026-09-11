import { Component, OnInit } from '@angular/core';
import { Apply } from '../../interfaces/Apply';
import { Observable } from 'rxjs';
import { Department } from '../../interfaces/Department';
import { ApplyAdmissionService } from '../../services/apply-admission.service';
import { Batch } from '../../interfaces/Batch';
import { error } from 'console';
import Swal from 'sweetalert2'; // <-- Import SweetAlert2
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-admission-apply',
  templateUrl: './admission-apply.component.html',
  styleUrls: ['./admission-apply.component.scss']
})
export class AdmissionApplyComponent implements OnInit {

  applicationForm: Apply = {
    departmentId: '',
    batch: '',
    action: 'SUBMIT'
  }

  departments$ = new Observable<Department[]>();
  batch$ = new Observable<Batch[]>();

  constructor(
    private applyAdmissionService: ApplyAdmissionService
  ) { }

  ngOnInit(): void {
    this.departments$ = this.applyAdmissionService.getAllDepartments$;
    this.batch$ = this.applyAdmissionService.getAllBatch$;
  }

  applyForAdmission(applyForm: NgForm) {
    this.applyAdmissionService.submitAdmissionForm(applyForm.value).subscribe({
      next: (data) => {
        Swal.fire({
          title: 'Success!',
          text: data.remarks || 'Your admission application has been submitted successfully.',
          icon: 'success',
          confirmButtonColor: '#2563eb',
          timer: 3000,
          timerProgressBar: true
        });
        applyForm.resetForm({
          departmentId: '',
          batch: '',
          action: 'SUBMIT'
        })
      },
      error: (error) => {
        Swal.fire({
          title: 'Submission Failed',
          text: error.error?.message || 'Something went wrong. Please try again.',
          icon: 'error',
          confirmButtonColor: '#ef4444'
        });
        applyForm.resetForm({
          departmentId: '',
          batch: '',
          action: 'SUBMIT'
        })
      }
    })
  }

}
