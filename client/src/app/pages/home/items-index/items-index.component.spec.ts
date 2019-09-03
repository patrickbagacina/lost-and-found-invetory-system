import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemsIndexComponent } from './items-index.component';

describe('ItemsIndexComponent', () => {
  let component: ItemsIndexComponent;
  let fixture: ComponentFixture<ItemsIndexComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItemsIndexComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemsIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
