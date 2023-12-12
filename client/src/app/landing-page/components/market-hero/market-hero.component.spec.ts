import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketHeroComponent } from './market-hero.component';

describe('MarketHeroComponent', () => {
  let component: MarketHeroComponent;
  let fixture: ComponentFixture<MarketHeroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MarketHeroComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MarketHeroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
