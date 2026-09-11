import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { QuestionsService } from '../../services/questions.service';
import { Department } from '../../interfaces/Department';
import { Batch } from '../../interfaces/Batch';

@Component({
  selector: 'app-create-question',
  templateUrl: './create-question.component.html',
  styleUrls: ['./create-question.component.scss']
})
export class CreateQuestionComponent implements OnInit {

  questionForm!: FormGroup;
  departments: Department[] = [];
  batches: Batch[] = [];

  @ViewChild('successDialog') successDialog!: TemplateRef<any>;
  @ViewChild('errorDialog') errorDialog!: TemplateRef<any>;

  constructor(
    private questionService: QuestionsService,
    private fb: FormBuilder,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    // 1. Initialize Form
    this.questionForm = this.fb.group({
      departmentId: ['', Validators.required],
      batch: ['', Validators.required],
      subject: ['', Validators.required],
      question: ['', Validators.required],
      option1: ['', Validators.required],
      option2: ['', Validators.required],
      option3: ['', Validators.required],
      option4: ['', Validators.required],
      answer: ['', Validators.required]
    });

    // 2. Load Dropdown Data
    this.questionService.getAllDepartments().subscribe(dept => {
      this.departments = dept;
    });
    this.questionService.getAllBatches().subscribe(batch => {
      this.batches = batch;
    });
  }

  // Dynamically collect typed options for the Answer dropdown
  get availableOptions(): string[] {
    const opts = [];
    const formValues = this.questionForm.value;
    
    if (formValues.option1) opts.push(formValues.option1);
    if (formValues.option2) opts.push(formValues.option2);
    if (formValues.option3) opts.push(formValues.option3);
    if (formValues.option4) opts.push(formValues.option4);
    
    return opts;
  }

  onSubmit(): void {
    if (this.questionForm.valid) {
      const payload = this.questionForm.value;

      this.questionService.saveQuestion(payload).subscribe({
        next: (res) => {
          // Open the modern success popup
          this.dialog.open(this.successDialog, {
            width: '400px',
            panelClass: 'modern-success-dialog',
            disableClose: true
          });
        },
        error: (err) => {
          console.error('Failed to save question', err);
          // Open the error popup
          this.dialog.open(this.errorDialog, {
            width: '400px',
            panelClass: 'modern-error-dialog',
            disableClose: true
          });
        }
      });
    } else {
      this.questionForm.markAllAsTouched();
    }
  }

  closeSuccessDialog(): void {
    this.dialog.closeAll();
    this.questionForm.reset(); // Reset form on success
  }

  closeErrorDialog(): void {
    this.dialog.closeAll();
    // Intentionally not resetting the form so the user can fix the error
  }
}