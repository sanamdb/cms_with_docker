import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-exam',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.scss']
})
export class ExamComponent implements OnInit {

  examForm!: FormGroup;
  
  // Arrays to hold dropdown data fetched from APIs
  departments: any[] = [];
  batches: any[] = [];

  constructor(
    private fb: FormBuilder,
    private http: HttpClient
  ) { }

  ngOnInit(): void {
    // 1. Initialize the form
    this.examForm = this.fb.group({
      examName: ['', [Validators.required, Validators.minLength(3)]],
      departmentId: ['', [Validators.required]],
      batch: ['', [Validators.required]],
      startDateTime: ['', [Validators.required]],
      endDateTime: ['', [Validators.required]],
      description: [''],
      // Initialize FormArray with one empty subject field
      subjects: this.fb.array([
        this.fb.control('', [Validators.required])
      ])
    });

    // 2. Fetch Dropdown Data
    this.loadDepartments();
    this.loadBatches();
  }

  // --- FormArray Getter ---
  get subjects(): FormArray {
    return this.examForm.get('subjects') as FormArray;
  }

  // --- Dynamic Subject Methods ---
  addSubject(): void {
    this.subjects.push(this.fb.control('', [Validators.required]));
  }

  removeSubject(index: number): void {
    if (this.subjects.length > 1) {
      this.subjects.removeAt(index);
    }
  }

  // --- API Calls ---
  loadDepartments(): void {
    this.http.get<any[]>('/college/api/v1/department').subscribe({
      next: (data) => this.departments = data,
      error: (err) => console.error('Failed to load departments', err)
    });
  }

  loadBatches(): void {
    this.http.get<any[]>('/college/api/v1/batch').subscribe({
      next: (data) => this.batches = data,
      error: (err) => console.error('Failed to load batches', err)
    });
  }

  // --- Submit Logic ---
  submitExam(): void {
    if (this.examForm.valid) {
      const payload = { ...this.examForm.value };

      // HTML datetime-local provides YYYY-MM-DDTHH:mm. 
      // The backend example includes seconds (:00). We append them if missing.
      if (payload.startDateTime && payload.startDateTime.length === 16) {
        payload.startDateTime += ':00';
      }
      if (payload.endDateTime && payload.endDateTime.length === 16) {
        payload.endDateTime += ':00';
      }

      this.http.post('/exam/api/v1/examination', payload).subscribe({
        next: (response) => {
          console.log('Exam created successfully', response);
          this.examForm.reset();
          
          // Reset the FormArray back to a single empty field after submit
          this.subjects.clear();
          this.addSubject();
        },
        error: (err) => console.error('Failed to create exam', err)
      });
    } else {
      this.examForm.markAllAsTouched();
    }
  }
}