import {Component, EventEmitter, Input, Output} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DialogModule} from "primeng/dialog";
import {ButtonModule} from "primeng/button";
import {RippleModule} from "primeng/ripple";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-modal-success-navigate',
  standalone: true,
  imports: [CommonModule, DialogModule, ButtonModule, RippleModule, RouterLink],
  templateUrl: './modal-success-navigate.component.html',
  styleUrl: './modal-success-navigate.component.css'
})
export class ModalSuccessNavigateComponent {
  @Input() visible: boolean = false;
  @Output() visibleChange = new EventEmitter<boolean>();
}
