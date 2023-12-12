import {Component} from "@angular/core";
import {ContentsComponent} from "./contents/contents.component";
import {FlashSaleComponent} from "./flash-sale/flash-sale.component";
import {FooterComponent} from "./footer/footer.component";
import {MarketHeroComponent} from "./market-hero/market-hero.component";
import {NavbarComponent} from "./navbar/navbar.component";
import {NewsComponent} from "./news/news.component";

@Component({
  selector: 'landing-page',
  standalone: true,
  templateUrl: 'landing-page.component.ts.html',
  imports: [ContentsComponent, FlashSaleComponent, FooterComponent, MarketHeroComponent, NavbarComponent, NewsComponent]
})
export class LandingPageComponent {

}
