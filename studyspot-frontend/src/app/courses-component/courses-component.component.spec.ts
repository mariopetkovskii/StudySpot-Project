import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoursesComponentComponent } from './courses-component.component';

describe('CoursesComponentComponent', () => {
  let component: CoursesComponentComponent;
  let fixture: ComponentFixture<CoursesComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoursesComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CoursesComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
