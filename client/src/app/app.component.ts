import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterOutlet} from '@angular/router';
import {StyleClassModule} from 'primeng/styleclass';
import {NavbarComponent} from "./landing-page/components/navbar/navbar.component";
import {FlashSaleComponent} from "./landing-page/components/flash-sale/flash-sale.component";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [StyleClassModule, CommonModule, RouterOutlet, NavbarComponent, FlashSaleComponent],
  templateUrl: './app.component.html',
  styleUrl: '../styles/styles.css'
})
export class AppComponent {
  title = 'client';
}
