import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CheckboxModule} from "primeng/checkbox";
import {DividerModule} from "primeng/divider";
import {ButtonModule} from "primeng/button";
import {RippleModule} from "primeng/ripple";
import {ChipsModule} from "primeng/chips";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {regexValidator, emailRegex} from "../../../../shared/regex/formRegex"
import {AuthService} from "../../../services/userService/AuthService";

@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [CommonModule, CheckboxModule, DividerModule, ButtonModule, RippleModule, ChipsModule, ReactiveFormsModule],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css'
})
export class SignInComponent {
  loginForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, regexValidator(emailRegex)]),
    password: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(32)]),
    checkbox: new FormControl(false)
  });

  constructor(private authService: AuthService) {
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const data = {
        email: this.loginForm.value.email,
        password: this.loginForm.value.password
      }
      this.authService.login(data).subscribe(response => {
          console.log(response)
        },
        error => {
          console.log(error)
        })
    } else {
      console.log('Form không hợp lệ');
    }
  }

  get email() {
    return this.loginForm.get('email');
  }

  get password() {
    return this.loginForm.get('password');
  }

}
