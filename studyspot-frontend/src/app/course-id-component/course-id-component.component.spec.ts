import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseIdComponentComponent } from './course-id-component.component';

describe('CourseIdComponentComponent', () => {
  let component: CourseIdComponentComponent;
  let fixture: ComponentFixture<CourseIdComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CourseIdComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CourseIdComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
