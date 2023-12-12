import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ButtonModule} from "primeng/button";

@Component({
  selector: 'app-not-found',
  standalone: true,
  imports: [CommonModule, ButtonModule],
  templateUrl: './not-found.component.html',
  styleUrl: './not-found.component.css'
})
export class NotFoundComponent {

}
