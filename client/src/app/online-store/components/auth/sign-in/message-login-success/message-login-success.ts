import {Component, EventEmitter, Input, Output} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RippleModule} from "primeng/ripple";


@Component({
  selector: 'app-message-login-success',
  standalone: true,
  imports: [CommonModule, RippleModule,],
  templateUrl: './message-login-success.html',

})
export class ModalSuccessNavigateComponent {
  @Input() visible: boolean = false;
  @Output() visibleChange = new EventEmitter<boolean>();
  
  toggleVisibility() {
    this.visible = !this.visible;
    this.visibleChange.emit(this.visible);
  }
}
