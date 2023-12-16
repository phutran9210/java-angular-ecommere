import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CheckboxModule} from "primeng/checkbox";
import {DividerModule} from "primeng/divider";
import {ButtonModule} from "primeng/button";
import {RippleModule} from "primeng/ripple";
import {ChipsModule} from "primeng/chips";
import {MessageService} from 'primeng/api';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {regexValidator, emailRegex} from "../../../../shared/regex/formRegex"
import {AuthService} from "../../../services/userService/AuthService";
import {catchError, tap} from 'rxjs/operators';
import {throwError} from 'rxjs';
import {ToastModule} from "primeng/toast";

@Component({
  selector: 'app-sign-in',
  standalone: true,
  providers: [MessageService],
  imports: [CommonModule, CheckboxModule, DividerModule, ButtonModule, RippleModule, ChipsModule, ReactiveFormsModule, ToastModule],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css'
})
export class SignInComponent {
  loginForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, regexValidator(emailRegex)]),
    password: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(320)]),
    checkbox: new FormControl(false)
  });

  constructor(private authService: AuthService,
              private messageService: MessageService) {
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const data = {
        email: this.loginForm.value.email,
        password: this.loginForm.value.password
      }
      this.authService.login(data).pipe(
        tap(response => {
          console.log(response);
        }),
        catchError(error => {
          return throwError(() => {
            this.showError(error.status)
          });
        })
      ).subscribe();
    } else {
      this.showError("300")
    }
  }

  get email() {
    return this.loginForm.get('email');
  }

  get password() {
    return this.loginForm.get('password');
  }

  showError(statusCode: string) {
    this.messageService.add({severity: 'error', summary: 'Error', detail: `Something wrong: ${statusCode}`});
  }

}
