import {AbstractControl, ValidatorFn} from "@angular/forms";

export const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
export const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/;

// Validator
export function regexValidator(regex: RegExp): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    const valid = regex.test(control.value);
    return valid ? null : {'invalidPassword': {value: control.value}};
  };
}


