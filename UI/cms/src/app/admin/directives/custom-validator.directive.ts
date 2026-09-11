import { Directive } from '@angular/core';
import { AbstractControl, NG_VALIDATORS, ValidationErrors, Validators } from '@angular/forms';

@Directive({
  selector: '[appCustomValidator]',
  providers: [
    {
      provide: NG_VALIDATORS,
      useExisting: CustomValidatorDirective,
      multi: true
    }
  ]
})
export class CustomValidatorDirective implements Validators {

  constructor() { }

  validate(control: AbstractControl): ValidationErrors | null {
    const description = control.value;
    if (description.includes('Test')) {
      return {
        testError: true
      }
    }
    return null;

  }

}
