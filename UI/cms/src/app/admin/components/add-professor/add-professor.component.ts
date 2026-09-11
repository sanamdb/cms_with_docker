import { state } from '@angular/animations';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { exhaustMap, mergeMap, switchMap } from 'rxjs';
import { NameValidatorService } from '../../validators/name-validator.service';
import { EmailValidatorService } from '../../validators/email-validator.service';
import { DobValidatorService } from '../../validators/dob-validator.service';


@Component({
  selector: 'app-add-professor',
  templateUrl: './add-professor.component.html',
  styleUrls: ['./add-professor.component.scss']
})
export class AddProfessorComponent implements OnInit {

  professorForm!: FormGroup;

  get subjects() {
    return this.professorForm.get('subjects') as FormArray;
  }

  years: number[] = Array.from({ length: 21 }, (_, i) => i);;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient
  ) { }

  ngOnInit(): void {
    this.professorForm = this.fb.group({
      id: new FormControl('', { updateOn: 'blur', validators: [Validators.required, Validators.min(101)] }),
      firstName: ['', { updateOn: 'blur', validators: [Validators.required, Validators.minLength(2), Validators.maxLength(20), NameValidatorService.validateName] }],
      lastName: [''],
      email: ['', [EmailValidatorService.validateEmail('@'), Validators.email]],
      dob: ['', [Validators.required]],
      qualification: [''],
      totalYearOfExperience: ['', [Validators.required]],
      totalYearOfExperienceInTeaching: ['', [Validators.required]],
      gender: [''],
      address: this.fb.group({
        addressLine1: ['', [Validators.required, Validators.minLength(10)]],
        addressLine2: [''],
        city: [''],
        state: [''],
        pincode: [''],
      }),
      subjects: this.fb.array([
        this.fb.group({
          name: ['', [Validators.required, Validators.minLength(2)]],
          yearOfExperience: [''],
        })
      ]),
      tnc: [false]
    }, { /*updateOn: 'blur',*/ validators: [DobValidatorService.validateDOB] });

    //this.onLoadSetValue();
    this.onLoadPatchSet();
    /*
    this.professorForm.valueChanges.pipe(mergeMap((data) => {
      return this.http.get<any>('https://jsonplaceholder.typicode.com/users');
    })).subscribe(response => {
      console.log(response);
    })
    */

    /*
    this.professorForm.valueChanges.pipe(switchMap((data) => {
      return this.http.get<any>('https://jsonplaceholder.typicode.com/users');
    })).subscribe(response => {
      console.log(response);
    })
    */
    this.professorForm.valueChanges.pipe(exhaustMap((data) => {
      return this.http.get<any>('https://jsonplaceholder.typicode.com/users');
    })).subscribe(response => {
      console.log(response);
    })
  }

  /**
   * if all field not provided: Must supply a value for form control with name: 'lastName'
   */
  onLoadSetValue() {
    this.professorForm.setValue({
      id: '',
      firstName: 'Rohan'
    })
  }

  onLoadPatchSet() {
    this.professorForm.patchValue({
      id: 101,
      firstName: 'Rohan'
    })
  }

  submitProfessor() {
    this.professorForm.reset();
  }

  addSubject() {
    this.subjects.push(this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2)]],
      yearOfExperience: [''],
    }))
  }

  onDelete(i: number) {
    this.subjects.removeAt(i);
  }

  changedTnc() {
    if (this.professorForm.get('tnc')?.value) {
      this.professorForm.addControl('signature', new FormControl(''));
    } else {
      this.professorForm.removeControl('signature');
    }
  }

}
