import { LowerCasePipe } from '@angular/common';
import { Injectable } from '@angular/core';
import { AbstractControl, ValidationErrors } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class NameValidatorService {

  constructor() { }

  static validateName(control: AbstractControl): ValidationErrors | null {
    const value = control.value
    console.log(value);
    if(value.includes('test') || value.includes('Test')){
      return {
        nameError: true
      }
    }
    return null
  }
}
