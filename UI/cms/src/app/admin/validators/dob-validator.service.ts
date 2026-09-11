import { Injectable } from '@angular/core';
import { FormGroup, ValidationErrors } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class DobValidatorService {

  constructor() { }

  static validateDOB(form: FormGroup): ValidationErrors | null {
    const dobValue = form.get('dob')?.value;
    const dob = new Date(dobValue);
    const today = new Date();

    // 2. Calculate the exact date 18 years ago today
    const eighteenYearsAgo = new Date(
      today.getFullYear() - 18,
      today.getMonth(),
      today.getDate()
    );
    if (dob > eighteenYearsAgo) {
      form.get('dob')?.setErrors({ dobError: true });
      return {
        dobError: true
      }
    }
    return null;
  }

}
