import {Component, OnDestroy, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {interval, Subscription} from 'rxjs';
import {map} from 'rxjs/operators';

@Component({
  selector: 'app-flash-sale',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './flash-sale.component.html',
  styleUrl: './flash-sale.component.css'
})
export class FlashSaleComponent implements OnInit, OnDestroy {
  isDivVisible: boolean = true;
  items = ['Mauris rhoncus aenean vel elit scelerisque mauris pellentesque pulvinar.',
    'In ante metus dictum at tempor commodo ullamcorper a lacus.',
    'Social CRM that helps you grow faster for free'];
  currentItem: string | null = null;
  private subscription: Subscription | null = null;

  ngOnInit() {
    this.subscription = interval(3000)
      .pipe(
        map(index => this.items[index % this.items.length])
      )
      .subscribe(item => {
        this.currentItem = item;
      });
  }

  ngOnDestroy() {
    this.subscription?.unsubscribe();
  }

  toggleDivVisibility(): void {
    this.isDivVisible = !this.isDivVisible;
  }

}
