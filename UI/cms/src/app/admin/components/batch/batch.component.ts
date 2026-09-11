import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-batch',
  templateUrl: './batch.component.html',
  styleUrls: ['./batch.component.scss']
})
export class BatchComponent implements OnInit {

  batchForm!: FormGroup;
  
  // Generates an array of strings from "2010" to "2050"
  years: string[] = Array.from({ length: 41 }, (_, i) => (2010 + i).toString());
  
  // Available statuses
  statuses: string[] = ['NOTHING', 'ADMISSION', 'STARTED', 'COMPLETED'];

  // Grabs the dialog template from the HTML
  @ViewChild('confirmDialog') confirmDialog!: TemplateRef<any>;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.batchForm = this.fb.group({
      academicYear: ['', [Validators.required]],
      status: ['NOTHING', [Validators.required]] // Defaulting to NOTHING
    });
  }

  // Triggered when the user clicks the main Save button
  openConfirmation() {
    if (this.batchForm.valid) {
      this.dialog.open(this.confirmDialog, {
        width: '400px',
        disableClose: true // Prevents closing by clicking outside
      });
    } else {
      this.batchForm.markAllAsTouched();
    }
  }

  // Triggered from inside the Dialog popup
  confirmSubmit() {
    const payload = this.batchForm.value;
    
    this.http.post('/college/api/v1/batch', payload).subscribe({
      next: (response) => {
        console.log('Batch created successfully', response);
        this.dialog.closeAll();
        
        // Reset the form, keeping the default status
        this.batchForm.reset({ status: 'NOTHING' }); 
        
        // Optional: Show a success snackbar/alert here
      },
      error: (err) => {
        console.error('Error creating batch', err);
        this.dialog.closeAll();
        // Optional: Show an error snackbar/alert here
      }
    });
  }

  // Triggered from inside the Dialog popup
  cancelSubmit() {
    this.dialog.closeAll();
  }

}