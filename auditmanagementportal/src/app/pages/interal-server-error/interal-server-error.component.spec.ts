import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InteralServerErrorComponent } from './interal-server-error.component';

describe('InteralServerErrorComponent', () => {
  let component: InteralServerErrorComponent;
  let fixture: ComponentFixture<InteralServerErrorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InteralServerErrorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InteralServerErrorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
