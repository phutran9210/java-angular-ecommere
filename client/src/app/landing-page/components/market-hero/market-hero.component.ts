import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ChipsModule} from "primeng/chips";

@Component({
  selector: 'app-market-hero',
  standalone: true,
  imports: [CommonModule, ChipsModule],
  templateUrl: './market-hero.component.html',
  styleUrl: './market-hero.component.css'
})
export class MarketHeroComponent {

}
