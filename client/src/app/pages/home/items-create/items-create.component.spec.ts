import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemsCreateComponent } from './items-create.component';

describe('ItemsCreateComponent', () => {
  let component: ItemsCreateComponent;
  let fixture: ComponentFixture<ItemsCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItemsCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItemsCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
