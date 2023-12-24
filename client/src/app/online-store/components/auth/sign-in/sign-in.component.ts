import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CheckboxModule} from "primeng/checkbox";
import {DividerModule} from "primeng/divider";
import {ButtonModule} from "primeng/button";
import {RippleModule} from "primeng/ripple";
import {ChipsModule} from "primeng/chips";
import {Message, MessageService} from 'primeng/api';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {regexValidator, emailRegex} from "../../../../shared/regex/formRegex"
import {AuthService} from "../../../services/userService/AuthService";
import {catchError, tap} from 'rxjs/operators';
import {throwError} from 'rxjs';
import {ToastModule} from "primeng/toast";
import {ActivatedRoute, Router} from "@angular/router";
import {MessagesModule} from "primeng/messages";
import {LoginResponse} from "../../../../core/models/response.model";

@Component({
  selector: 'app-sign-in',
  standalone: true,
  providers: [MessageService],
  imports: [CommonModule, CheckboxModule, DividerModule, ButtonModule, RippleModule, ChipsModule, ReactiveFormsModule, ToastModule, MessagesModule],
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
              private messageService: MessageService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const data = {
        email: this.loginForm.value.email,
        password: this.loginForm.value.password
      }
      this.authService.login(data).pipe(
        tap(response => {
          if (response.status >= 200 || response.status < 300 && response.body) {
            const body: LoginResponse = response.body as LoginResponse
            this.authService.saveAccessToken(body.accessToken)
            // this.loginForm.reset();
            // this.router.navigate(['']).then(() => {
            //
            // }).catch(error => {
            //   // Xử lý lỗi điều hướng, nếu có
            // });

          }
        }),
        catchError(error => {
          console.log(error)
          return throwError(() => {
            this.showError(error.status, error.error, true)
          });
        })
      ).subscribe();
    } else {
      this.showError(300, "unknown")
    }
  }

  get email() {
    return this.loginForm.get('email');
  }

  get password() {
    return this.loginForm.get('password');
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
