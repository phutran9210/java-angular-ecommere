import {Component} from "@angular/core";
import {FlashSaleComponent} from "../../landing-page/components/flash-sale/flash-sale.component";
import {NavbarComponent} from "../../landing-page/components/navbar/navbar.component";

@Component({
  selector: 'online-store',
  standalone: true,
  templateUrl: 'online-store.component.html',
  imports: [FlashSaleComponent, NavbarComponent]
})

export class OnlineStoreComponent {
}
