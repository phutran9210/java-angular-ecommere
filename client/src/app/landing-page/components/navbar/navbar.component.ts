import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ButtonModule} from "primeng/button";
import {RippleModule} from "primeng/ripple";
import {StyleClassModule} from "primeng/styleclass";
import {RouterLink} from "@angular/router";
import {AuthService} from "../../../online-store/services/userService/AuthService";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, ButtonModule, RippleModule, StyleClassModule, RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {
  isLogin: boolean = false;

  constructor(private authService: AuthService) {
  }

  ngOnInit() {
    this.authService.checkLoginStatus().subscribe(
      response => {

        console.log(response)
      },
      error => {
        console.log(error)
      }
    )


  }

}
