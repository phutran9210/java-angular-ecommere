import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalSuccessNavigateComponent } from './modal-success-navigate.component';

describe('ModalSuccessNavigateComponent', () => {
  let component: ModalSuccessNavigateComponent;
  let fixture: ComponentFixture<ModalSuccessNavigateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalSuccessNavigateComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ModalSuccessNavigateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
