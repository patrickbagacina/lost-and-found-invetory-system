import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RedeemedItemsViewComponent } from './redeemed-items-view.component';

describe('RedeemedItemsViewComponent', () => {
  let component: RedeemedItemsViewComponent;
  let fixture: ComponentFixture<RedeemedItemsViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RedeemedItemsViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RedeemedItemsViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
