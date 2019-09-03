import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RedeemedItemsIndexComponent } from './redeemed-items-index.component';

describe('RedeemedItemsIndexComponent', () => {
  let component: RedeemedItemsIndexComponent;
  let fixture: ComponentFixture<RedeemedItemsIndexComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RedeemedItemsIndexComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RedeemedItemsIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
