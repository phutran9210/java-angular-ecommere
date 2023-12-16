import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ChipsModule} from "primeng/chips";
import {ButtonModule} from "primeng/button";
import {RippleModule} from "primeng/ripple";
import {AbstractControl, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {emailRegex, passwordRegex, regexValidator} from "../../../../shared/regex/formRegex";
import {AuthService} from "../../../services/userService/AuthService";
import {MessageService} from 'primeng/api';
import {ToastModule} from "primeng/toast";
import {catchError, tap} from "rxjs/operators";
import {EMPTY, of, throwError} from "rxjs";
import {
  ModalSuccessNavigateComponent
} from "./modalSuccessNavigate/modal-success-navigate/modal-success-navigate.component";


@Component({
  selector: 'app-register',
  standalone: true,
  providers: [MessageService],
  imports: [CommonModule, ChipsModule, ButtonModule, RippleModule, ReactiveFormsModule, ToastModule, ModalSuccessNavigateComponent],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  showDialog = false
  registerForm: FormGroup = new FormGroup({
    email: new FormControl("", [Validators.required, regexValidator(emailRegex)]),
    password: new FormControl("", [Validators.required, regexValidator(passwordRegex)]),
    confirmPassword: new FormControl("", [Validators.required])
  }, {validators: [this.passwordMatchingValidator]})

  constructor(private authService: AuthService,
              private messageService: MessageService) {
  }

  get email() {
    return this.registerForm.get('email');
  }

  get password() {
    return this.registerForm.get('password');
  }

  get confirmPassword() {
    return this.registerForm.get('confirmPassword')
  }

  openDialog() {
    this.showDialog = true;
  }

  onSubmit() {
    if (!this.registerForm.valid) {
      return this.showError(300, "Data not valid")
    }
    const data = {
      email: this.registerForm.value.email,
      password: this.registerForm.value.password
    }
    this.authService.register(data).pipe(
      tap(response => {
        if (response.status >= 201 || response.status < 300) {
          this.registerForm.reset();
          this.openDialog()
        }
      }),
      catchError(error => {
        if (error.status === 409) {
          this.showError(409, error.error, true)
          return EMPTY
        }
        this.showError(error.status, error.error)
        return EMPTY
      })
    ).subscribe();
  }

  passwordMatchingValidator(formGroup: AbstractControl): null | { mismatch: boolean } {
    return formGroup.get('password')?.value === formGroup.get('confirmPassword')?.value
      ? null : {mismatch: true};
  }

  showError(statusCode: number, message: string, sticky?: boolean) {
    this.messageService.add({
      severity: 'error',
      summary: 'Error',
      detail: `${message} : ${statusCode}`,
      sticky: sticky
    });
  }


}
