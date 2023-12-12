import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TagModule} from "primeng/tag";

@Component({
  selector: 'app-contents',
  standalone: true,
  imports: [CommonModule, TagModule],
  templateUrl: './contents.component.html',
  styleUrl: './contents.component.css'
})
export class ContentsComponent {

}
