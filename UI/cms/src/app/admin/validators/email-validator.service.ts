import { Injectable } from '@angular/core';
import { AbstractControl, ValidationErrors } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class EmailValidatorService {

  constructor() { }

  static validateEmail(input: string) {
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value;
      if(!value.includes(input)) {
        return {
          emailError: true
        }
      }
      return null
    }
  }

}
