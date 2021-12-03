import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateOperationsComponent } from './operations-create.component';

describe('AllAccountComponent', () => {
  let component: CreateOperationsComponent;
  let fixture: ComponentFixture<CreateOperationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateOperationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateOperationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
