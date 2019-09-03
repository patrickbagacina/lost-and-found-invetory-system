import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemsRedeemComponent } from './items-redeem.component';

describe('ItemsRedeemComponent', () => {
  let component: ItemsRedeemComponent;
  let fixture: ComponentFixture<ItemsRedeemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItemsRedeemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemsRedeemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
